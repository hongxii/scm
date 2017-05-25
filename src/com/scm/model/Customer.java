package com.scm.model;

/**
 * Created by 郭老师 on 2017/5/2.
 */
public class Customer {

    private String customerCode;    //客户编号  -主键
    private String name;            //名称
    private String password;        //密码    ==前三个不能为空
    private String contactor;       //联系人
    private String address;         //地址
    private String postcode;        //邮编
    private String tel;             //电话
    private String fax;             //传真
    private String createDate;      //创建日期

    public Customer(){}

    public Customer(String customerCode, String name, String password) {
        this.customerCode = customerCode;
        this.name = name;
        this.password = password;
    }

    public Customer(String customerCode, String name, String contactor, String address, String postcode, String tel, String fax, String createDate) {
        this.customerCode = customerCode;
        this.name = name;
        this.password = password;
        this.contactor = contactor;
        this.address = address;
        this.postcode = postcode;
        this.tel = tel;
        this.fax = fax;
        this.createDate = createDate;
    }

    public Customer(String customerCode, String name, String password, String contactor, String address, String postcode, String tel, String fax, String createDate) {
        this.customerCode = customerCode;
        this.name = name;
        this.password = password;
        this.contactor = contactor;
        this.address = address;
        this.postcode = postcode;
        this.tel = tel;
        this.fax = fax;
        this.createDate = createDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
