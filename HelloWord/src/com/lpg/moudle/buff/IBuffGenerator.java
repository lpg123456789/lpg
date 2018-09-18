package com.lpg.moudle.buff;

import java.util.ArrayList;

public interface IBuffGenerator {

	/**
	 * 获取可能产生的BUFF的信息
	 * @return
	 */
	public ArrayList<BuffInfo> getBuffInfos();
}
