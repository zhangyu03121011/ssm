package com.mornsun.crm.core.service.company.course;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperApiService;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

/**
 * 企业秒懂Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ICompanyCourseService extends IBasePageHelperApiService<CompanyCourseVo> {

	/**
	 * 导入Excel文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<CompanyCourseVo> importExcelFile(HttpServletRequest request) throws Exception;

}
