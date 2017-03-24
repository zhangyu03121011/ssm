package com.mornsun.jsw.core.api.product.base.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.mornsun.jsw.api.api.product.base.IProductSearchApi;
import com.mornsun.jsw.api.vo.product.base.SearchVo;
import com.mornsun.jsw.core.service.product.base.IProductSearchService;

/**
 * 产品搜索Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductSearchApiImpl extends BasePageHelperProviderApiImpl<SearchVo,IProductSearchService>  implements IProductSearchApi {

}
