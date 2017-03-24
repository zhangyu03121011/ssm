package com.mornsun.jsw.api.model.tag;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 标签Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class TagModel extends BaseModel  {

    /**
     * 分类ID
     */
    @Validate
    private String catalogId;

    /**
     * 标签名称
     */
    @Validate
    private String tagName;

    private static final long serialVersionUID = 1L;

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId == null ? null : catalogId.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

}
