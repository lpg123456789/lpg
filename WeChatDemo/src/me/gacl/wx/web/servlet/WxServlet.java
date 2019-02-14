package me.gacl.wx.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.gacl.wx.util.MessageHandlerUtil;

@WebServlet(urlPatterns="/WxServlet")
public class WxServlet extends HttpServlet {

	/**
	 * Token���ɿ����߿���������д����������ǩ������Token��ͽӿ�URL�а�����Token���бȶԣ��Ӷ���֤��ȫ�ԣ�
	 * ���������ҽ�Token����Ϊgacl
	 */
	private final String TOKEN = "gacl";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("��ʼУ��ǩ��");
		/**
		 * ����΢�ŷ�������������ʱ���ݹ�����4������
		 */
		String signature = request.getParameter("signature");// ΢�ż���ǩ��signature����˿�������д��token�����������е�timestamp������nonce������
		String timestamp = request.getParameter("timestamp");// ʱ���
		String nonce = request.getParameter("nonce");// �����
		String echostr = request.getParameter("echostr");// ����ַ���

		System.out.println("����΢�ŵ�ֵ111  " + signature + " " + timestamp + " " + nonce + " " + echostr);

		response.getWriter().println(echostr);
		//response.getWriter().write(echostr);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ���ա�������Ӧ��΢�ŷ�����ת�����û����͸������ʺŵ���Ϣ
        // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("�������");
        String result = "";
        try {
            Map<String,String> map = MessageHandlerUtil.parseXml(request);
            System.out.println("��ʼ������Ϣ");
            result = MessageHandlerUtil.buildXml(map);
            System.out.println(result);
            if(result.equals("")){
                result = "δ��ȷ��Ӧ";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("�����쳣��"+ e.getMessage());
        }
        response.getWriter().println(result);
	}

	
}
