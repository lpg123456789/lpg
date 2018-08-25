package org.jack.com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jack.com.model.Customer;
import org.jack.com.service.CustomerService;

/**
 * Created by jack on 2015/11/29.
 * 创建客户
 */
@WebServlet("customer_create")
public class CustomerCreateServlet extends HttpServlet {
	
	 private CustomerService customerService;
    /*
    * 进入创建客户界面
    * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 //to do something
        List<Customer> customerList=customerService.getCustomerList();
        req.setAttribute("customerList",customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req,resp);
    }
    /*
      * 处理  创建客户 请求
      * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        // to do something 
    }
    
    @Override
    public void init() throws ServletException {
        customerService=new CustomerService();
    }
}