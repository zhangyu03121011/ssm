package com.mornsun.app.core.service.product.tag.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.app.core.service.product.tag.IProductTagService;
import com.mornsun.jsw.api.api.product.tag.IProductTagApi;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;

/**
 * 产品标签Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductTagServiceImpl extends BasePageHelperApiServiceImpl<ProductTagVo,IProductTagApi>  implements IProductTagService {

}
