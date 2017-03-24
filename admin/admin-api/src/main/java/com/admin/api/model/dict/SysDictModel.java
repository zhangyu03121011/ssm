package com.admin.api.model.dict;

import com.common.base.annotation.Primary;
import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 数据字典Model
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SysDictModel extends BaseModel {
    
    private static final long serialVersionUID = -2486924817169396671L;

    /**
     * 字典ID
     */
    @Primary
    private String dictId;

    /**
     * 父级ID
     */
    @Validate
    private String parentId;

    /**
     * 名称
     */
    @Validate
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}