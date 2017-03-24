package com.common.sql.model;

import java.io.Serializable;

/**
 * Schema信息
 * 
 * @author 枫之叶
 * 
 */
public class SchemaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模式名称（表模式）
     */
    private String schemaName;

    /**
     * 类别名称
     */
    private CatalogVO catalogVO;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public CatalogVO getCatalogVO() {
        return catalogVO;
    }

    public void setCatalogVO(CatalogVO catalogVO) {
        this.catalogVO = catalogVO;
    }

}
