package com.lpg.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 二进制、字节数组、序列化工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月30日 下午2:45:17
 */
public class BinaryUtils {
	/**
	 * 对象转Byte数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] objectToByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			bytes = byteArrayOutputStream.toByteArray();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				}
				catch (IOException e) {
					// Logs.error("close objectOutputStream failed, " + e);
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				}
				catch (IOException e) {
					// Logs.error("close byteArrayOutputStream failed, " + e);
				}
			}

		}
		return bytes;
	}

	/**
	 * Byte数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byteArrayToObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			obj = objectInputStream.readObject();
		}
		catch (Exception e) {
			// Logs.error("byteArrayToObject failed, " + e);
		}
		finally {
			if (byteArrayInputStream != null) {
				try {
					byteArrayInputStream.close();
				}
				catch (IOException e) {
					// Logs.error("close byteArrayInputStream failed, " + e);
				}
			}
			
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				}
				catch (IOException e) {
					// Logs.error("close objectInputStream failed, " + e);
				}
			}
		}
		return obj;
	}
	
}
