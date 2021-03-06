package com.mornsun.jsw.core.api.applyarea.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.common.util.ExceptionUtil;
import com.mornsun.jsw.api.api.applyarea.IApplyAreaApi;
import com.mornsun.jsw.api.model.applyarea.ApplyAreaModelCriteria;
import com.mornsun.jsw.api.vo.applyarea.ApplyAreaVo;
import com.mornsun.jsw.core.service.applyarea.IApplyAreaService;

/**
 * 应用领域Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ApplyAreaApiImpl extends BasePageHelperProviderApiImpl<ApplyAreaVo,IApplyAreaService>  implements IApplyAreaApi {

  @Autowired
  private IApplyAreaService applyareaService;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public long countByExample(ApplyAreaModelCriteria example) throws Exception{
    try {
        return applyareaService.countByExample(example);
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
  public int deleteByExample(ApplyAreaModelCriteria example) throws Exception{
    try {
        return applyareaService.deleteByExample(example);
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
  public int insertSelective(ApplyAreaVo record) throws Exception{
    try {
        return applyareaService.insertSelective(record);
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
  public List<ApplyAreaVo> selectByExample(ApplyAreaModelCriteria example) throws Exception{
    try {
        return applyareaService.selectByExample(example);
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
  public int updateByExampleSelective(ApplyAreaVo record, ApplyAreaModelCriteria example) throws Exception{
    try {
        return applyareaService.updateByExampleSelective(record,example);
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
  public int updateByExample(ApplyAreaVo record, ApplyAreaModelCriteria example) throws Exception{
    try {
        return applyareaService.updateByExample(record,example);
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
  public int updateByPrimaryKey(ApplyAreaVo record) throws Exception{
    try {
        return applyareaService.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
