package com.mornsun.app.manager.controller.user.attention;

import com.mornsun.app.core.service.user.attention.IUserAttentionService;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mornsun.jsw.api.vo.user.attention.UserAttentionVo;

/**
 * 用户关注Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/user/attention")
public class UserAttentionController extends BasePageHelperApiServiceController<UserAttentionVo,IUserAttentionService> {

}
