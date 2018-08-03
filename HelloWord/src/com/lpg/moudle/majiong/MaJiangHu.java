package com.lpg.moudle.majiong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaJiangHu {

	public static void main(String[] args) {
		int[] data = new int[] { 11, 11, 11, 12, 13, 14, 15, 16, 17, 18, 19, 19, 19 };
		List<Integer> pais = new ArrayList<Integer>();
		for (Integer i : data) {
			pais.add(i);
		}
		int[] ting = new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19 };
		long begin = System.currentTimeMillis();
		for (Integer i : ting) {
			System.out.println(pais);
			boolean canHu = IsCanHU(pais, i);
			System.out.println("胡牌为" + i + "可以胡否" + canHu);
			System.out.println();
		}
		long end = System.currentTimeMillis();
		System.out.println("time " + (end - begin));
	}

	public static boolean IsCanHU(List<Integer> mah, int ID) {
		List<Integer> pais = new ArrayList<Integer>(mah);
		pais.add(ID);
		// 只有两张牌
		if (pais.size() == 2) {
			return pais.get(0) == pais.get(1);
		}
		// 先排序
		Collections.sort(pais);
		// 依据牌的顺序从左到右依次分出将牌
		for (int i = 0; i < pais.size(); i++) {
			List<Integer> paiT = new ArrayList<Integer>(pais);
			Integer pai = paiT.get(i);
			int count = getNumById(paiT, pai);
			// 判断是否能做将牌
			if (count >= 2) {
				// 移除两张将牌
				paiT.remove(pai);
				paiT.remove(pai);
				// 避免重复运算 将光标移到其他牌上
				i += count - 1;
				if (HuPaiPanDin(paiT)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean HuPaiPanDin(List<Integer> mahs) {
		if (mahs.size() == 0) {
			return true;
		}
		Integer pai = mahs.get(0);
		int count = getNumById(mahs, pai);
		// 组成克子
		if (count == 3) {
			mahs.remove(pai);
			mahs.remove(pai);
			mahs.remove(pai);
			return HuPaiPanDin(mahs);
		} else { // 组成顺子
			if (mahs.contains(mahs.get(1)) && mahs.contains(mahs.get(2))) {
				mahs.remove(0);
				mahs.remove(0);
				mahs.remove(0);
				return HuPaiPanDin(mahs);
			}
			return false;
		}
	}

	public static int getNumById(List<Integer> mah, int pai) {
		int totalNum = 0;
		for (Integer integer : mah) {
			if (integer.intValue() == pai) {
				totalNum++;
			}
		}
		return totalNum;
	}
}
