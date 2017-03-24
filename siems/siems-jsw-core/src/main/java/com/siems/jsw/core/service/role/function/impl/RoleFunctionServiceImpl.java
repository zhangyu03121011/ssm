package com.siems.jsw.core.service.role.function.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.siems.jsw.core.service.role.function.IRoleFunctionService;
import com.siems.jsw.core.dao.role.function.IRoleFunctionDao;
import com.siems.jsw.api.vo.role.function.RoleFunctionVo;

import com.siems.jsw.api.model.role.function.RoleFunctionModelCriteria;
import java.util.List;

/**
 * 角色功能关系Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class RoleFunctionServiceImpl extends BasePageHelperServiceImpl<RoleFunctionVo,IRoleFunctionDao>  implements IRoleFunctionService {  

  @Autowired
  private IRoleFunctionDao rolefunctionDao;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public long countByExample(RoleFunctionModelCriteria example) throws Exception{
    try {
        return rolefunctionDao.countByExample(example);
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
  public int deleteByExample(RoleFunctionModelCriteria example) throws Exception{
    try {
        return rolefunctionDao.deleteByExample(example);
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
  public int insertSelective(RoleFunctionVo record) throws Exception{
    try {
        return rolefunctionDao.insertSelective(record);
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
  public List<RoleFunctionVo> selectByExample(RoleFunctionModelCriteria example) throws Exception{
    try {
        return rolefunctionDao.selectByExample(example);
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
  public int updateByExampleSelective(RoleFunctionVo record, RoleFunctionModelCriteria example) throws Exception{
    try {
        return rolefunctionDao.updateByExampleSelective(record,example);
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
  public int updateByExample(RoleFunctionVo record, RoleFunctionModelCriteria example) throws Exception{
    try {
        return rolefunctionDao.updateByExample(record,example);
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
  public int updateByPrimaryKey(RoleFunctionVo record) throws Exception{
    try {
        return rolefunctionDao.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
