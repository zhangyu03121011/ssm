package com.mornsun.jsw.api.model.search.record;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 搜索记录Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SearchRecordModel extends BaseModel {

    /**
     * 关键字
     */
    @Validate
    private String keyword;

    /**
     * 搜索数量
     */
    @Validate
    private Integer resultCount;

    private static final long serialVersionUID = 1L;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

}
