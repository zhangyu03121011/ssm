package com.admin.manager.controller.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.api.vo.notice.SysNoticeVo;
import com.admin.core.service.notice.ISysNoticeService;
import com.common.orm.mybatis.controller.BasePageHelperServiceController;

/**
 * 系统通知Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年2月1日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/notice")
public class SysNoticeController extends BasePageHelperServiceController<SysNoticeVo, ISysNoticeService> {

}
