package gk.server.shine.persistence.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.server.shine.persistence.Dao;
import gk.server.shine.persistence.PersistenceData;
import gk.server.shine.persistence.Saver;
import gk.server.shine.utils.task.TaskHandle;
import gk.server.shine.utils.task.TaskManager;


public class DefaultSaver<T extends PersistenceData> implements Saver<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 定时任务间隔
     */
    protected final long tickInterval;

    protected final Dao<T> dao;

    /**
     * 待保存的数据
     */
    protected final ConcurrentMap<Object, T> waitSaveDatas = new ConcurrentHashMap<>();

    protected TaskHandle tickTask;

    public DefaultSaver(Dao<T> dao) {
        this(DEFAULT_TICK_INTERVAL, dao);
    }

    public DefaultSaver(long tickInterval, Dao<T> dao) {
        if (tickInterval <= 0) {
            throw new IllegalArgumentException("tick interval <=0.");
        }
        if (dao == null) {
            throw new IllegalArgumentException("dao is null.");
        }
        this.tickInterval = tickInterval;
        this.dao = dao;
    }

    @Override
    public void startup() {
        tickTask = TaskManager.getInstance().scheduleTask(() -> {
            tick();
        }, tickInterval, tickInterval);
    }

    @Override
    public void shutdown() {
        if (tickTask != null) {
            tickTask.cancel();
            tickTask = null;
        }
        doSaveAll(true);
    }

    protected void tick() {
        if (waitSaveDatas.isEmpty()) {
            return;
        }
        doSaveAll(false);
    }

    protected void doSaveAll(boolean shutdown) {
        List<T> failList = new ArrayList<>();
        Iterator<Entry<Object, T>> iterator = waitSaveDatas.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Object, T> entry = iterator.next();
            T data = entry.getValue();
            iterator.remove();
            boolean success = false;
            try {
                success = dao.save(data);
            } catch (Exception e) {
                logger.error("save data error.key[" + data.getPrimaryKey() + "]", e);
                success = false;
            }
            if (!success) {
                failList.add(data);
            }
        }
        if (!failList.isEmpty()) {
            for (T data : failList) {
                Object key = data.getPrimaryKey();
                waitSaveDatas.putIfAbsent(key, data);
            }
        }
		/* if (shutdown && !waitSaveDatas.isEmpty()) {
		    String keysStr = StringUtils.join(waitSaveDatas.keySet(), ',');
		    logger.error("shutdown save fail.failList size:{},keys:[{}]", waitSaveDatas.size(), keysStr);
		}*/
    }

    @Override
    public void asyncSave(T data) {
        if (data == null) {
            throw new NullPointerException("asyncSave error.data is null.");
        }
        Object key = data.getPrimaryKey();
        if (key == null) {
            throw new NullPointerException("asyncSave error.key is null.");
        }
        waitSaveDatas.put(key, data);
    }

    @Override
    public boolean blockSave(T data) {
        if (data == null) {
            throw new NullPointerException("blockSave error.data is null.");
        }
        Object key = data.getPrimaryKey();
        if (key == null) {
            throw new NullPointerException("blockSave error.key is null.");
        }
        boolean result = dao.save(data);
        return result;
    }

    @Override
    public boolean save(T data, boolean block) {
        if (data == null) {
            throw new NullPointerException("save error.data is null.");
        }
        Object key = data.getPrimaryKey();
        if (key == null) {
            throw new NullPointerException("save error.key is null.");
        }
        if (block) {
            return dao.save(data);
        } else {
            waitSaveDatas.put(key, data);
            return true;
        }
    }

    @Override
    public void batchSave(Collection<T> datas, boolean block) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        if (block) {
            dao.batchSave(datas);
        } else {
            for (T data : datas) {
                Object key = data.getPrimaryKey();
                if (key == null) {
                    //logger.error("async batch save [{}] fail.key is null.", ReflectionToStringBuilder.toString(data));
                    continue;
                }
                waitSaveDatas.put(key, data);
            }
        }
    }

    public long getTickInterval() {
        return tickInterval;
    }

    public TaskHandle getTickTask() {
        return tickTask;
    }

    public void setTickTask(TaskHandle tickTask) {
        this.tickTask = tickTask;
    }

    public Dao<T> getDao() {
        return dao;
    }

    public ConcurrentMap<Object, T> getWaitSaveDatas() {
        return waitSaveDatas;
    }

}
