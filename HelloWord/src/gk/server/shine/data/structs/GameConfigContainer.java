package gk.server.shine.data.structs;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.server.shine.data.manager.GameConfigParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 游戏配置容器
 * 
 * @author hdh
 *
 */
public abstract class GameConfigContainer<T extends GameConfig> {
    protected final static Logger logger = LoggerFactory.getLogger(GameConfigContainer.class);
    /**
     * 配置的class
     */
    protected final Class<T> clazz;

    protected final List<T> list = new ArrayList<>();

    protected final Map<Integer, T> map = new HashMap<>();

    public GameConfigContainer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract void load();

    public void load(String filePath) {
        File file = new File(filePath);
        if (!file.isFile() || !file.exists()) {
            throw new NullPointerException("clazz[" + clazz.getName() + "] file[" + filePath + "] not exists.");
        }
        load(file);
    }

    public void load(File file) {
        try {
            String fileStr = FileUtils.readFileToString(file, Charsets.UTF_8);
            List<T> tmpList = GameConfigParser.toList(fileStr, clazz);
            list.addAll(tmpList);
            for (T t : list) {
                map.put(t.getSid(), t);
            }
        } catch (IOException e) {
            logger.error("load[" + file.getName() + "] clazz[" + clazz.getName() + "] error.", e);
            throw new RuntimeException("load[" + file.getName() + "] clazz[" + clazz.getName() + "] error.");
        }
    }

    /**
     * ManagerPool.init时执行 重新加载时 reloadDataManger.reload时执行
     * 
     * @param reload 是否重新加载时执行
     */
    public void afterLoad(boolean reload) {

    }

    public Map<Integer, T> getMap() {
        return map;
    }

    public List<T> getList() {
        return list;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T get(int sid) {
        return map.get(sid);
    }

}
