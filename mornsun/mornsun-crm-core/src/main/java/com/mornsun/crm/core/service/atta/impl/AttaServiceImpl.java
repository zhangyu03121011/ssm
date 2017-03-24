package com.mornsun.crm.core.service.atta.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.base.constant.BaseConstant;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.ResultVo;
import com.common.service.service.atta.IUploadService;
import com.common.util.ExceptionUtil;
import com.mornsun.crm.api.constant.AttaTypeConstant;
import com.mornsun.crm.core.service.atta.IAttaService;
import com.mornsun.jsw.api.api.atta.IAttaApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;

/**
 * 附件Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class AttaServiceImpl extends BaseLogger implements IAttaService, BaseConstant {

	@Autowired
	private IAttaApi attaApi;

	@Autowired
	private IUploadService uploadService;

	/**
	 * 文件上传
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<BaseAttaModel> importAtta(String type, HttpServletRequest request) throws Exception {
		ResultVo<BaseAttaModel> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			if (StringUtils.isEmpty(type)) {

				res = RESULT_DATA_NULL;// 数据错误

			} else {

				// 先删除附件信息
				AttaVo attaVoTmp = new AttaVo();
				attaVoTmp.setSourceId("-1");
				attaApi.delete(attaVoTmp);

				AttaTypeConstant attaTypeConstant = null;
				if (type.equals(AttaTypeConstant.PRODUCT.getType())) {
					attaTypeConstant = AttaTypeConstant.PRODUCT;
				} else if (type.equals(AttaTypeConstant.COMPANY_COURSE.getType())) {
					attaTypeConstant = AttaTypeConstant.COMPANY_COURSE;
				}
				List<BaseAttaModel> attaModels = uploadService.upload("-1", attaTypeConstant.getType(),
						attaTypeConstant.getName(), request);
				if (CollectionUtils.isNotEmpty(attaModels)) {
					for (BaseAttaModel baseAttaModel : attaModels) {

						AttaVo attaVo = new AttaVo();
						BeanUtils.copyProperties(baseAttaModel, attaVo);

						// 保存附件
						attaVo.setCreateBy("sys");
						int count = attaApi.insert(attaVo);
						if (count > 0) {
							resultVo.setObj(baseAttaModel);
							res = RESULT_SUCCESS;
						} else {
							break;
						}

					}

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][importAtta][type=" + type + "][res=" + res + "]");
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
	 * 文件上传
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<BaseAttaModel> upload(String id, String type, HttpServletRequest request) throws Exception {
		ResultVo<BaseAttaModel> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			if (StringUtils.isEmpty(id)) {

				res = RESULT_DATA_NULL;// 数据错误

			} else {

				AttaTypeConstant attaTypeConstant = null;
				if (type.equals(AttaTypeConstant.COMPANY.getType())) {
					attaTypeConstant = AttaTypeConstant.COMPANY;
				} else if (type.equals(AttaTypeConstant.COMPANY_COURSE.getType())) {
					attaTypeConstant = AttaTypeConstant.COMPANY_COURSE;
					// 先删除附件信息
					AttaVo attaVoTmp = new AttaVo();
					attaVoTmp.setSourceId(id);
					attaApi.delete(attaVoTmp);
				}
				List<BaseAttaModel> attaModels = uploadService.upload(id, attaTypeConstant.getType(),
						attaTypeConstant.getName(), request);
				if (CollectionUtils.isNotEmpty(attaModels)) {
					for (BaseAttaModel baseAttaModel : attaModels) {

						AttaVo attaVo = new AttaVo();
						BeanUtils.copyProperties(baseAttaModel, attaVo);

						// 保存附件
						attaVo.setCreateBy("sys");
						int count = attaApi.insert(attaVo);
						if (count > 0) {
							resultVo.setObj(baseAttaModel);
							res = RESULT_SUCCESS;
						} else {
							break;
						}

					}

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][upload][id=" + id + "][res=" + res + "]");
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
	 * 文件查询
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVo<AttaVo> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<AttaVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			if (StringUtils.isEmpty(id)) {

				res = RESULT_DATA_NULL;// 数据错误

			} else {
				AttaVo attaVo = new AttaVo();
				attaVo.setSourceId(id);
				attaVo = attaApi.getOne(attaVo);
			}

			logger.info("[" + this.getClass().getSimpleName() + "][detail][id=" + id + "][res=" + res + "]");
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
