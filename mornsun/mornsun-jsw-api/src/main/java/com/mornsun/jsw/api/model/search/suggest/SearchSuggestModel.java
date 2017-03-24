package com.mornsun.jsw.api.model.search.suggest;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 搜索记录反馈Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SearchSuggestModel extends BaseModel  {

    /**
     * 搜索记录ID
     */
    @Validate
    private String searchRecordId;

    /**
     * 应用领域
     */
    @Validate
    private String applyArea;

    /**
     * 邮箱
     */
    @Validate
    private String email;

    /**
     * 关键字
     */
    @Validate
    private String keyword;

    private static final long serialVersionUID = 1L;

    public String getSearchRecordId() {
        return searchRecordId;
    }

    public void setSearchRecordId(String searchRecordId) {
        this.searchRecordId = searchRecordId == null ? null : searchRecordId.trim();
    }

    public String getApplyArea() {
        return applyArea;
    }

    public void setApplyArea(String applyArea) {
        this.applyArea = applyArea == null ? null : applyArea.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

}
