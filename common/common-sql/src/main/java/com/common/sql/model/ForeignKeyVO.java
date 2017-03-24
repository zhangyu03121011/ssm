package com.common.sql.model;

import java.io.Serializable;

/**
 * 外键信息
 * 
 * @author 枫之叶
 * 
 */
public class ForeignKeyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 被导入的主键表名称
     */
    private String pkTableName;

    /**
     * 被导入的主键列名称
     */
    private String pkColumnName;

    /**
     * 外键表名称
     */
    private String fkTableName;

    /**
     * 外键列名称
     */
    private String fkColumnName;

    /**
     * 外键中的序列号
     */
    private String fkIndex;

    /**
     * 外键的名称
     */
    private String fkName;

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    public String getFkTableName() {
        return fkTableName;
    }

    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    public String getFkIndex() {
        return fkIndex;
    }

    public void setFkIndex(String fkIndex) {
        this.fkIndex = fkIndex;
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

}
