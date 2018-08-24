package com.lpg.javaMail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author lpg
 * @date 2018年8月24日 利用java发邮件，异常情况发邮件处理
 *       https://www.cnblogs.com/hzxy-blog/p/6411678.html
 */
public class Mail {

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		// 不要使用SimpleEmail,会出现乱码问题
		HtmlEmail email = new HtmlEmail();
		try {
			// 这里是SMTP发送服务器的名字：，普通qq号只能是smtp.qq.com ；smtp.exmail.qq.com没测试成功
			email.setHostName("smtp.qq.com");
			// 设置需要鉴权端口
			email.setSmtpPort(465);
			// 开启 SSL 加密
			email.setSSLOnConnect(true);
			// 字符编码集的设置
			email.setCharset("utf-8");
			// 收件人的邮箱
			email.addTo("940942729@qq.com");
			// 发送人的邮箱
			email.setFrom("940942729@qq.com", "（发件人名称）");
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和得到的授权码
			//授权码	https://jingyan.baidu.com/article/90895e0f2af42664ec6b0b14.html	rcoxmyyzvkjabfdd
			email.setAuthentication("940942729@qq.com", "rcoxmyyzvkjabfdd");
			email.setSubject("下午3：00会议室讨论，请准时参加");
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			email.setMsg("邮件内容");
			// 发送
			email.send();

			System.out.println("邮件发送成功!");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("邮件发送失败!");
		}
	}

}
