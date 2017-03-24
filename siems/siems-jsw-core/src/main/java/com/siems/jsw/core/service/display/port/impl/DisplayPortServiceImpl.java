package com.siems.jsw.core.service.display.port.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.siems.jsw.core.service.display.port.IDisplayPortService;
import com.siems.jsw.core.dao.display.port.IDisplayPortDao;
import com.siems.jsw.api.vo.display.port.DisplayPortVo;

import com.siems.jsw.api.model.display.port.DisplayPortModelCriteria;
import java.util.List;

/**
 * 陈列区域端口Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class DisplayPortServiceImpl extends BasePageHelperServiceImpl<DisplayPortVo,IDisplayPortDao>  implements IDisplayPortService {  

  @Autowired
  private IDisplayPortDao displayportDao;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public long countByExample(DisplayPortModelCriteria example) throws Exception{
    try {
        return displayportDao.countByExample(example);
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
  public int deleteByExample(DisplayPortModelCriteria example) throws Exception{
    try {
        return displayportDao.deleteByExample(example);
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
  public int insertSelective(DisplayPortVo record) throws Exception{
    try {
        return displayportDao.insertSelective(record);
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
  public List<DisplayPortVo> selectByExample(DisplayPortModelCriteria example) throws Exception{
    try {
        return displayportDao.selectByExample(example);
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
  public int updateByExampleSelective(DisplayPortVo record, DisplayPortModelCriteria example) throws Exception{
    try {
        return displayportDao.updateByExampleSelective(record,example);
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
  public int updateByExample(DisplayPortVo record, DisplayPortModelCriteria example) throws Exception{
    try {
        return displayportDao.updateByExample(record,example);
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
  public int updateByPrimaryKey(DisplayPortVo record) throws Exception{
    try {
        return displayportDao.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
