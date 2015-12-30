package com.loongSmart4j.chapter.service;

import com.loongSmart4j.chapter.helper.DatabaseHelper;
import com.loongSmart4j.chapter.model.Customer;
import com.loongSmart4j.chapter.util.PropsUtil;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import jdk.nashorn.internal.ir.ReturnNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Loong on 2015/12/29.
 * 提供客户数据服务
 */
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList(String keyword) {
        String sql = "select * from customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        //TODO
        return null;
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        //TODO
        return false;
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        //TODO
        return false;
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        //TODO
        return false;
    }
}
