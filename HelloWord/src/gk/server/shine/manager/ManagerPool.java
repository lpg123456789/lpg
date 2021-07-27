package gk.server.shine.manager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gk.common.shine.component.GameService;
import gk.server.shine.data.manager.DataManager;
import gk.server.shine.func.a.manager.AManager;
import gk.server.shine.func.b.manager.BManager;
import gk.server.shine.manager.exception.ServerStarupError;

public class ManagerPool extends GameService {

	private static ManagerPool instance = new ManagerPool();

	public static ManagerPool getInstance() {
		return instance;
	}
	
	private final Map<Class<? extends GameService>, GameService> serviceMap = new HashMap<>();

	private ManagerPool() {

	}

	public AManager aManager;

	public BManager bManager;
	
    /**
     * 数据配置管理器
     */
    public DataManager dataManager;

	@Override
	public void init(){
		logger.info("[STARTUP]ManagerPool init start.");
		registerAll();
		for (GameService service : getSortServices()) {
			try {
				service.init();
			} catch (Throwable e) {
				logger.error("service[" + service.getClass().getName() + "] init error.", e);
				throw new ServerStarupError();
			}
		}
		logger.info("[STARTUP]ManagerPool init success.");
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

	private List<GameService> getSortServices() {
		List<GameService> services = new ArrayList<>(serviceMap.values());
		//Collections.sort(services);
		return services;
	}
	
	private void registerService(GameService service) {
        Class<? extends GameService> clazz = service.getClass();
        serviceMap.put(clazz, service);
    }

    private GameService registerService(Class<? extends GameService> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        GameService service = clazz.getConstructor().newInstance();
        serviceMap.put(clazz, service);
        return service;
    }

	@Override
	public void ready() throws Exception {
		
	}

	@Override
	public void suspend() {
	
	}

	@Override
	public void destroy() {
		
	}

}
