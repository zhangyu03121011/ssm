package com.mornsun.jsw.core.service.product.base.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.mornsun.jsw.api.vo.product.base.SearchVo;
import com.mornsun.jsw.core.dao.product.base.IProductSearchDao;
import com.mornsun.jsw.core.service.product.base.IProductSearchService;

/**
 * 产品Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductSearchServiceImpl extends BasePageHelperServiceImpl<SearchVo,IProductSearchDao>  implements IProductSearchService {

}
