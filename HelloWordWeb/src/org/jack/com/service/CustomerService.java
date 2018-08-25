package org.jack.com.service;
 
import org.jack.com.model.Customer;
import org.jack.com.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.sql.*;
import java.util.*;
 
/**
 * config.properties
 * Created by jack on 2015/11/29.
 * 提供客户数据服务
 */
public class CustomerService {
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    static {
    	String str=System.getProperty("user.dir")+"\\src\\config\\config.properties";
        Properties conf= PropsUtil.loadProps(str);
        DRIVER=conf.getProperty("jdbc.driver");
        URL=conf.getProperty("jdbc.url");
        USERNAME= conf.getProperty("jdbc.username");
        PASSWORD=conf.getProperty("jdbc.password");
        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
        	LOGGER.error("can not load jdbc driver",e);
        }
    }
    
    /*
    * 获取客户列表
    * */
    public List<Customer> getCustomerList(String keyword){
        //to do something
        Connection conn=null;
        try{
            List<Customer> customerList=new ArrayList<Customer>();
            String sql="select * from customer";
            conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Customer customer=new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
            return customerList;
        }catch (SQLException e){
        	LOGGER.error("execute sql failure",e);
        }finally {
            if (conn !=null){
                try {
                    conn.close();
                }catch (SQLException e){
                  LOGGER.error("close connection failure",e);
                }
            }
        }
        return  null;
    }
 
    /*
    * 获取客户
    * */
    public  Customer getCustomer(long id){
        //to do something
        return null;
    }
 
    /*
    * 创建客户
    * */
    public boolean createCustomer(Map<String,Object> fieldMap){
        //to do something
        return false;
    }
 
    /*
    * 更新客户
    * */
    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        //to do something
        return false;
    }
 
    /*
    * 删除客户
    * */
    public boolean deleteCustomer(long id){
        //to do something
        return false;
    }
 
   }
