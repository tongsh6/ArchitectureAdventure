package com.loongSmart4j.chapter.model;

/**
 * Created by Loong on 2015/12/29.
 * 客户类
 */
public class Customer {
    /**
     * ID
     */
    private long id;

    /**
     * 客户名称
     */
    private String name;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 备注
     */
    private String remark;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
