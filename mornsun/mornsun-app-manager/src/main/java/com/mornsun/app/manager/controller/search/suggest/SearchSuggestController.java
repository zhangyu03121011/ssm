package com.mornsun.app.manager.controller.search.suggest;

import com.mornsun.app.core.service.search.suggest.ISearchSuggestService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.search.suggest.SearchSuggestVo;

/**
 * 搜索记录反馈Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/search/suggest")
public class SearchSuggestController extends BasePageHelperApiServiceController<SearchSuggestVo,ISearchSuggestService> {

}
