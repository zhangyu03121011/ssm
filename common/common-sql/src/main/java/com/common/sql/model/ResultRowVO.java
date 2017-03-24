package com.common.sql.model;

import java.io.Serializable;
import java.util.List;

/**
 * 结果集（行的信息）
 * 
 * @author 枫之叶
 * 
 */
public class ResultRowVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 行的编号
     */
    private int id;

    /**
     * 行的列结果集（一行包括许多列值）
     */
    private List<ResultColumnVO> columnList;

    /**
     * 行的数组结果
     */
    private Object[] columnArray;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultColumnVO> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ResultColumnVO> columnList) {
        this.columnList = columnList;
    }

    public Object[] getColumnArray() {
        return columnArray;
    }

    public void setColumnArray(Object[] columnArray) {
        this.columnArray = columnArray;
    }
}
