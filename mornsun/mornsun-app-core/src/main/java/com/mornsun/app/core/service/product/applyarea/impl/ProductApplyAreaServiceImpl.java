package com.mornsun.app.core.service.product.applyarea.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.product.applyarea.IProductApplyAreaApi;
import com.mornsun.app.core.service.product.applyarea.IProductApplyAreaService;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;

/**
 * 产品应用领域Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductApplyAreaServiceImpl extends BasePageHelperApiServiceImpl<ProductApplyAreaVo,IProductApplyAreaApi>  implements IProductApplyAreaService {

}
