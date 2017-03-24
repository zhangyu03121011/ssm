package com.mornsun.crm.manager.controller.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.controller.BasePageHelperApiServiceController;
import com.common.util.ExceptionUtil;
import com.mornsun.crm.core.service.product.IProductService;
import com.mornsun.jsw.api.vo.product.atta.ProductAttaVo;
import com.mornsun.jsw.api.vo.product.base.ProductBaseVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.product.param.ProductParamVo;
import com.mornsun.jsw.api.vo.product.replace.ProductReplaceVo;

/**
 * 产品Controller
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BasePageHelperApiServiceController<ProductVo, IProductService> {

	@Autowired
	private IProductService productService;

	/**
	 * 查询产品基本分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/base/pagination")
	public PageListVo<ProductBaseVo> pagination(ProductBaseVo obj, PageListVo<ProductBaseVo> page,
			HttpServletRequest request) {
		PageListVo<ProductBaseVo> resultVo = new PageListVo<>();
		try {
			resultVo = productService.pagination(obj, page, request);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询产品替换分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/replace/pagination")
	public PageListVo<ProductReplaceVo> pagination(ProductReplaceVo obj, PageListVo<ProductReplaceVo> page,
			HttpServletRequest request) {
		PageListVo<ProductReplaceVo> resultVo = new PageListVo<>();
		try {
			resultVo = productService.pagination(obj, page, request);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询产品附件分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/atta/pagination")
	public PageListVo<ProductAttaVo> pagination(ProductAttaVo obj, PageListVo<ProductAttaVo> page,
			HttpServletRequest request) {
		PageListVo<ProductAttaVo> resultVo = new PageListVo<>();
		try {
			resultVo = productService.pagination(obj, page, request);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询产品参数分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/param/pagination")
	public PageListVo<ProductParamVo> pagination(ProductParamVo obj, PageListVo<ProductParamVo> page,
			HttpServletRequest request) {
		PageListVo<ProductParamVo> resultVo = new PageListVo<>();
		try {
			resultVo = productService.pagination(obj, page, request);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 修改产品基本
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/base/update")
	public ResultVo<ProductBaseVo> updateBase(ProductBaseVo obj) {
		ResultVo<ProductBaseVo> resultVo = new ResultVo<>();
		try {
			resultVo = productService.updateBase(obj);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 修改产品替换
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/replace/update")
	public ResultVo<ProductReplaceVo> updateReplace(ProductReplaceVo obj) {
		ResultVo<ProductReplaceVo> resultVo = new ResultVo<>();
		try {
			resultVo = productService.updateReplace(obj);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 修改产品附件
	 * 
	 * @param obj
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/atta/update")
	public ResultVo<ProductAttaVo> updateAtta(ProductAttaVo obj) {
		ResultVo<ProductAttaVo> resultVo = new ResultVo<>();
		try {
			resultVo = productService.updateAtta(obj);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 修改产品参数
	 * 
	 * @param obj
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/param/update")
	public ResultVo<ProductParamVo> updateParam(ProductParamVo obj) {
		ResultVo<ProductParamVo> resultVo = new ResultVo<>();
		try {
			resultVo = productService.updateParam(obj);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 导入产品基本Excel文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/base/import")
	@ResponseBody
	public ResultVo<ProductVo> importBaseExcelFile(HttpServletRequest request) {
		ResultVo<ProductVo> resultVo = new ResultVo<>();
		try {
			resultVo = productService.importBaseExcelFile(request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 导入产品替换Excel文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/replace/import")
	@ResponseBody
	public ResultVo<ProductVo> importReplaceExcelFile(HttpServletRequest request) {
		ResultVo<ProductVo> resultVo = new ResultVo<>();
		try {
			resultVo = productService.importReplaceExcelFile(request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 导入产品附件Excel文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/atta/import")
	@ResponseBody
	public ResultVo<ProductVo> importAttaExcelFile(HttpServletRequest request) {
		ResultVo<ProductVo> resultVo = new ResultVo<>();
		try {
			resultVo = productService.importAttaExcelFile(request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

}
