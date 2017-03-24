package com.common.sql.model;

import java.io.Serializable;
import java.util.List;

/**
 * 表信息
 * 
 * @author 枫之叶
 * 
 */
public class TableVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表的名称
     */
    private String tableName;

    /**
     * 表的主键
     */
    private PrimaryKeyVO primaryKeyVO;

    /**
     * 表的外键
     */
    private List<ForeignKeyVO> foreignKeyVOs;

    /**
     * 表的列信息
     */
    private List<ColumnVO> columnVOs;

    /**
     * 表类型。典型的类型是
     * "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"
     * 、"ALIAS"
     */
    private String tableType;

    /**
     * 表的解释性注释
     */
    private String remarks;

    /**
     * Schema信息
     */
    private SchemaVO schemaVO;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public SchemaVO getSchemaVO() {
        return schemaVO;
    }

    public void setSchemaVO(SchemaVO schemaVO) {
        this.schemaVO = schemaVO;
    }

    public PrimaryKeyVO getPrimaryKeyVO() {
        return primaryKeyVO;
    }

    public void setPrimaryKeyVO(PrimaryKeyVO primaryKeyVO) {
        this.primaryKeyVO = primaryKeyVO;
    }

    public List<ForeignKeyVO> getForeignKeyVOs() {
        return foreignKeyVOs;
    }

    public void setForeignKeyVOs(List<ForeignKeyVO> foreignKeyVOs) {
        this.foreignKeyVOs = foreignKeyVOs;
    }

    public List<ColumnVO> getColumnVOs() {
        return columnVOs;
    }

    public void setColumnVOs(List<ColumnVO> columnVOs) {
        this.columnVOs = columnVOs;
    }

}
