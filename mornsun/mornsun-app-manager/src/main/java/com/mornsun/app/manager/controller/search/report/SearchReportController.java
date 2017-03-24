package com.mornsun.app.manager.controller.search.report;

import com.mornsun.app.core.service.search.report.ISearchReportService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.search.report.SearchReportVo;

/**
 * 搜索统计Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/search/report")
public class SearchReportController extends BasePageHelperApiServiceController<SearchReportVo,ISearchReportService> {

}
