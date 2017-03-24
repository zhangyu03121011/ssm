package com.siems.jsw.api.model.shop;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 门店Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ShopModel extends BaseModel  {

    /**
     * 客户公司ID
     */
    @Validate
    private String customerId;

    /**
     * 用户名
     */
    @Validate
    private String email;

    /**
     * 密码
     */
    @Validate
    private String passWord;

    /**
     * 门店名称
     */
    @Validate
    private String shopName;

    /**
     * 门店类别：1-专营店，2-直营店
     */
    @Validate
    private String shopType;

    /**
     * 所属部门
     */
    @Validate
    private String deptId;

    /**
     * 门店负责人
     */
    @Validate
    private String shopOwner;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner == null ? null : shopOwner.trim();
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
