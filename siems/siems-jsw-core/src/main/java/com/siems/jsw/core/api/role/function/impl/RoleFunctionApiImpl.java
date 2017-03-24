package com.siems.jsw.core.api.role.function.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.siems.jsw.core.service.role.function.IRoleFunctionService;
import com.siems.jsw.api.api.role.function.IRoleFunctionApi;
import com.siems.jsw.api.vo.role.function.RoleFunctionVo;

import com.siems.jsw.api.model.role.function.RoleFunctionModelCriteria;
import java.util.List;

/**
 * 角色功能关系Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class RoleFunctionApiImpl extends BasePageHelperProviderApiImpl<RoleFunctionVo,IRoleFunctionService>  implements IRoleFunctionApi {  

  @Autowired
  private IRoleFunctionService rolefunctionService;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public long countByExample(RoleFunctionModelCriteria example) throws Exception{
    try {
        return rolefunctionService.countByExample(example);
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
        return rolefunctionService.deleteByExample(example);
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
        return rolefunctionService.insertSelective(record);
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
        return rolefunctionService.selectByExample(example);
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
        return rolefunctionService.updateByExampleSelective(record,example);
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
        return rolefunctionService.updateByExample(record,example);
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
        return rolefunctionService.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
