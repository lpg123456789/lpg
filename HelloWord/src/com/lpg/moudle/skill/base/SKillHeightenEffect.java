package com.lpg.moudle.skill.base;

import java.util.ArrayList;

import com.lpg.moudle.buff.BuffInfo;
import com.lpg.moudle.buff.IBuffGenerator;
import com.lpg.moudle.skill.config.SkillHeightenTemplate;

/**
 * 技能增强的最终效果。
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月19日 上午11:33:35
 */
public class SKillHeightenEffect implements IBuffGenerator {
	private int pvePer;
	private int pveMPer;
	private int pvpPer;
	private int pvpMPer;
	private int pveHurt;
	private int pvpHurt;
	private ArrayList<BuffInfo> buffInfos = new ArrayList<>();

	/**
	 * 获取PVE物理百分比
	 * 
	 * @return
	 */
	public int getPvePer() {
		return pvePer;
	}

	/**
	 * 获取PVE魔法百分比
	 * 
	 * @return
	 */
	public int getPveMPer() {
		return pveMPer;
	}

	/**
	 * 获取PVP物理百分比
	 * 
	 * @return
	 */
	public int getPvpPer() {
		return pvpPer;
	}

	/**
	 * 获取PVP魔法百分比
	 * 
	 * @return
	 */
	public int getPvpMPer() {
		return pvpMPer;
	}

	/**
	 * 获取PVE固定伤害
	 * 
	 * @return
	 */
	public int getPveHurt() {
		return pveHurt;
	}

	/**
	 * 获取PVP固定伤害
	 * 
	 * @return
	 */
	public int getPvpHurt() {
		return pvpHurt;
	}

	@Override
	public ArrayList<BuffInfo> getBuffInfos() {
		return buffInfos;
	}

	/**
	 * 叠加技能增强效果。即，将新的技能增强的数值加在先前已经存在的数值上
	 * 
	 * @param t
	 */
	public void superposition(SkillHeightenTemplate t) {
		this.pvePer += t.getPvePer();
		this.pveMPer += t.getPveMPer();
		this.pvpPer += t.getPvpPer();
		this.pvpMPer += t.getPvpMPer();
		this.pveHurt += t.getPveHurt();
		this.pvpHurt += t.getPvpHurt();
		
		if (t.getBuffId() != 0) {
			buffInfos.add(new BuffInfo(t.getBuffId(), t.getOdds(), t.getBuffTarget(), t.getBuffTime()));
		}
	}
	
	/**
	 * 将效果数据重置清零
	 */
	public void reset() {
		this.pvePer = 0;
		this.pveMPer = 0;
		this.pvpPer = 0;
		this.pvpMPer = 0;
		this.pveHurt = 0;
		this.pvpHurt = 0;
		this.buffInfos.clear();
	}
}
