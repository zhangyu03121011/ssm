package com.mornsun.jsw.core.service.search.record.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.mornsun.jsw.api.vo.search.record.SearchRecordVo;
import com.mornsun.jsw.core.dao.search.record.ISearchRecordDao;
import com.mornsun.jsw.core.service.search.record.ISearchRecordService;

/**
 * 搜索记录Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SearchRecordServiceImpl extends BasePageHelperServiceImpl<SearchRecordVo,ISearchRecordDao>  implements ISearchRecordService {

}
