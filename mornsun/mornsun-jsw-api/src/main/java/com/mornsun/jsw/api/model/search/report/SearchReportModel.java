package com.mornsun.jsw.api.model.search.report;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 搜索统计Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SearchReportModel extends BaseModel  {

    /**
     * 源ID
     */
    @Validate
    private String sourceId;

    /**
     * 源类别
     */
    @Validate
    private String sourceType;

    /**
     * 搜索数量
     */
    @Validate
    private Integer searchCount;

    /**
     * 关键字
     */
    @Validate
    private String keyword;

    private static final long serialVersionUID = 1L;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

}
