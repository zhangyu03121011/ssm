package com.common.sql.model;

import java.io.Serializable;

/**
 * 列信息
 * 
 * @author 枫之叶
 * 
 */
public class ColumnVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 数据类型,java.sql.Types 的 SQL 类型
     */
    private String dataType;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 列的大小。对于 char 或 date 类型，列的大小是最大字符数，对于 numeric 和 decimal // 类型，列的大小就是精度。
     */
    private String columnSize;

    /**
     * 是否允许使用 NULL。 0 - 可能不允许使用 NULL 值 1 - 明确允许使用 NULL 值 2 - 不知道是否可使用 null
     */
    private String isNull;

    /**
     * 描述列的注释
     */
    private String remarks;

    /**
     * 精度
     */
    private int decimalDigits;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是否自动增长
     */
    private String isAutoincrement;

    /**
     * 表中的列的索引（从 1 开始）
     */
    private int columnIndex;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getIsAutoincrement() {
        return isAutoincrement;
    }

    public void setIsAutoincrement(String isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }

}
