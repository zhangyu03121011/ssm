package com.mornsun.app.manager.controller.user.course;

import com.mornsun.app.core.service.user.course.IUserCourseService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;

/**
 * 用户秒懂Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/course")
public class UserCourseController extends BasePageHelperApiServiceController<UserCourseVo,IUserCourseService> {

}
