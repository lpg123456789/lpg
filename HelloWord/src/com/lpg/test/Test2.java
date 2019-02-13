package com.lpg.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 他是在二进制中的问题
 * @author lpg
 * 2018年12月6日
 */
public class Test2 {

	public static void main(String[] args) {
		
		//-18623.828383202726
		//攻击 8492.0 物防穿透 0.0 物理防御 43247.0 技能物理百分比0.5
		//攻击 8492.0 魔防穿透 0.0魔法防御 1590.0 技能魔法百分比0.0
		//技能固定值 0.0 固定增伤 0.0  固定减伤[守] 2098.0
		//暴击系数 1.0 最终伤害加成减免 1.0 伤害浮动值 0.9562695891352071
		
		double atk=8492.0;
		
		double ppen=0;
		double pdef=43247.0;
		double pper=0.5;
		
		double mpen=0;
		double mdef=1590.0;
		double mper=0.0;
		
		double hurt= 0.0 ;
		double fdmgAdd=0.0;
		double fdmgRed=2098.0;
		
		double critFac=1;
		double dmgARFac=1;
		double fac=0.9;
		
		
		double a=(atk + ppen - pdef) * pper;
		double b=(atk + mpen - mdef) * mper + hurt + fdmgAdd - fdmgRed;
		double c=critFac * dmgARFac * fac;
		
		double result=(a+b) * c;
		System.out.println("结果是"+ result);
			
	}
	
	
}
