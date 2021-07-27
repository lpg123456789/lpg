package gk.server.shine.data.manager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import gk.common.shine.component.GameService;
import gk.server.shine.data.bean.A_Bean;
import gk.server.shine.data.container.A_Container;
import gk.server.shine.data.structs.GameConfig;
import gk.server.shine.data.structs.GameConfigContainer;

public class DataManager extends GameService {
	
	  /**
     * 所有容器类集合
     */
    private Map<Class<?>, GameConfigContainer<?>> containers = new HashMap<>();
    
    public A_Container container = new A_Container();
    
    public DataManager() {
        registerAll();
    }
    
    public void registerAll() {
    	containers.put(A_Bean.class, container);
    }

	@Override
	public void init() {
		for (GameConfigContainer<?> container : containers.values()) {
			container.load();
		}
		// 所有配置加成成功后执行afterLoad
		for (GameConfigContainer<?> container : containers.values()) {
			container.afterLoad(false);
		}
	}

	public Map<Class<?>, GameConfigContainer<?>> getContainers() {
		return containers;
	}

	@SuppressWarnings("unchecked")
	public <T extends GameConfig> GameConfigContainer<T> getContainer(Class<T> clazz) {
		GameConfigContainer<?> container = containers.get(clazz);
		if (container == null) {
			return null;
		}
		return (GameConfigContainer<T>) container;
	}

	public <T extends GameConfig> T get(Class<T> clazz, int id) {
		GameConfigContainer<?> container = containers.get(clazz);
		if (container == null) {
			return null;
		}
		GameConfig gameConfig = container.get(id);
		if (gameConfig == null) {
			return null;
		}
		return clazz.cast(gameConfig);
	}

	@SuppressWarnings("unchecked")
	public <T extends GameConfig> T get(Class<T> clazz, Predicate<T> predicate) {
		List<T> list = getList(clazz);
		T t = list.stream().filter(predicate).findAny().orElse(null);
		return t;
	}

	@SuppressWarnings("unchecked")
	public <T extends GameConfig> List<T> getList(Class<T> clazz) {
		GameConfigContainer<?> container = containers.get(clazz);
		if (container == null) {
			return null;
		}
		return (List<T>) container.getList();
	}

	@SuppressWarnings("unchecked")
	public <T extends GameConfig> Map<Integer, T> getMap(Class<T> clazz) {
		GameConfigContainer<?> container = containers.get(clazz);
		if (container == null) {
			return null;
		}
		return (Map<Integer, T>) container.getMap();
	}

	/**
	 * 获取满足条件的配置
	 *
	 * @param           <T>
	 * @param clazz
	 * @param predicate 返回true时塞到结果列表中
	 * @return
	 */
	public <T extends GameConfig> Map<Integer, T> getConfigs(Class<T> clazz, Predicate<T> predicate) {
		if (clazz == null) {
			throw new NullPointerException("clazz is null");
		}
		if (predicate == null) {
			return getMap(clazz);
		}
		GameConfigContainer<T> container = getContainer(clazz);
		if (container == null) {
			return Collections.emptyMap();
		}
		Map<Integer, T> allConfigs = container.getMap();
		Map<Integer, T> result = new HashMap<>();
		for (T config : allConfigs.values()) {
			if (predicate.test(config)) {
				result.put(config.getSid(), config);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T extends GameConfig> Map<Integer, T> getMap(Class<T> clazz, Collection<Integer> ids) {
		if (clazz == null) {
			throw new NullPointerException("getMap error.clazz is null.");
		}
		if (ids == null || ids.isEmpty()) {
			return null;
		}
		GameConfigContainer<?> container = containers.get(clazz);
		if (container == null) {
			return Collections.emptyMap();
		}
		Map<Integer, T> allConfigs = (Map<Integer, T>) container.getMap();
		Map<Integer, T> result = new HashMap<>();
		for (int id : ids) {
			T config = allConfigs.get(id);
			if (config == null) {
				continue;
			}
			result.put(id, config);
		}
		return result;
	}
}
