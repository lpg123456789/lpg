package org.jack.test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.jack.com.helper.DatabaseHelper;
import org.jack.com.model.Customer;
import org.jack.com.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jack on 2015/12/5.
 * 单元测试类CustomerServiceTest
 */
public class CustomerServiceTest {
    private final CustomerService customerService;
    public CustomerServiceTest(){
        customerService = new CustomerService();
    }
    
    static {
    	String projectTest=System.getProperty("user.dir")+"\\src\\config\\log4j.properties";
		File file = new File(projectTest);
		if (file.exists()) {
			PropertyConfigurator.configure(projectTest);
		}	
    }
 
    @Before
    public void init() throws  Exception{
        //to do 初始化数据库
        //DatabaseHelper.executeSqlFile("sql/customer_init.sql");
    }
 
    @Test
    public void getCustomerListTest() throws Exception{
    	//这个是绿色的
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2,customerList.size());
    }
 
    @Test
    public void getCustomerTest() throws Exception{
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }
 
    @Test
    public void createCustomerTest() throws Exception{
        Map<String,Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name","customer100");
        fieldMap.put("contact","John");
        fieldMap.put("telephone","13512345678");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }
 
    @Test
    public void updateCustomerTest() throws  Exception{
        long id = 1;
        Map<String,Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("contact","Eric");
        boolean result = customerService.updateCustomer(1,fieldMap);
        Assert.assertTrue(result);
    }
 
    @Test
    public void deleteCustomerTest() throws Exception{
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
