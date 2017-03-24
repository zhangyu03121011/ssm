package com.mornsun.app.core.service.user.expert.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.model.atta.BaseAttaModel;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.service.service.atta.IUploadService;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.common.util.SessionUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.api.constant.AttaTypeConstant;
import com.mornsun.app.core.service.atta.IAttaService;
import com.mornsun.app.core.service.catalog.ICatalogService;
import com.mornsun.app.core.service.user.attention.IUserAttentionService;
import com.mornsun.app.core.service.user.catalog.IUserCatalogService;
import com.mornsun.app.core.service.user.expert.IUserExpertService;
import com.mornsun.jsw.api.api.user.expert.IUserExpertApi;
import com.mornsun.jsw.api.vo.atta.AttaVo;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;
import com.mornsun.jsw.api.vo.user.attention.UserAttentionVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;
import com.mornsun.jsw.api.vo.user.expert.UserExpertVo;

/**
 * 用户专家Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserExpertServiceImpl extends BasePageHelperApiServiceImpl<UserExpertVo, IUserExpertApi>
		implements IUserExpertService {

	@Autowired
	private IUserCatalogService userCatalogService;

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private IUploadService uploadService;

	@Autowired
	private IAttaService attaService;

	@Autowired
	private ICatalogService catalogService;

	@Autowired
	private IUserAttentionService userAttentionService;

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserExpertVo> list(UserExpertVo obj, PageListVo<UserExpertVo> pageListVo,
			HttpServletRequest request) throws Exception {
		ResultVo<UserExpertVo> resultVo = new ResultVo<UserExpertVo>();
		int res = RESULT_FAILURE;
		try {

			// 不设置用户id
			obj.setUserId(null);
			resultVo = super.list(obj, pageListVo, request);
			res = resultVo.getRes();
			if (resultVo.getRes() == RESULT_SUCCESS) {

				List<UserExpertVo> userExpertVos = resultVo.getPageListVo().getRows();
				for (UserExpertVo userExpertVoTmp : userExpertVos) {
					// 查询擅长领域
					UserCatalogVo userCatalogVo = new UserCatalogVo();
					userCatalogVo.setUserId(userExpertVoTmp.getUserId());
					userCatalogVo.setType(AppConstant.AREA_TYPE_SPECIALTY);
					userExpertVoTmp.setUserCatalogVos(userCatalogService.getAll(userCatalogVo));
				}

			}

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
	 * 查询专家信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserExpertVo> single(UserExpertVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserExpertVo> resultVo = new ResultVo<UserExpertVo>();
		int res = RESULT_FAILURE;
		try {

			resultVo = super.single(obj, request);
			res = resultVo.getRes();

			logger.info("[" + this.getClass().getSimpleName() + "][single][" + obj.getClass().getSimpleName() + "="
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
	 * 专家明细
	 */
	public ResultVo<UserExpertVo> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<UserExpertVo> resultVo = new ResultVo<UserExpertVo>();
		int res = RESULT_FAILURE;
		try {

			UserExpertVo userExpertVo = new UserExpertVo();
			userExpertVo.setUserId(id);
			resultVo = super.single(userExpertVo, request);
			res = resultVo.getRes();

			if (res == RESULT_SUCCESS) {
				// 获取登录信息
				UserVo userVo = (UserVo) memcacheService.get(SessionUtil.getInstance().getSessionId(request));
				if (userVo != null) {

					// 查询是否已经关注
					if (id.equals(userVo.getId())) {
						resultVo.getObj().setIsAttention(STATUS_YES);
					} else {
						UserAttentionVo userAttentionVo = new UserAttentionVo();
						userAttentionVo.setUserId(userVo.getId());
						userAttentionVo.setAttentionUserId(id);
						userAttentionVo.setFlag(true);
						userAttentionVo = userAttentionService.getOne(userAttentionVo);
						if (userAttentionVo != null) {
							resultVo.getObj().setIsAttention(STATUS_YES);
						} else {
							resultVo.getObj().setIsAttention(STATUS_NO);
						}
					}

				}
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

	/**
	 * 保存专家信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserExpertVo> save(UserExpertVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserExpertVo> resultVo = new ResultVo<UserExpertVo>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			String msg = ReflectUtil.getInstance().validObjField(obj);
			if (StringUtils.isNotEmpty(msg)) {

				res = RESULT_DATA_NULL;// 数据错误
				logger.error(msg);

			} else {

				List<UserCatalogVo> userCatalogVos = new ArrayList<>();
				// 保存专家擅长领域
				if (StringUtils.isNotEmpty(obj.getCatalogId())) {

					// 获取登录信息
					UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());

					String catalogIdArray[] = obj.getCatalogId().split(",");
					// 先删除用户分类关系
					UserCatalogVo userCatalogVo = new UserCatalogVo();
					userCatalogVo.setUserId(userVo.getId());
					userCatalogVo.setType(AppConstant.AREA_TYPE_SPECIALTY);
					userCatalogService.delete(userCatalogVo);

					for (String catalogId : catalogIdArray) {
						if (StringUtils.isEmpty(catalogId)) {
							continue;
						}

						UserCatalogVo userCatalogVoTmp = new UserCatalogVo();
						userCatalogVoTmp.setUserId(userVo.getId());
						userCatalogVoTmp.setType(AppConstant.AREA_TYPE_INTEREST);
						userCatalogVoTmp.setCatalogId(catalogId);
						userCatalogVoTmp.setCatalogId(catalogId);

						// 添加产品应用领域
						ResultVo<UserCatalogVo> resultVoTmp = userCatalogService.save(userCatalogVoTmp, request);
						res = resultVoTmp.getRes();
						if (res != RESULT_SUCCESS) {
							break;
						} else {

							CatalogVo catalogVo = catalogService.getOneById(catalogId);
							UserCatalogVo userCatalogVoListTmp = new UserCatalogVo();
							userCatalogVoListTmp.setCatalogVo(catalogVo);
							userCatalogVos.add(userCatalogVoListTmp);

						}
					}
				}

				if (res == RESULT_SUCCESS) {
					resultVo = super.save(obj, request);
					res = resultVo.getRes();
					resultVo.getObj().setUserCatalogVos(userCatalogVos);
				}

				if (res == RESULT_SUCCESS) {
					List<BaseAttaModel> attaModels = uploadService.upload(resultVo.getObj().getId(),
							AttaTypeConstant.EXPERT.getType(), AttaTypeConstant.EXPERT.getName(), request);
					if (CollectionUtils.isNotEmpty(attaModels)) {
						for (BaseAttaModel baseAttaModel : attaModels) {

							AttaVo attaVo = new AttaVo();
							BeanUtils.copyProperties(baseAttaModel, attaVo);

							// 保存附件
							ResultVo<AttaVo> resultVoTmp = attaService.save(attaVo, request);
							res = resultVoTmp.getRes();
							if (res != RESULT_SUCCESS) {
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
