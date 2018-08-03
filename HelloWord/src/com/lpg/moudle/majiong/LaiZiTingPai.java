package com.lpg.moudle.majiong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaiZiTingPai {

	public static void main(String[] args) {

	}

	/**
	 * @param mah
	 *            整副牌
	 * @param laiZi
	 *            癞子的那张牌
	 * @return
	 */
	public boolean myLaiZiTingPai(List<Integer> mah, Integer laiZi) {
		return laiZiCanHu(mah, laiZi);
	}

	// 癞子是否可以胡
	public static boolean laiZiCanHu(List<Integer> mah, Integer laiZi) {
		List<Integer> pais = new ArrayList<Integer>(mah);
		// 先排序
		Collections.sort(pais);
		if (pais.size() == 2) {
			if (pais.get(0) == pais.get(1)) {
				return true;
			}
			if (pais.contains(laiZi)) {
				return true;
			}
			return false;
		}

		int laiZiNum = 0;
		for (Integer integer : pais) {
			if (integer == laiZi) {
				laiZiNum++;
			}
		}
		for (int i = 0; i < laiZiNum; i++) {
			pais.remove(laiZi);
		}

		// 依据牌的顺序从左到右依次分出将牌
		for (int i = 0; i < pais.size(); i++) {
			int myLaiZiNum = laiZiNum;
			List<Integer> paiT = new ArrayList<Integer>();
			paiT.addAll(pais);
			// 第一个牌
			Integer currentPai = paiT.get(i);
			int count = getPaiNum(paiT, currentPai);
			// 如果只有一个数量（1.癞子 2.普通牌）
			// 如果不只一个数量（1.癞子 2.普通牌）
			if (count >= 2) {
				paiT.remove(currentPai);
				paiT.remove(currentPai);
			} else {
				if (currentPai == laiZi) {// 癞子（只有一个数量是凑不成对子的）
					continue;
				} else {// 不是癞子
					myLaiZiNum--;
					paiT.remove(currentPai);
					paiT.remove(laiZi);
				}
			}
			List<Integer> arrayList = new ArrayList<>();
			if (HuPaiPanDin(paiT, arrayList, myLaiZiNum, paiT.size(), 0)) {
				return true;
			}
		}
		return false;
	}

	// 胡的是正常的刻顺胡
	// 递归后的麻将，paiSize去除癞子后的牌的数量，当前达到的位置
	private static boolean HuPaiPanDin(List<Integer> mahs, List<Integer> leftPais, int laiZiNum, int paiSize, int currentIndex) {
		// System.out.println("mahs"+mahs+" leftPais"+leftPais+" laiZiNum"+laiZiNum+"
		// paiSize"+paiSize+" currentIndex"+currentIndex);
		if (mahs.size() == 0 && leftPais.size() == 0) {
			return true;
		}
		// 说明已经遍历结束了，但是还未能成功，就必须要走癞子流程
		if (paiSize == currentIndex) {
			return HuPaiPanDinLaiZi(leftPais, laiZiNum);
		}
		Integer currentPai = mahs.get(0);
		int count = getPaiNum(mahs, currentPai);
		// 组成克子
		if (count == 3) {
			mahs.remove(mahs.get(0));
			mahs.remove(mahs.get(0));
			mahs.remove(mahs.get(0));
			return HuPaiPanDin(mahs, leftPais, laiZiNum, paiSize, currentIndex + 3);
		} else { // 组成顺子
			Integer left = mahs.get(0);
			if (left < 40) {// 万筒条
				Integer mid = left + 1;
				Integer right = left + 2;
				if (mahs.contains(mid) && mahs.contains(right)) {
					mahs.remove(right);
					mahs.remove(mid);
					mahs.remove(left);
					return HuPaiPanDin(mahs, leftPais, laiZiNum, paiSize, currentIndex + 3);
				} else {
					mahs.remove(left);
					leftPais.add(left);
					return HuPaiPanDin(mahs, leftPais, laiZiNum, paiSize, currentIndex + 1);
				}
			} else {
				mahs.remove(left);
				leftPais.add(left);
				return HuPaiPanDin(mahs, leftPais, laiZiNum, paiSize, currentIndex + 1);
			}
		}
	}

	// 胡的是有癞子的刻顺胡，癞子要进行一个万能牌填充（这个才能进行癞子的补充）
	public static boolean HuPaiPanDinLaiZi(List<Integer> leftPais, int laiZiNum) {
		if (laiZiNum < 0) {
			return false;
		}
		if (leftPais.size() == 0) {
			return true;
		}
		Integer currentPai = leftPais.get(0);
		if (currentPai < 40) {// 小于40
			// 未完成的顺子或者对子，需要减去一张癞子，否则减去两张癞子
			int num = getPaiNum(leftPais, currentPai);
			if (num == 2) {// 有对子
				leftPais.remove(currentPai);
				leftPais.remove(currentPai);
				laiZiNum -= 1;
				return HuPaiPanDinLaiZi(leftPais, laiZiNum);
			} else {
				leftPais.remove(currentPai);
				Integer mid = currentPai + 1;
				Integer right = currentPai + 2;
				if (leftPais.contains(mid) || leftPais.contains(right)) {// 有完成一半的顺子
					if (leftPais.contains(mid)) {
						leftPais.remove(mid);
					}
					if (leftPais.contains(right)) {
						leftPais.remove(right);
					}
					laiZiNum -= 1;
				} else {
					laiZiNum -= 2;
				}
				return HuPaiPanDinLaiZi(leftPais, laiZiNum);
			}
		} else {// 风牌 未完成的对子，需要减去一张癞子，否则减去两张癞子
			int num = getPaiNum(leftPais, currentPai);
			if (num == 1) {
				leftPais.remove(currentPai);
				laiZiNum -= 2;
			}
			if (num == 2) {
				leftPais.remove(currentPai);
				leftPais.remove(currentPai);
				laiZiNum -= 1;
			}
			return HuPaiPanDinLaiZi(leftPais, laiZiNum);
		}
	}

	// 排完序后获取数量
	public static int getPaiNum(List<Integer> pais, int nowPai) {
		int beginIndex = pais.indexOf(nowPai);
		int lastIndex = pais.lastIndexOf(nowPai);
		return lastIndex - beginIndex + 1;
	}

}
