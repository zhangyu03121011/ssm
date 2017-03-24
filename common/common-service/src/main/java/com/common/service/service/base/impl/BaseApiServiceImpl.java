package com.common.service.service.base.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.common.api.base.IBaseApi;
import com.common.base.model.base.BaseModel;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.service.service.api.impl.BaseReferenceApiImpl;
import com.common.service.service.base.IBaseApiService;
import com.common.util.AnnotationUtil;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;
import com.common.util.ReflectUtil;

/**
 * 业务层通用方法
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public class BaseApiServiceImpl<T extends BaseModel, E extends IBaseApi<T>> extends BaseReferenceApiImpl<T, E>
		implements IBaseApiService<T> {

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> list(T obj, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			List<T> list = this.getAll(obj);
			res = RESULT_SUCCESS;
			resultVo.setObj(obj);
			resultVo.setList(list);

			logger.info("[" + this.getClass().getSimpleName() + "][list][" + obj.getClass().getSimpleName() + "="
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
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public ResultVo<T> list(T obj, PageListVo<T> pageListVo, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			pageListVo = this.getPage(obj, pageListVo);
			res = RESULT_SUCCESS;
			resultVo.setObj(obj);
			resultVo.setPageListVo(pageListVo);

			logger.info("[" + this.getClass().getSimpleName() + "][list][" + obj.getClass().getSimpleName() + "="
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
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<T> page(T obj, PageListVo<T> pageListVo, HttpServletRequest request) throws Exception {
		try {
			// 查询信息
			pageListVo = this.getPage(obj, pageListVo);

			logger.info("[" + this.getClass().getSimpleName() + "][page][" + obj.getClass().getSimpleName() + "="
					+ JSON.toJSONString(obj) + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return pageListVo;
	}

	/**
	 * 单个信息查询
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> single(T obj, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (obj == null) {
				res = RESULT_DATA_NULL;
			} else {

				// 查询是否已经存在
				T objTmp = this.getOne(obj);
				if (objTmp != null) {

					res = RESULT_SUCCESS;
					resultVo.setObj(objTmp);
					logger.info("[" + this.getClass().getSimpleName() + "][single]["
							+ ReflectUtil.getInstance().getClassGenricType(this.getClass()).getClass().getSimpleName()
							+ "]success");

				} else {

					logger.error("[" + this.getClass().getSimpleName() + "][single]["
							+ ReflectUtil.getInstance().getClassGenricType(this.getClass()).getClass().getSimpleName()
							+ "]error");
					res = RESULT_NO_EXISTS;

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][single][" + obj.getClass().getSimpleName() + " ="
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
	 * 查询明细
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (id == null) {
				res = RESULT_DATA_NULL;
			} else {

				// 查询是否已经存在
				T objTmp = this.getOneById(id);
				if (objTmp != null) {

					res = RESULT_SUCCESS;
					resultVo.setObj(objTmp);
					logger.info("[" + this.getClass().getSimpleName() + "][list]["
							+ ReflectUtil.getInstance().getClassGenricType(this.getClass()).getClass().getSimpleName()
							+ "]success");

				} else {

					logger.error("[" + this.getClass().getSimpleName() + "][detail]["
							+ ReflectUtil.getInstance().getClassGenricType(this.getClass()).getClass().getSimpleName()
							+ "]error");
					res = RESULT_NO_EXISTS;

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][detail][id =" + id + "][res=" + res + "]");
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
	 * 查询数量
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> count(T obj, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			if (obj == null) {
				res = RESULT_DATA_NULL;
			} else {

				// 查询总数
				long count = this.getCount(obj);
				res = RESULT_SUCCESS;
				resultVo.setCount(count);

				logger.info("[" + this.getClass().getSimpleName() + "][count][" + obj.getClass().getSimpleName()
						+ "]success");

			}

			logger.info("[" + this.getClass().getSimpleName() + "][count][" + obj.getClass().getSimpleName() + "="
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
	 * 更新数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> update(T obj, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			// 验证请求数据是否正确
			boolean flag = true;
			Object fieldValue = null;

			// 获取主键
			Field field = AnnotationUtil.getInstance().getAnnotationPrimaryField(obj.getClass());
			if (field != null) {
				fieldValue = field.getDeclaringClass()
						.getDeclaredMethod("get" + StringUtils.capitalize(field.getName())).invoke(obj);
				if (fieldValue == null || StringUtils.isEmpty(fieldValue.toString())) {
					logger.error("[" + this.getClass().getSimpleName() + "][update]primary field:" + field.getName()
							+ "=" + fieldValue);
					flag = false;
				}
			}

			if (!flag) {

				res = RESULT_DATA_NULL;

			} else {

				// 是否验证
				boolean validResult = true;
				if (obj.isValidFlag()) {

					// 查询是否已经存在
					T objTmp = this.getOneById(fieldValue.toString());
					if (objTmp == null) {

						logger.error("[" + this.getClass().getSimpleName() + "][update]["
								+ obj.getClass().getSimpleName() + "]error");
						res = RESULT_NO_EXISTS;
						validResult = false;

					}

				}

				// 验证结果通过
				if (validResult) {

					// 更新数据
					res = this.update(obj);
					if (res > 0) {

						res = RESULT_SUCCESS;
						resultVo.setObj(obj);
						logger.info("[" + this.getClass().getSimpleName() + "][update]["
								+ obj.getClass().getSimpleName() + "]success");

					} else {

						res = RESULT_FAILURE;
						logger.info("[" + this.getClass().getSimpleName() + "][update]["
								+ obj.getClass().getSimpleName() + "]failure");

					}

				}

			}

			logger.info("[" + this.getClass().getSimpleName() + "][update][" + obj.getClass().getSimpleName() + "="
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
	 * 批量更新数据
	 * 
	 * @param objList
	 * @param request
	 * @return
	 */
	public ResultVo<T> batch(List<T> objList, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			int count = 0;

			// 批量处理数据
			if (CollectionUtils.isNotEmpty(objList)) {
				for (T obj : objList) {

					// 插入
					if (obj.isFlag()) {
						resultVo = this.save(obj, request);
					} else {// 更新
						resultVo = this.update(obj, request);
					}

					if (resultVo.getRes() != RESULT_SUCCESS) {
						count++;
					}
				}
			}

			if (count == 0) {
				res = RESULT_SUCCESS;
			}

			logger.info("[" + this.getClass().getSimpleName() + "][batch][objList=" + JSON.toJSONString(objList)
					+ "][res=" + res + "]");
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
	 * 保存数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> save(T obj, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {
			// 验证请求数据是否正确
			boolean flag = true;

			// 获取所有需要验证的字段
			List<Field> list = AnnotationUtil.getInstance().getAnnotationValidField(obj.getClass());
			for (Field field : list) {
				Object fieldValue = field.getDeclaringClass()
						.getDeclaredMethod("get" + StringUtils.capitalize(field.getName())).invoke(obj);
				if (fieldValue == null || StringUtils.isEmpty(fieldValue.toString())) {
					logger.error("[" + this.getClass().getSimpleName() + "][save]validate field:" + field.getName()
							+ "=" + fieldValue);
					flag = false;
				}
			}

			if (!flag) {

				res = RESULT_DATA_NULL;// 数据错误

			} else {

				// 是否验证
				boolean validResult = true;
				if (obj.isValidFlag()) {
					// 查询是否已经存在
					T objTmp = this.getOne(obj);
					if (objTmp != null) {

						logger.error("[" + this.getClass().getSimpleName() + "][save][" + obj.getClass().getSimpleName()
								+ "]error");
						res = RESULT_HAS_EXISTS;
						validResult = false;

					}
				}

				// 验证结果通过
				if (validResult) {

					logger.info("[" + this.getClass().getSimpleName() + "][save][" + obj.getClass().getSimpleName()
							+ "]success");

					// 获取主键
					Field field = AnnotationUtil.getInstance().getAnnotationPrimaryField(obj.getClass());
					if (field != null) {

						logger.info("[" + this.getClass().getSimpleName() + "][save]prikey");

						// 判断主键是否有值
						Object priObj = field.getDeclaringClass()
								.getDeclaredMethod("get" + StringUtils.capitalize(field.getName())).invoke(obj);
						if (priObj == null || StringUtils.isEmpty(priObj.toString())) {
							field.getDeclaringClass()
									.getDeclaredMethod("set" + StringUtils.capitalize(field.getName()), field.getType())
									.invoke(obj, PrimaryUtil.getInstance().getPrimaryValue());
						}

					}

					// 添加数据
					res = this.insert(obj);
					if (res > 0) {
						res = RESULT_SUCCESS;// 成功
						resultVo.setObj(obj);
					} else {
						res = RESULT_FAILURE;
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
