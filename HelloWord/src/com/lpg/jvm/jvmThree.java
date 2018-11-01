package com.lpg.jvm;

import java.math.BigInteger;

public class jvmThree {

	public static void main(String[] args) {

		BigInteger[] pArr = new BigInteger[10000];

		pArr[0] = new BigInteger("0");

		pArr[1] = new BigInteger("1");

		for (int i = 2; i < 10000; i++) {

			pArr[i] = pArr[i - 1].add(pArr[i - 2]);

			try {

				Thread.sleep(100);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			System.out.println("第" + i + ":" + pArr[i]);

		}

	}

}
