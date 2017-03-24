package com.mornsun.crm.core.service.brand.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.brand.IBrandService;
import com.mornsun.jsw.api.api.brand.IBrandApi;
import com.mornsun.jsw.api.vo.brand.BrandVo;

/**
 * 品牌Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class BrandServiceImpl extends BasePageHelperApiServiceImpl<BrandVo, IBrandApi>
		implements IBrandService {

}
