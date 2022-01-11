package com.lpg.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.lpg.json.JsonUtil;


/**
 * http请求工具
 * 
 * @author zhangzhen
 * @date 2017年8月15日
 * @version 1.0
 */
public class ServletUtil {

	private static Logger log = Logger.getLogger(ServletUtil.class);

	/**
	 * http请求
	 * 
	 * @param url
	 *            地址
	 * @param methName
	 *            接口名
	 * @param parame
	 *            参数
	 */
	public static String httpPostSendJson(String url, String methName,
			HashMap<String, Object> parames) {
		InputStreamReader in = null;
		BufferedOutputStream out = null;
		try {
			String result;
			HttpURLConnection connection = null;
			URL urls = new URL(url + methName);
			connection = (HttpURLConnection) urls.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/json:charset=utf-8");
			out = new BufferedOutputStream(connection.getOutputStream());
			StringBuffer buf = new StringBuffer();
			buf.append(JsonUtil.toJson(parames));
			out.write(buf.toString().getBytes());
			out.flush();
			int response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {

				in = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				return result;
			} else {
				log.error("http code:" + response_code);
				in = new InputStreamReader(connection.getErrorStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				log.error(result);
			}
			return null;
		} catch (Exception e) {
			log.error("发送异常！", e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            地址
	 * @param methName
	 *            接口名
	 * @param parame
	 *            参数
	 */
	public static String httpPostSend(String url, String methName,
			List<Parames> parames) {
		InputStreamReader in = null;
		BufferedOutputStream out = null;
		try {
			String result;
			Iterator<Parames> entryItr = parames.iterator();
			HttpURLConnection connection = null;
			URL urls = new URL(url + methName);
			connection = (HttpURLConnection) urls.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			out = new BufferedOutputStream(connection.getOutputStream());
			StringBuffer buf = new StringBuffer();
			while (entryItr.hasNext()) {
				Parames en = entryItr.next();
				buf.append(URLEncoder.encode(en.getCanshuName(), "utf-8"));
				buf.append(("=" + URLEncoder.encode(en.getCanshuValue(),
						"utf-8")));
				if (entryItr.hasNext()) {
					buf.append("&");
				}
			}
			out.write(buf.toString().getBytes());
			// System.out.println("请求参数为:" + buf.toString());
			out.flush();
			int response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {
				in = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				return result;
			} else {
				log.error("http code:" + response_code);
				in = new InputStreamReader(connection.getErrorStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				log.error(result);
			}
			return null;
		} catch (SocketTimeoutException socketTimeOut) {
			log.error("content out！", socketTimeOut);
			return null;
		} catch (Exception e) {
			log.error("发送异常！", e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * http请求
	 * 
	 * @param url
	 *            地址
	 * @param methName
	 *            接口名
	 * @param parame
	 *            参数
	 */
	public static String httpPostSend(String url, String methName, String data) {
		InputStreamReader in = null;
		BufferedOutputStream out = null;
		try {
			String result;
			HttpURLConnection connection = null;
			URL urls = new URL(url + methName);
			connection = (HttpURLConnection) urls.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			out = new BufferedOutputStream(connection.getOutputStream());

			out.write(data.toString().getBytes());
			// System.out.println("请求参数为:" + buf.toString());
			out.flush();
			int response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {
				in = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				return result;
			} else {
				log.error("http code:" + response_code);
				in = new InputStreamReader(connection.getErrorStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				log.error(result);
			}
			return null;
		} catch (SocketTimeoutException socketTimeOut) {
			log.error("content out！", socketTimeOut);
			return null;
		} catch (Exception e) {
			log.error("发送异常！", e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * http请求 授权
	 * 
	 * @param url
	 *            地址
	 * @param methName
	 *            接口名
	 * @param parame
	 *            参数
	 */
	public static String httpPostOAuthSend(String url, String methName,
			List<Parames> parames) {
		InputStreamReader in = null;
		BufferedOutputStream out = null;
		try {
			String result;
			Iterator<Parames> entryItr = parames.iterator();
			HttpURLConnection connection = null;
			URL urls = new URL(url + methName);
			connection = (HttpURLConnection) urls.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Authorization", "OAuth");
			out = new BufferedOutputStream(connection.getOutputStream());
			StringBuffer buf = new StringBuffer();
			while (entryItr.hasNext()) {
				Parames en = entryItr.next();
				buf.append(URLEncoder.encode(en.getCanshuName(), "utf-8"));
				buf.append(("=" + URLEncoder.encode(en.getCanshuValue(),
						"utf-8")));
				if (entryItr.hasNext()) {
					buf.append("&");
				}
			}
			out.write(buf.toString().getBytes());
			out.flush();
			int response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {
				in = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				return result;
			} else {
				log.error("http code:" + response_code);
				in = new InputStreamReader(connection.getErrorStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				log.error(result);
			}
			return null;
		} catch (Exception e) {
			log.error("发送异常！", e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * httpget
	 * 
	 * @param url
	 * @param methName
	 * @param parames
	 * @return
	 */
	public static String httpGetSend(String url, String methName,
			List<Parames> parames) {
		InputStreamReader in = null;
		try {
			String result;
			Iterator<Parames> entryItr = parames.iterator();
			HttpURLConnection connection = null;
			StringBuffer buf = new StringBuffer();
			while (entryItr.hasNext()) {
				Parames en = entryItr.next();
				buf.append(en.getCanshuName());
				buf.append(("=" + en.getCanshuValue()));
				if (entryItr.hasNext()) {
					buf.append("&");
				}
			}
			URL urls = new URL(url + methName + "?" + buf.toString());
			connection = (HttpURLConnection) urls.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			int response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {
				in = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				return result;
			} else {
				log.error("http code:" + response_code);
				in = new InputStreamReader(connection.getErrorStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				log.error(result);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送异常！", e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * httpsget
	 * 
	 * @param url
	 * @param methName
	 * @param parames
	 * @return
	 */
	public static String httpsGetSend(String url, String methName,
			List<Parames> parames) {
		InputStreamReader in = null;
		try {
			String result;
			Iterator<Parames> entryItr = parames.iterator();
			HttpsURLConnection connection = null;
			StringBuffer buf = new StringBuffer();
			while (entryItr.hasNext()) {
				Parames en = entryItr.next();
				buf.append(en.getCanshuName());
				buf.append(("=" + en.getCanshuValue()));
				if (entryItr.hasNext()) {
					buf.append("&");
				}
			}
			URL urls = new URL(url + methName + "?" + buf.toString());
			log.error(url + methName + "?" + buf.toString());
			connection = (HttpsURLConnection) urls.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			int response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {

				in = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				return result;
			} else {
				log.error("http code:" + response_code);
				in = new InputStreamReader(connection.getErrorStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				log.error(result);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送异常！", e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * httpget
	 * 
	 * @param url
	 * @param methName
	 * @param parames
	 * @return
	 */
	public static String httpGetSend_OPPO(String url, String methName,
			List<Parames> parames, String data1, String data2) {
		InputStreamReader in = null;
		try {
			String result;
			Iterator<Parames> entryItr = parames.iterator();
			HttpURLConnection connection = null;
			StringBuffer buf = new StringBuffer();
			while (entryItr.hasNext()) {
				Parames en = entryItr.next();
				buf.append(en.getCanshuName());
				buf.append(("=" + en.getCanshuValue()));
				if (entryItr.hasNext()) {
					buf.append("&");
				}
			}
			URL urls = new URL(url + methName + "?" + buf.toString());
			connection = (HttpURLConnection) urls.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.addRequestProperty("param", data1);
			connection.addRequestProperty("oauthSignature", data2);

			int response_code = connection.getResponseCode();
			if (response_code == HttpURLConnection.HTTP_OK) {
				in = new InputStreamReader(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				return result;
			} else {
				log.error("http code:" + response_code);
				in = new InputStreamReader(connection.getErrorStream());
				BufferedReader bufferedReader = new BufferedReader(in);
				StringBuffer strBuffer = new StringBuffer();
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					strBuffer.append(line);
				}
				result = strBuffer.toString();
				log.error(result);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发送异常！", e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
