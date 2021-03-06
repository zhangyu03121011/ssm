package com.mornsun.app.core.service.search.suggest.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.search.suggest.ISearchSuggestApi;
import com.mornsun.app.core.service.search.suggest.ISearchSuggestService;
import com.mornsun.jsw.api.vo.search.suggest.SearchSuggestVo;

/**
 * 搜索记录反馈Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SearchSuggestServiceImpl extends BasePageHelperApiServiceImpl<SearchSuggestVo,ISearchSuggestApi>  implements ISearchSuggestService {

}
