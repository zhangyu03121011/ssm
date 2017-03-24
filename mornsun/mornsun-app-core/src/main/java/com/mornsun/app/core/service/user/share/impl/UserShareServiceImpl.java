package com.mornsun.app.core.service.user.share.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.PrimaryUtil;
import com.common.util.ReflectUtil;
import com.mornsun.app.api.constant.AppConstant;
import com.mornsun.app.core.service.user.share.IUserShareService;
import com.mornsun.jsw.api.api.user.share.IUserShareApi;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.share.UserShareVo;

/**
 * 用户分享Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserShareServiceImpl extends BasePageHelperApiServiceImpl<UserShareVo, IUserShareApi>
		implements IUserShareService {

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
	public ResultVo<UserShareVo> save(UserShareVo obj, HttpServletRequest request) throws Exception {
		ResultVo<UserShareVo> resultVo = new ResultVo<UserShareVo>();
		int res = RESULT_FAILURE;
		try {

			// 验证对象必填项
			String msg = ReflectUtil.getInstance().validObjField(obj);
			if (StringUtils.isNotEmpty(msg)) {

				res = RESULT_DATA_NULL;// 数据错误
				logger.error(msg);

			} else {

				// 查询是否已经分享过
				// 判断用户是否已经点赞过
				UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());
				UserShareVo userShareVo = new UserShareVo();
				userShareVo.setUserId(userVo.getId());
				userShareVo.setSourceId(obj.getSourceId());
				userShareVo = super.getOne(userShareVo);
				if (userShareVo != null) {

					resultVo.setObj(userShareVo);
					res = RESULT_SUCCESS;

				} else {

					// 源类别（1-邀请好友，2-问答，3-秒懂，4-产品）
					String shareUrl = "";
					if (AppConstant.SHARE_TYPE_ANSWER.equals(obj.getSourceType())) {
						shareUrl = "/wechat/user/question/detail?id=" + obj.getSourceId();
					} else if (AppConstant.SHARE_TYPE_COURSE.equals(obj.getSourceType())) {
						shareUrl = "/wechat/user/course/detail?id=" + obj.getSourceId();
					} else if (AppConstant.SHARE_TYPE_INVITE.equals(obj.getSourceType())) {
						shareUrl = "/wechat/user/share?id=" + obj.getSourceId();
					} else if (AppConstant.SHARE_TYPE_PRODUCT.equals(obj.getSourceType())) {
						shareUrl = "/";
					}

					// 分享URL
					obj.setId(PrimaryUtil.getInstance().getPrimaryValue());
					obj.setShareUrl(shareUrl);
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
