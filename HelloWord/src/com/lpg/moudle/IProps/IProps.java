package com.lpg.moudle.IProps;

public interface IProps {
	/**
	 * 获取模板id
	 * @return
	 */
	public int getTemplateId();
	
	/**
	 * 获取名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 获取道具大类型
	 * @return
	 */
	public int getType();
	
	/**
	 * 获取道具子类
	 * @return
	 */
	public int getSubType();
	
	/**
	 * 获取道具品质
	 * @return
	 */
	public int getQuality();
	
	/**
	 * 获取颜色值（带#号）
	 * @return
	 */
	public String getColor();
	

	/**
	 * 能否叠加
	 * @return
	 */
	public boolean canOverlying();
	
	
	/**
	 * 获取道具掉落权重
	 * @return
	 */
	public int getDropWeight();
	
	
	/**
	 * 能否批量使用
	 * @return
	 */
	public boolean canBatch();
}
