package com.mornsun.app.manager.controller.company.course;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mornsun.app.core.service.company.course.ICompanyCourseService;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

/**
 * 企业秒懂Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/company/course")
public class CompanyCourseController
		extends BasePageHelperApiServiceController<CompanyCourseVo, ICompanyCourseService> {

	@Autowired
	private ICompanyCourseService companyCourseService;

	/**
	 * 阅读企业秒懂信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/read")
	@ResponseBody
	public ResultVo<CompanyCourseVo> read(CompanyCourseVo vo, HttpServletRequest request) {
		ResultVo<CompanyCourseVo> resultVo = new ResultVo<>();
		try {
			resultVo = companyCourseService.read(vo, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}
}
