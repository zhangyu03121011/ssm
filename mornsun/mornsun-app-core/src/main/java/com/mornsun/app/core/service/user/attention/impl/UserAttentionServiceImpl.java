package com.mornsun.app.core.service.user.attention.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.core.service.user.attention.IUserAttentionService;
import com.mornsun.jsw.api.api.user.attention.IUserAttentionApi;
import com.mornsun.jsw.api.vo.user.attention.UserAttentionVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户关注Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserAttentionServiceImpl extends BasePageHelperApiServiceImpl<UserAttentionVo, IUserAttentionApi>
		implements IUserAttentionService {

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
	public ResultVo<UserAttentionVo> save(UserAttentionVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserAttentionVo> resultVo = new ResultVo<>();
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
				UserAttentionVo userAttentionVo = new UserAttentionVo();
				userAttentionVo.setUserId(userVo.getId());
				userAttentionVo.setAttentionUserId(obj.getAttentionUserId());
				userAttentionVo = super.getOne(userAttentionVo);
				if (userAttentionVo != null) {
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
}
