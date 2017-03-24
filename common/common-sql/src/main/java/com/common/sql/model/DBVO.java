package com.common.sql.model;

import java.io.Serializable;

/**
 * 数据库信息
 * 
 * @author 枫之叶
 * 
 */
public class DBVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据库版本
     */
    private String dbVersion;

    /**
     * 数据库驱动版本
     */
    private String dbDriverVersion;

    /**
     * 数据库URL
     */
    private String dbUrl;

    /**
     * 数据库当前用户名
     */
    private String dbUserName;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

    public String getDbDriverVersion() {
        return dbDriverVersion;
    }

    public void setDbDriverVersion(String dbDriverVersion) {
        this.dbDriverVersion = dbDriverVersion;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

}
