package flower.common.component.service;

import flower.common.component.Component;

public interface Service extends Component,Comparable<Service>{

	int PRIORITY_LOWEST = 10;
	int PRIORITY_LOW = 30;
	int PRIORITY_NORMAL = 50;
	int PRIORITY_HIGH = 70;
	int PRIORITY_HIGHEST = 90;

	String getName();

	ServiceStatus getStatus();

	/**
	 * 启动优先级
	 * 
	 * @return
	 */
	default int getPriority() {
		return PRIORITY_NORMAL;
	}

	@Override
	default int compareTo(Service o) {
		return Integer.compare(o.getPriority(), getPriority());
	}
	
}
