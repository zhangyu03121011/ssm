package com.mornsun.jsw.core.service.user.employee.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.mornsun.jsw.core.service.user.employee.IUserEmployeeService;
import com.mornsun.jsw.core.dao.user.employee.IUserEmployeeDao;
import com.mornsun.jsw.api.vo.user.employee.UserEmployeeVo;

import com.mornsun.jsw.api.model.user.employee.UserEmployeeModelCriteria;
import java.util.List;

/**
 * 用户内部员工Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserEmployeeServiceImpl extends BasePageHelperServiceImpl<UserEmployeeVo,IUserEmployeeDao>  implements IUserEmployeeService {  @Autowired
  private IUserEmployeeDao useremployeeDao;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public int countByExample(UserEmployeeModelCriteria example) throws Exception{
    try {
        return useremployeeDao.countByExample(example);
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
  public int deleteByExample(UserEmployeeModelCriteria example) throws Exception{
    try {
        return useremployeeDao.deleteByExample(example);
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
  public int insertSelective(UserEmployeeVo record) throws Exception{
    try {
        return useremployeeDao.insertSelective(record);
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
  public List<UserEmployeeVo> selectByExample(UserEmployeeModelCriteria example) throws Exception{
    try {
        return useremployeeDao.selectByExample(example);
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
  public int updateByExampleSelective(UserEmployeeVo record, UserEmployeeModelCriteria example) throws Exception{
    try {
        return useremployeeDao.updateByExampleSelective(record,example);
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
  public int updateByExample(UserEmployeeVo record, UserEmployeeModelCriteria example) throws Exception{
    try {
        return useremployeeDao.updateByExample(record,example);
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
  public int updateByPrimaryKey(UserEmployeeVo record) throws Exception{
    try {
        return useremployeeDao.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
