package com.mornsun.crm.manager.controller.vendor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.mornsun.crm.core.service.vendor.IVendorService;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

/**
 * 厂家Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/vendor")
public class VendorController extends BasePageHelperApiServiceController<CompanyCourseVo, IVendorService> {

}
