package com.common.sql.model;

import java.io.Serializable;

/**
 * 表的主键信息
 * 
 * @author 枫之叶
 * 
 */
public class PrimaryKeyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 列名
     */
    private String pkColumnName;

    /**
     * 主键中的序列号
     */
    private String pkIndex;

    /**
     * 主键中的名称
     */
    private String pkName;

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getPkIndex() {
        return pkIndex;
    }

    public void setPkIndex(String pkIndex) {
        this.pkIndex = pkIndex;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

}
