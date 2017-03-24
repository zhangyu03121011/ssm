package com.mornsun.app.manager.controller.user.catalog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.app.core.service.user.catalog.IUserCatalogService;
import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;

/**
 * 用户分类关系Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/catalog")
public class UserCatalogController extends BasePageHelperApiServiceController<UserCatalogVo, IUserCatalogService> {

}
