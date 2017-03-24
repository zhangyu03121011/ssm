package com.common.sql.model;

import java.io.Serializable;

/**
 * 列的结果类
 * 
 * @author 枫之叶
 * 
 */
public class ResultColumnVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 列的名称
     */
    private String columnName;

    /**
     * 列的值
     */
    private Object columnValue;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * catalog名称
     */
    private String catalogName;

    /**
     * 列的java类型
     */
    private String columnClassName;

    /**
     * 总列数
     */
    private int columnCount;

    /**
     * 列长度大小
     */
    private int columnSize;

    /**
     * 列类型
     */
    private int columnType;

    /**
     * 列类型名称
     */
    private String columnTypeName;

    /**
     * Schema名称
     */
    private String schemaName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(Object columnValue) {
        this.columnValue = columnValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

}
