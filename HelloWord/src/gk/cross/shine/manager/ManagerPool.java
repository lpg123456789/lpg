package gk.cross.shine.manager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import gk.common.shine.component.GameService;
import gk.cross.shine.cross.across.manager.ACrossManager;
import gk.cross.shine.exception.ServerStarupError;

/**
 * manager工厂类
 *
 * @author oyxz
 * @version 1.0
 * @since 2018-01-26
 */

public class ManagerPool extends GameService {

	private static ManagerPool instance;

	private final Map<Class<? extends GameService>, GameService> serviceMap = new HashMap<>();

	public ACrossManager aCrossManager;

	private ManagerPool() {

	}

	public static ManagerPool getInstance() {
		if (instance == null) {
			synchronized (ManagerPool.class) {
				if (instance == null)
					instance = new ManagerPool();
			}
		}
		return instance;
	}

	@Override
	public void init() {
		logger.info("[STARTUP]ManagerPool init start.");
		registerAll();
		for (GameService service : getSortServices()) {
			try {
				service.init();
			} catch (Throwable e) {
				logger.error("service[" + service.getClass().getName() + "] init error.", e);
			}
		}
		logger.info("[STARTUP]ManagerPool init success.");
	}

	@Override
	public void destroy() {
		// 停服时 启动优先级越高的 越晚执行关闭
		List<GameService> services = getSortServices();
		Collections.reverse(services);
		for (GameService service : services) {
			try {
				service.destroy();
			} catch (Throwable e) {
				logger.error("service[" + service.getClass().getName() + "] destroy error.", e);
			}
		}
		logger.warn("[SHUTDOWN]ManagerPool destroy success.");
	}

	private List<GameService> getSortServices() {
		List<GameService> services = new ArrayList<>(serviceMap.values());
		return services;
	}

	private void registerAll() {
		try {
			// 兼容旧代码
			// 扫描该类中所有gameService的实现类自动注册
			Field[] fields = getClass().getFields();
			for (Field field : fields) {
				Class<?> clazz = field.getType();
				if (!GameService.class.isAssignableFrom(clazz)) {
					String fieldName = field.getName();
					System.err.println(fieldName);
					continue;
				}
				@SuppressWarnings("unchecked")
				Class<? extends GameService> serviceClazz = (Class<? extends GameService>) clazz;
				Object object = field.get(this);
				GameService service = null;
				if (object != null) {
					service = serviceClazz.cast(object);
					registerService(service);
				} else {
					service = registerService(serviceClazz);
					field.set(this, service);
				}
			}
		} catch (Exception e) {
			logger.error("registerAll error.", e);
			throw new ServerStarupError();
		}
	}

	private void registerService(GameService service) {
		Class<? extends GameService> clazz = service.getClass();
		serviceMap.put(clazz, service);
	}

	private GameService registerService(Class<? extends GameService> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		GameService service = clazz.getConstructor().newInstance();
		serviceMap.put(clazz, service);
		return service;
	}

}
