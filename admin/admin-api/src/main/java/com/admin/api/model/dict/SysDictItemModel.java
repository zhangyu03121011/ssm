package com.admin.api.model.dict;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 数据字典明细Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysDictItemModel extends BaseModel {
    
    private static final long serialVersionUID = 4987844413256895342L;

    /**
     * 字典明细ID
     */
    @Primary
    private String dictItemId;

    /**
     * 字典ID
     */
    @Validate
    private String dictId;

    /**
     * 名称
     */
    @Validate
    private String name;

    /**
     * 值
     */
    @Validate
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    public String getDictItemId() {
        return dictItemId;
    }

    public void setDictItemId(String dictItemId) {
        this.dictItemId = dictItemId == null ? null : dictItemId.trim();
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}