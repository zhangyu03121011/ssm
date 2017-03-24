package com.mornsun.app.core.service.search.report.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.search.report.ISearchReportApi;
import com.mornsun.app.core.service.search.report.ISearchReportService;
import com.mornsun.jsw.api.vo.search.report.SearchReportVo;

/**
 * 搜索统计Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SearchReportServiceImpl extends BasePageHelperApiServiceImpl<SearchReportVo,ISearchReportApi>  implements ISearchReportService {

}
