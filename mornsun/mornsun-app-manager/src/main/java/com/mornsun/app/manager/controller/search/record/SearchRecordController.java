package com.mornsun.app.manager.controller.search.record;

import com.mornsun.app.core.service.search.record.ISearchRecordService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.search.record.SearchRecordVo;

/**
 * 搜索记录Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/search/record")
public class SearchRecordController extends BasePageHelperApiServiceController<SearchRecordVo,ISearchRecordService> {

}
