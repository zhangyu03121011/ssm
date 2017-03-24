package com.common.sql.model;

import java.io.Serializable;

/**
 * 数据库连接信息
 * 
 * @author 枫之叶
 * 
 */
public class DriverVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库驱动
     */
    private String driverClass;

    /**
     * jdbc连接串
     */
    private String jdbcUrl;

    /**
     * 数据库登录用户名
     */
    private String user;

    /**
     * 数据库登录密码
     */
    private String password;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
