package com.mornsun.jsw.core.api.user.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.api.user.order.IUserOrderApi;
import com.mornsun.jsw.api.model.user.order.UserOrderModelCriteria;
import com.mornsun.jsw.api.vo.user.order.UserOrderVo;
import com.mornsun.jsw.core.service.user.order.IUserOrderService;

/**
 * 用户订单Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserOrderApiImpl extends BasePageHelperProviderApiImpl<UserOrderVo, IUserOrderService>
		implements IUserOrderApi {

	@Autowired
	private IUserOrderService userorderService;

	/**
	 * 根据条件查询总数
	 *
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public long countByExample(UserOrderModelCriteria example) throws Exception {
		try {
			return userorderService.countByExample(example);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 根据条件删除数据
	 *
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int deleteByExample(UserOrderModelCriteria example) throws Exception {
		try {
			return userorderService.deleteByExample(example);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 根据条件插入数据
	 *
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int insertSelective(UserOrderVo record) throws Exception {
		try {
			return userorderService.insertSelective(record);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 根据条件查询数据
	 *
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public List<UserOrderVo> selectByExample(UserOrderModelCriteria example) throws Exception {
		try {
			return userorderService.selectByExample(example);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 根据条件更新数据
	 *
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExampleSelective(UserOrderVo record, UserOrderModelCriteria example) throws Exception {
		try {
			return userorderService.updateByExampleSelective(record, example);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 根据条件更新数据
	 *
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExample(UserOrderVo record, UserOrderModelCriteria example) throws Exception {
		try {
			return userorderService.updateByExample(record, example);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 根据主键更新数据
	 *
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int updateByPrimaryKey(UserOrderVo record) throws Exception {
		try {
			return userorderService.updateByPrimaryKey(record);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

}
