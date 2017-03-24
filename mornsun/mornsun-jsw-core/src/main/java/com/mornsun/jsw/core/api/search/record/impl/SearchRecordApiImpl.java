package com.mornsun.jsw.core.api.search.record.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.mornsun.jsw.api.api.search.record.ISearchRecordApi;
import com.mornsun.jsw.api.vo.search.record.SearchRecordVo;
import com.mornsun.jsw.core.service.search.record.ISearchRecordService;

/**
 * 搜索记录Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SearchRecordApiImpl extends BasePageHelperProviderApiImpl<SearchRecordVo,ISearchRecordService>  implements ISearchRecordApi {

}
