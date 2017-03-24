package com.mornsun.crm.core.service.atta;

import javax.servlet.http.HttpServletRequest;

import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.ResultVo;
import com.mornsun.jsw.api.vo.atta.AttaVo;

/**
 * 附件Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IAttaService {

	/**
	 * 文件上传
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<BaseAttaModel> upload(String id,String type, HttpServletRequest request) throws Exception;
	
	/**
	 * 文件上传
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<BaseAttaModel> importAtta(String type, HttpServletRequest request) throws Exception;

	/**
	 * 文件查询
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<AttaVo> detail(String id, HttpServletRequest request) throws Exception;
}
