package com.mornsun.app.core.service.company.course.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.app.core.service.company.course.ICompanyCourseService;
import com.mornsun.jsw.api.api.company.course.ICompanyCourseApi;
import com.mornsun.jsw.api.vo.company.course.CompanyCourseVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 企业秒懂Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class CompanyCourseServiceImpl extends BasePageHelperApiServiceImpl<CompanyCourseVo, ICompanyCourseApi>
		implements ICompanyCourseService {

	@Autowired
	private IMemcacheService memcacheService;

	public ResultVo<CompanyCourseVo> read(CompanyCourseVo vo, HttpServletRequest request) throws Exception {
		ResultVo<CompanyCourseVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 更新浏览量数据
			// 秒懂
			CompanyCourseVo tmpVo = new CompanyCourseVo();
			tmpVo.setId(vo.getId());
			tmpVo.setReadCount(1);
			resultVo = super.update(tmpVo, request);
			res = resultVo.getRes();

			logger.debug("[" + this.getClass().getSimpleName() + "][read][" + vo.getClass().getSimpleName() + "="
					+ JSON.toJSONString(vo) + "][res=" + res + "]");
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
	public ResultVo<CompanyCourseVo> list(CompanyCourseVo obj, PageListVo<CompanyCourseVo> pageListVo,
			HttpServletRequest request) throws Exception {
		ResultVo<CompanyCourseVo> resultVo = new ResultVo<>();
		int res = RESULT_FAILURE;
		try {

			// 获取登录信息
			UserVo userVo = (UserVo) memcacheService.get(obj.getSessionId());
			if (userVo != null) {
				obj.setCurrUserId(userVo.getId());
			}

			resultVo = super.list(obj, pageListVo, request);
			res = resultVo.getRes();

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
}
