package com.mornsun.app.manager.controller.user.footprint;

import com.mornsun.app.core.service.user.footprint.IUserFootprintService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.footprint.UserFootprintVo;

/**
 * 用户足迹Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/footprint")
public class UserFootprintController extends BasePageHelperApiServiceController<UserFootprintVo,IUserFootprintService> {

}
