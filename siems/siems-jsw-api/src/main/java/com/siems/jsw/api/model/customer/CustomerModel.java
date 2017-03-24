package com.siems.jsw.api.model.customer;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 客户Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CustomerModel extends BaseModel  {

    /**
     * 客户名称
     */
    @Validate
    private String customerNo;

    /**
     * 客户名称
     */
    @Validate
    private String customerName;

    /**
     * 密码
     */
    @Validate
    private String passWord;

    /**
     * 公司名称
     */
    @Validate
    private String companyName;

    /**
     * 客户邮箱
     */
    @Validate
    private String email;

    /**
     * 联系电话
     */
    @Validate
    private String contact;

    /**
     * 省份
     */
    @Validate
    private String province;

    /**
     * 城市
     */
    @Validate
    private String city;

    /**
     * 县区
     */
    @Validate
    private String county;

    /**
     * 详细地址
     */
    @Validate
    private String address;

    /**
     * 账号类型：1-系统管理员，2-超级管理员
     */
    @Validate
    private String type;

    private static final long serialVersionUID = 1L;

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

}
