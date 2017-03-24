package com.mornsun.app.core.service.user.catalog.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.user.catalog.IUserCatalogApi;
import com.mornsun.app.core.service.user.catalog.IUserCatalogService;
import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;

/**
 * 用户分类关系Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserCatalogServiceImpl extends BasePageHelperApiServiceImpl<UserCatalogVo, IUserCatalogApi>
		implements IUserCatalogService {

}
