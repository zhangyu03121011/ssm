package com.mornsun.crm.manager.controller.company.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.crm.core.service.company.base.ICompanyService;
import com.mornsun.jsw.api.vo.company.base.CompanyVo;

/**
 * 公司Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BasePageHelperApiServiceController<CompanyVo, ICompanyService> {

}
