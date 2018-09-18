package com.lpg.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 网络工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月30日 下午2:17:35
 */
public class NetUtils {
	/**
	 * ip地址转成long型数字  * 将IP地址转化成整数的方法如下：  * 1、通过String的split方法按.分隔得到4个长度的数组
	 * 2、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1
	 *  @param strIp  * @return  
	 */
	public static long ipToLong(String ip) {
		if (ip == null || ip.length() == 0) {
			return 0L;
		}
		String[] arr = ip.split("\\.");
		return (Long.parseLong(arr[0]) << 24) + (Long.parseLong(arr[1]) << 16) + (Long.parseLong(arr[2]) << 8) + Long.parseLong(arr[3]);
	}

	/**
	 *  * 将十进制整数形式转换成127.0.0.1形式的ip地址  * 将整数形式的IP地址转化成字符串的方法如下：
	 *  * 1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。
	 *  * 2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。
	 *  * 3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。
	 *  * 4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP。  * @param longIp  * @return  
	 */
	public static String longToIp(long ip) {
		StringBuffer sb = new StringBuffer("");
		//  直接右移24位
		sb.append(String.valueOf((ip >>> 24)));
		sb.append(".");
		//  将高8位置0，然后右移16位
		sb.append(String.valueOf((ip & 0x00FFFFFF) >>> 16));
		sb.append(".");
		//  将高16位置0，然后右移8位
		sb.append(String.valueOf((ip & 0x0000FFFF) >>> 8));
		sb.append(".");
		//  将高24位置0
		sb.append(String.valueOf((ip & 0x000000FF)));
		return sb.toString();
	}

	/**
	 * 获取本机内网ip(linux环境下生效，window返回127.0.0.1)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getLocalIp() throws Exception {
		Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
			Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					return ip.getHostAddress();
				}
			}
		}
		return "";
	}
}
