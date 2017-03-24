package com.mornsun.app.manager.controller.company.base;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mornsun.app.core.service.company.base.ICompanyService;
import com.mornsun.jsw.api.vo.company.base.CompanyVo;

/**
 * 企业Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BasePageHelperApiServiceController<CompanyVo, ICompanyService> {

}
