package org.jack.com.service;
 
import org.jack.com.helper.DatabaseHelper;
import org.jack.com.model.Customer;
import org.jack.com.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.sql.*;
import java.util.*;
 
/**
 * Created by jack on 2015/11/29.
 * 提供客户数据服务
 */
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
 
    /*
    * 获取客户列表
    * */
    public List<Customer> getCustomerList(){
        //to do something
        String sql="select * from customer";
        return DatabaseHelper.queryEntityList(Customer.class,sql);
 
    }
 
    /*
    * 获取客户
    * */
    public  Customer getCustomer(long id){
        //to do something
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }
 
    /*
    * 创建客户
    * */
    public boolean createCustomer(Map<String,Object> fieldMap){
        //to do something
 
        return DatabaseHelper.insertEntity(Customer.class,fieldMap);
    }
 
    /*
    * 更新客户
    * */
    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        //to do something
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }
 
    /*
    * 删除客户
    * */
    public boolean deleteCustomer(long id){
        //to do something
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }
 
   }
