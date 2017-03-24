package com.mornsun.crm.manager.controller.catalog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;
import com.mornsun.crm.core.service.catalog.ICatalogService;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;

/**
 * 分类Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController extends BasePageHelperApiServiceController<CatalogVo, ICatalogService> {

	@Autowired
	private ICatalogService catalogService;

	/**
	 * 导入分类Excel文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/import")
	@ResponseBody
	public ResultVo<CatalogVo> importExcelFile(HttpServletRequest request) {
		ResultVo<CatalogVo> resultVo = new ResultVo<>();
		try {
			resultVo = catalogService.importExcelFile(request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

}
