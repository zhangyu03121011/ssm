package com.mornsun.jsw.api.vo.search.record;

import com.mornsun.jsw.api.model.search.record.SearchRecordModel;

/**
 * 搜索记录Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SearchRecordVo extends SearchRecordModel {

    private static final long serialVersionUID = 1L;

    /**
     * 搜索数量
     */
    private Integer searchCount;

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

}
