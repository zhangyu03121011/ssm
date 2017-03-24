package com.mornsun.crm.manager.controller.company.course;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;
import com.mornsun.crm.core.service.company.course.ICompanyCourseService;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

/**
 * 公司秒懂Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/company/course")
public class CompanyCourseController extends BasePageHelperApiServiceController<CompanyCourseVo, ICompanyCourseService> {

	@Autowired
	private ICompanyCourseService companyCourseService;

	/**
	 * 导入公司秒懂Excel文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/import")
	@ResponseBody
	public ResultVo<CompanyCourseVo> importExcelFile(HttpServletRequest request) {
		ResultVo<CompanyCourseVo> resultVo = new ResultVo<>();
		try {
			resultVo = companyCourseService.importExcelFile(request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

}
