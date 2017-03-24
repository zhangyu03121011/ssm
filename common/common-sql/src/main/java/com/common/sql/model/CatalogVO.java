package com.common.sql.model;

import java.io.Serializable;

/**
 * Catalog信息
 * 
 * @author 枫之叶
 * 
 */
public class CatalogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类别名称（表类别）
     */
    private String catalogName;

    /**
     * 数据库信息
     */
    private DBVO dbvo;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public DBVO getDbvo() {
        return dbvo;
    }

    public void setDbvo(DBVO dbvo) {
        this.dbvo = dbvo;
    }

}
