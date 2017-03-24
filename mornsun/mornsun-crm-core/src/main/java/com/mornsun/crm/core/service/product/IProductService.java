package com.mornsun.crm.core.service.product;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperApiService;
import com.mornsun.jsw.api.vo.product.atta.ProductAttaVo;
import com.mornsun.jsw.api.vo.product.base.ProductBaseVo;
import com.mornsun.jsw.api.vo.product.base.ProductVo;
import com.mornsun.jsw.api.vo.product.param.ProductParamVo;
import com.mornsun.jsw.api.vo.product.replace.ProductReplaceVo;

/**
 * 产品Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IProductService extends IBasePageHelperApiService<ProductVo> {

	/**
	 * 修改产品基本
	 * 
	 * @param obj
	 * @return
	 */
	public ResultVo<ProductBaseVo> updateBase(ProductBaseVo obj);

	/**
	 * 修改产品替换
	 * 
	 * @param obj
	 * @return
	 */
	public ResultVo<ProductReplaceVo> updateReplace(ProductReplaceVo obj);

	/**
	 * 修改产品附件
	 * 
	 * @param obj
	 * @return
	 */
	public ResultVo<ProductAttaVo> updateAtta(ProductAttaVo obj);

	/**
	 * 修改产品参数
	 * 
	 * @param obj
	 * @return
	 */
	public ResultVo<ProductParamVo> updateParam(ProductParamVo obj);

	/**
	 * 查询产品基本分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductBaseVo> pagination(ProductBaseVo obj, PageListVo<ProductBaseVo> page,
			HttpServletRequest request);

	/**
	 * 查询产品替换分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductReplaceVo> pagination(ProductReplaceVo obj, PageListVo<ProductReplaceVo> page,
			HttpServletRequest request);

	/**
	 * 查询产品附件分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductAttaVo> pagination(ProductAttaVo obj, PageListVo<ProductAttaVo> page,
			HttpServletRequest request);

	/**
	 * 查询产品参数分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<ProductParamVo> pagination(ProductParamVo obj, PageListVo<ProductParamVo> page,
			HttpServletRequest request);

	/**
	 * 导入产品基本信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<ProductVo> importBaseExcelFile(HttpServletRequest request) throws Exception;

	/**
	 * 导入产品替换信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<ProductVo> importReplaceExcelFile(HttpServletRequest request) throws Exception;

	/**
	 * 导入产品附件信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<ProductVo> importAttaExcelFile(HttpServletRequest request) throws Exception;
}
