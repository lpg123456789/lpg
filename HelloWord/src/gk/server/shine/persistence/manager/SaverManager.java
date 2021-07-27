package gk.server.shine.persistence.manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.server.shine.persistence.Dao;
import gk.server.shine.persistence.PersistenceData;
import gk.server.shine.persistence.Saver;
import gk.server.shine.persistence.annotation.TableName;
import gk.server.shine.persistence.bean.GroupData;
import gk.server.shine.persistence.impl.DefaultSaver;

public class SaverManager {

	private final static Logger logger = LoggerFactory.getLogger(SaverManager.class);

	private static SaverManager instance = new SaverManager();

	public static SaverManager getInstance() {
		return instance;
	}

	private final Map<Class<?>, Saver<?>> saverMap = new HashMap<>();

	/**
	 * 启动时执行<br>
	 * 需早于其他模块启动
	 */
	public void startup() {
		registerAll();
		for (Entry<Class<?>, Saver<?>> entry : saverMap.entrySet()) {
			try {
				Saver<?> saver = entry.getValue();
				saver.startup();
			} catch (Exception e) {
				logger.error("saver[" + entry.getKey() + "] startup error.", e);
			}
		}
	}

	/**
	 * 关服时执行<br>
	 * 需晚于其他模块关闭
	 */
	public void shutdown() {
		for (Entry<Class<?>, Saver<?>> entry : saverMap.entrySet()) {
			try {
				Saver<?> saver = entry.getValue();
				saver.shutdown();
			} catch (Exception e) {
				logger.error("saver[" + entry.getKey() + "] shutdown error.", e);
			}
		}
	}

	private void registerAll() {
		registerDefaultSaver(GroupData.class);
	}

	private <T extends PersistenceData> void registerDefaultSaver(Class<T> clazz) {
		Dao<T> dao = DaoFactory.getInstance().getDao(clazz);
		if (dao == null) {
			logger.error("[{}] dao is null.", clazz);
			throw new NullPointerException("clazz[" + clazz.getName() + "] dao is null");
		}
		Saver<T> saver = new DefaultSaver<>(dao);
		registerSaver(clazz, saver);
	}

	private <B> String getTableTableName(Class<B> clazz) {
		TableName tableName = clazz.getAnnotation(TableName.class);
		String name;
		if (tableName != null) {
			name = tableName.value();
		} else {
			name = clazz.getSimpleName().toLowerCase();
		}
		return name;
	}

	private <T> void registerSaver(Class<T> clazz, Saver<T> saver) {
		saverMap.put(clazz, saver);
	}

	public Map<Class<?>, Saver<?>> getSaverMap() {
		return saverMap;
	}

	@SuppressWarnings("unchecked")
	public <S extends Saver<T>, T> S getSaver(Class<T> clazz) {
		Saver<?> saver = saverMap.get(clazz);
		if (saver == null) {
			return null;
		}
		return (S) saver;
	}

	/**
	 * 阻塞保存数据
	 * 
	 * @param      <T>
	 * @param bean
	 */
	@SuppressWarnings("unchecked")
	public <T> void blockSave(T bean) {
		if (bean == null) {
			return;
		}
		Class<T> beanClazz = (Class<T>) bean.getClass();
		Saver<T> saver = getSaver(beanClazz);
		if (saver == null) {
			logger.error("blockSave error.saver is null");
			throw new NullPointerException();
		}
		saver.blockSave(bean);
	}

	/**
	 * 异步保存数据
	 * 
	 * @param      <T>
	 * @param bean
	 */
	@SuppressWarnings("unchecked")
	public <T> void asyncSave(T bean) {
		if (bean == null) {
			return;
		}
		Class<T> beanClazz = (Class<T>) bean.getClass();
		Saver<T> saver = getSaver(beanClazz);
		if (saver == null) {
			logger.error("blockSave error.saver is null");
			throw new NullPointerException();
		}
		saver.asyncSave(bean);
	}

	/**
	 * 保存数据
	 * 
	 * @param       <T>
	 * @param bean
	 * @param block 是否阻塞当前线程
	 */
	@SuppressWarnings("unchecked")
	public <T> void save(T bean, boolean block) {
		if (bean == null) {
			return;
		}
		Class<T> beanClazz = (Class<T>) bean.getClass();
		Saver<T> saver = getSaver(beanClazz);
		if (saver == null) {
			logger.error("blockSave error.saver is null");
			throw new NullPointerException();
		}
		if (block) {
			saver.blockSave(bean);
		} else {
			saver.asyncSave(bean);
		}
	}

	public <T> void blockSave(Class<T> beanClazz, Collection<T> beans) {
		if (beanClazz == null) {
			throw new NullPointerException("beanClazz is null.");
		}
		if (beans == null || beans.isEmpty()) {
			return;
		}
		Saver<T> saver = getSaver(beanClazz);
		if (saver == null) {
			logger.error("asyncSave error.saver is null");
			throw new NullPointerException();
		}
		saver.batchSave(beans, true);
	}

	public <T> void asyncSave(Class<T> beanClazz, Collection<T> beans) {
		if (beanClazz == null) {
			throw new NullPointerException("beanClazz is null.");
		}
		if (beans == null || beans.isEmpty()) {
			return;
		}
		Saver<T> saver = getSaver(beanClazz);
		if (saver == null) {
			logger.error("asyncSave error.saver is null");
			throw new NullPointerException();
		}
		saver.batchSave(beans, false);
	}

}
