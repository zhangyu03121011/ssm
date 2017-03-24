package com.mornsun.crm.core.service.vendor.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.service.service.atta.IUploadService;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.crm.api.constant.AttaTypeConstant;
import com.mornsun.crm.core.service.vendor.IVendorService;
import com.mornsun.jsw.api.api.atta.IAttaApi;
import com.mornsun.jsw.api.api.company.course.ICompanyCourseApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;

/**
 * 厂家Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class VendorServiceImpl extends BasePageHelperApiServiceImpl<CompanyCourseVo, ICompanyCourseApi>
		implements IVendorService {

	@Autowired
	private IUploadService uploadService;

	@Autowired
	private IAttaApi attaApi;

	/**
	 * 保存产品附件信息
	 * 
	 * @param obj
	 * @param type
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<CompanyCourseVo> save(CompanyCourseVo obj, HttpServletRequest request) throws Exception {
		ResultVo<CompanyCourseVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			String msg = ReflectUtil.getInstance().validObjField(obj);
			if (StringUtils.isNotEmpty(msg)) {

				res = RESULT_DATA_NULL;// 数据错误
				logger.error(msg);

			} else {

				resultVo = super.save(obj, request);
				res = resultVo.getRes();

				if (res == RESULT_SUCCESS) {
					List<BaseAttaModel> attaModels = uploadService.upload(resultVo.getObj().getId(),
							AttaTypeConstant.COMPANY_COURSE.getType(), AttaTypeConstant.COMPANY_COURSE.getName(),
							request);
					if (CollectionUtils.isNotEmpty(attaModels)) {
						for (BaseAttaModel baseAttaModel : attaModels) {

							AttaVo attaVo = new AttaVo();
							BeanUtils.copyProperties(baseAttaModel, attaVo);

							// 保存附件
							int count = attaApi.insert(attaVo);
							if (count <= 0) {
								break;
							}

						}
					}
				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][save][" + obj.getClass().getSimpleName() + "="
					+ JSON.toJSONString(obj) + "][res=" + res + "]");
		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		resultVo.setRes(res);
		return resultVo;
	}

	/**
	 * 保存产品附件信息
	 * 
	 * @param obj
	 * @param type
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<CompanyCourseVo> update(CompanyCourseVo obj, HttpServletRequest request) throws Exception {
		ResultVo<CompanyCourseVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			if (StringUtils.isEmpty(obj.getId())) {

				res = RESULT_DATA_NULL;// 数据错误

			} else {

				resultVo = super.update(obj, request);
				res = resultVo.getRes();

				if (res == RESULT_SUCCESS) {

					List<BaseAttaModel> attaModels = uploadService.upload(resultVo.getObj().getId(),
							AttaTypeConstant.COMPANY_COURSE.getType(), AttaTypeConstant.COMPANY_COURSE.getName(),
							request);
					if (CollectionUtils.isNotEmpty(attaModels)) {
						for (BaseAttaModel baseAttaModel : attaModels) {

							AttaVo attaVo = new AttaVo();
							BeanUtils.copyProperties(baseAttaModel, attaVo);

							// 保存附件
							int count = attaApi.insert(attaVo);
							if (count <= 0) {
								break;
							}

						}
					}
				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][save][" + obj.getClass().getSimpleName() + "="
					+ JSON.toJSONString(obj) + "][res=" + res + "]");
		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		resultVo.setRes(res);
		return resultVo;
	}

}
