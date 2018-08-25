package org.jack.com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jack on 2015/11/29.
 * 创建客户
 */
@WebServlet("customer_create")
public class CustomerCreateServlet extends HttpServlet {
	
	
    /*
    * 进入创建客户界面
    * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //to do something
    }
    /*
      * 处理  创建客户 请求
      * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        // to do something 
    }
}