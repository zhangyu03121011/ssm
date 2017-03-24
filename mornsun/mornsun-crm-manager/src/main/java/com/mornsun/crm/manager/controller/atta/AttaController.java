package com.mornsun.crm.manager.controller.atta;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.constant.BaseConstant;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.ResultVo;
import com.mornsun.crm.core.service.atta.IAttaService;
import com.mornsun.jsw.api.vo.atta.AttaVo;

/**
 * 附件Controller
 * 
 * @author: HuiJunLuo
 * @date:2016年4月8日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/atta")
public class AttaController implements BaseConstant {

	private final static Logger logger = Logger.getLogger(AttaController.class);

	@Autowired
	private IAttaService attaService;

	/**
	 * 文件上传
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public ResultVo<BaseAttaModel> upload(String id, String type, HttpServletRequest request) {
		ResultVo<BaseAttaModel> resultVo = new ResultVo<>();
		try {
			resultVo = attaService.upload(id, type, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			logger.error(e.getMessage(), e);
		}
		return resultVo;
	}

	/**
	 * 文件上传
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/import")
	public ResultVo<BaseAttaModel> importAtta(String type, HttpServletRequest request) {
		ResultVo<BaseAttaModel> resultVo = new ResultVo<>();
		try {
			resultVo = attaService.importAtta(type, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			logger.error(e.getMessage(), e);
		}
		return resultVo;
	}

	/**
	 * 文件查询
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/detail")
	public ResultVo<AttaVo> detail(String id, HttpServletRequest request) {
		ResultVo<AttaVo> resultVo = new ResultVo<>();
		try {
			resultVo = attaService.detail(id, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			logger.error(e.getMessage(), e);
		}
		return resultVo;
	}
}
