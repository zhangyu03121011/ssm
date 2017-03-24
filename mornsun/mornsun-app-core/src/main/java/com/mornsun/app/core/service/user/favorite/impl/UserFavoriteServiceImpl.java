package com.mornsun.app.core.service.user.favorite.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.core.service.product.tag.IProductTagService;
import com.mornsun.app.core.service.user.favorite.IUserFavoriteService;
import com.mornsun.jsw.api.api.user.favorite.IUserFavoriteApi;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.favorite.UserFavoriteVo;

/**
 * 用户收藏Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserFavoriteServiceImpl extends BasePageHelperApiServiceImpl<UserFavoriteVo, IUserFavoriteApi>
		implements IUserFavoriteService {

	@Autowired
	private IProductTagService productTagService;

	@Autowired
	private IMemcacheService memcacheService;

	/**
	 * 保存分享信息
	 * 
	 * @param obj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<UserFavoriteVo> save(UserFavoriteVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserFavoriteVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			String msg = ReflectUtil.getInstance().validObjField(obj);
			if (StringUtils.isNotEmpty(msg)) {

				res = RESULT_DATA_NULL;// 数据错误
				logger.error(msg);

			} else {

				// 判断用户是否已经点赞过
				UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());
				UserFavoriteVo userFavoriteVo = new UserFavoriteVo();
				userFavoriteVo.setUserId(userVo.getId());
				userFavoriteVo.setSourceId(obj.getSourceId());
				userFavoriteVo = super.getOne(userFavoriteVo);
				if (userFavoriteVo != null) {
					res = RESULT_HAS_EXISTS;
				} else {
					resultVo = super.save(obj, request);
					res = resultVo.getRes();
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
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<UserFavoriteVo> list(UserFavoriteVo obj, PageListVo<UserFavoriteVo> pageListVo,
			HttpServletRequest request) throws Exception {
		ResultVo<UserFavoriteVo> resultVo = new ResultVo<UserFavoriteVo>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			resultVo = super.list(obj, pageListVo, request);
			res = resultVo.getRes();
			if (res == RESULT_SUCCESS) {
				// 查询产品标签信息
				List<UserFavoriteVo> userFavoriteVos = resultVo.getPageListVo().getRows();
				for (UserFavoriteVo userFavoriteVoTmp : userFavoriteVos) {
					// 查询标签
					ProductTagVo productTagVo = new ProductTagVo();
					productTagVo.setProductId(userFavoriteVoTmp.getProductVo().getId());
					userFavoriteVoTmp.getProductVo().setProductTagVos(productTagService.getAll(productTagVo));
				}
			}

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
