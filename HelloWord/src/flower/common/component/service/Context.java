package flower.common.component.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import flower.common.component.AbstractEntity;
import flower.common.utils.ClazzUtil;

public abstract class Context extends AbstractEntity<Service> {

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize() {
		//加载配置
		registerAll();
		// 初始化
		List<Service> list = getSortList(false);
		for (Service service : list) {
			long serviceBeginTime = System.currentTimeMillis();
			try {
				service.initialize();
			} catch (Throwable e) {
				throw e;
			}

		}
		// 监听关闭进程
		// 初始化之后才开始监听
		registerShutdownHook();

	}

	/**
	 * 注册监听进程关闭钩子
	 */
	protected void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			shutdown();
		}));
	}

	public void shutdown() {
		logger.info("{} shutdown begin.", getClass().getSimpleName());
		stop();
		destroy();
		logger.info("{} shutdown success.", getClass().getSimpleName());
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	/**
	 * 注册所有服务
	 */
	protected void registerAll() {
		try {
			List<ContextRegister> registerList = scanRegisterList();
			for (ContextRegister register : registerList) {
				register.registerAll();
			}
		} catch (Throwable e) {
			logger.error("auto register error.", e);
			//throw new ServerStarupError(e);
		}
	}

	/**
	 * 扫描register包下的注册类
	 * 
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	protected List<ContextRegister> scanRegisterList() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<ContextRegister> registerList = new ArrayList<>();
		String registerPackage = getRegisterPackage();
		List<Class<?>> registerClazzList = ClazzUtil.scanClassList(registerPackage, (clazz) -> {
			if (ContextRegister.class.isAssignableFrom(clazz)) {
				return true;
			}
			return false;
		});
		for (Class<?> registerClazz : registerClazzList) {
			ContextRegister register = (ContextRegister) registerClazz.getConstructor().newInstance();
			registerList.add(register);
		}
		return registerList;
	}

	/**
	 * 获取注册类目录<br>
	 * 
	 * @return
	 */
	protected String getRegisterPackage() {
		String contextPackage = getClass().getPackage().getName();
		String registerPackage = contextPackage + ".register";
		return registerPackage;
	}

	protected List<Service> getSortList(boolean reverse) {
		List<Service> list = new ArrayList<>();
		list.addAll(components.values());
		Collections.sort(list);
		if (reverse) {
			Collections.reverse(list);
		}
		return list;
	}

}
