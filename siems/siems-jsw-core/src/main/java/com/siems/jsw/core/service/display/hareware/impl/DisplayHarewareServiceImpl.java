package com.siems.jsw.core.service.display.hareware.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.siems.jsw.core.service.display.hareware.IDisplayHarewareService;
import com.siems.jsw.core.dao.display.hareware.IDisplayHarewareDao;
import com.siems.jsw.api.vo.display.hareware.DisplayHarewareVo;

import com.siems.jsw.api.model.display.hareware.DisplayHarewareModelCriteria;
import java.util.List;

/**
 * 陈列区域硬件关系Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class DisplayHarewareServiceImpl extends BasePageHelperServiceImpl<DisplayHarewareVo,IDisplayHarewareDao>  implements IDisplayHarewareService {  

  @Autowired
  private IDisplayHarewareDao displayharewareDao;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public long countByExample(DisplayHarewareModelCriteria example) throws Exception{
    try {
        return displayharewareDao.countByExample(example);
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
  public int deleteByExample(DisplayHarewareModelCriteria example) throws Exception{
    try {
        return displayharewareDao.deleteByExample(example);
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
  public int insertSelective(DisplayHarewareVo record) throws Exception{
    try {
        return displayharewareDao.insertSelective(record);
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
  public List<DisplayHarewareVo> selectByExample(DisplayHarewareModelCriteria example) throws Exception{
    try {
        return displayharewareDao.selectByExample(example);
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
  public int updateByExampleSelective(DisplayHarewareVo record, DisplayHarewareModelCriteria example) throws Exception{
    try {
        return displayharewareDao.updateByExampleSelective(record,example);
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
  public int updateByExample(DisplayHarewareVo record, DisplayHarewareModelCriteria example) throws Exception{
    try {
        return displayharewareDao.updateByExample(record,example);
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
  public int updateByPrimaryKey(DisplayHarewareVo record) throws Exception{
    try {
        return displayharewareDao.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
