package com.mornsun.jsw.core.service.param.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.mornsun.jsw.core.service.param.IParamService;
import com.mornsun.jsw.core.dao.param.IParamDao;
import com.mornsun.jsw.api.vo.param.ParamVo;

import com.mornsun.jsw.api.model.param.ParamModelCriteria;
import java.util.List;

/**
 * 参数Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ParamServiceImpl extends BasePageHelperServiceImpl<ParamVo, IParamDao> implements IParamService {

	@Autowired
	private IParamDao paramDao;

	/**
	 * 根据条件查询总数
	 *
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public long countByExample(ParamModelCriteria example) throws Exception {
		try {
			return paramDao.countByExample(example);
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
	public int deleteByExample(ParamModelCriteria example) throws Exception {
		try {
			return paramDao.deleteByExample(example);
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
	public int insertSelective(ParamVo record) throws Exception {
		try {
			return paramDao.insertSelective(record);
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
	public List<ParamVo> selectByExample(ParamModelCriteria example) throws Exception {
		try {
			return paramDao.selectByExample(example);
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
	public int updateByExampleSelective(ParamVo record, ParamModelCriteria example) throws Exception {
		try {
			return paramDao.updateByExampleSelective(record, example);
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
	public int updateByExample(ParamVo record, ParamModelCriteria example) throws Exception {
		try {
			return paramDao.updateByExample(record, example);
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
	public int updateByPrimaryKey(ParamVo record) throws Exception {
		try {
			return paramDao.updateByPrimaryKey(record);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

}
