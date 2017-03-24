package com.mornsun.jsw.core.service.user.catalog.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.service.page.impl.BasePageHelperServiceImpl;
import com.mornsun.jsw.core.service.user.catalog.IUserCatalogService;
import com.mornsun.jsw.core.dao.user.catalog.IUserCatalogDao;
import com.mornsun.jsw.api.vo.user.catalog.UserCatalogVo;

import com.mornsun.jsw.api.model.user.catalog.UserCatalogModelCriteria;
import java.util.List;

/**
 * 用户分类关系Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserCatalogServiceImpl extends BasePageHelperServiceImpl<UserCatalogVo,IUserCatalogDao>  implements IUserCatalogService {  @Autowired
  private IUserCatalogDao usercatalogDao;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public int countByExample(UserCatalogModelCriteria example) throws Exception{
    try {
        return usercatalogDao.countByExample(example);
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
  public int deleteByExample(UserCatalogModelCriteria example) throws Exception{
    try {
        return usercatalogDao.deleteByExample(example);
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
  public int insertSelective(UserCatalogVo record) throws Exception{
    try {
        return usercatalogDao.insertSelective(record);
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
  public List<UserCatalogVo> selectByExample(UserCatalogModelCriteria example) throws Exception{
    try {
        return usercatalogDao.selectByExample(example);
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
  public int updateByExampleSelective(UserCatalogVo record, UserCatalogModelCriteria example) throws Exception{
    try {
        return usercatalogDao.updateByExampleSelective(record,example);
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
  public int updateByExample(UserCatalogVo record, UserCatalogModelCriteria example) throws Exception{
    try {
        return usercatalogDao.updateByExample(record,example);
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
  public int updateByPrimaryKey(UserCatalogVo record) throws Exception{
    try {
        return usercatalogDao.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
