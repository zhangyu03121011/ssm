package com.mornsun.jsw.api.model.catalog;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 分类Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CatalogModel extends BaseModel  {

    /**
     * 分类名称
     */
    @Validate
    private String catalogName;

    /**
     * 父级ID
     */
    @Validate
    private String parentId;

    private static final long serialVersionUID = 1L;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName == null ? null : catalogName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

}
