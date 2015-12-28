package com.loongSmart4j.chapter.service;

import com.loongSmart4j.chapter.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Loong on 2015/12/29.
 * CustomerService 单元测试
 */
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init() throws Exception {

    }

    @Test
    public void testGetCustomerList() throws Exception {
        List<Customer> customerList = customerService.getCustomerList("");
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void testGetCustomer() throws Exception {
        long id=1;
        Customer customer=new CustomerService().getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Map<String,Object> fieldMap=new HashMap<String, Object>();
        fieldMap.put("name","customer100");
        fieldMap.put("contact","John");
        fieldMap.put("telephone","18474838849");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        long id=1;
        Map<String,Object> fieldMap=new HashMap<String, Object>();
        fieldMap.put("contact","Eric");
        boolean result = customerService.updateCustomer(id,fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        long id=1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}