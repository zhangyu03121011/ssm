package com.mornsun.jsw.core.api.company.base.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.common.util.ExceptionUtil;

import com.common.orm.mybatis.api.page.impl.BasePageHelperProviderApiImpl;
import com.mornsun.jsw.api.api.company.base.ICompanyApi;
import com.mornsun.jsw.api.model.company.base.CompanyModelCriteria;
import com.mornsun.jsw.api.vo.company.base.CompanyVo;
import com.mornsun.jsw.core.service.company.base.ICompanyService;

import java.util.List;

/**
 * 企业Api
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class CompanyApiImpl extends BasePageHelperProviderApiImpl<CompanyVo,ICompanyService>  implements ICompanyApi {  @Autowired
  private ICompanyService companyService;

    /**
    * 根据条件查询总数
    *
    * @param example
    * @return
    * @throws Exception
    */
  public int countByExample(CompanyModelCriteria example) throws Exception{
    try {
        return companyService.countByExample(example);
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
  public int deleteByExample(CompanyModelCriteria example) throws Exception{
    try {
        return companyService.deleteByExample(example);
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
  public int insertSelective(CompanyVo record) throws Exception{
    try {
        return companyService.insertSelective(record);
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
  public List<CompanyVo> selectByExample(CompanyModelCriteria example) throws Exception{
    try {
        return companyService.selectByExample(example);
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
  public int updateByExampleSelective(CompanyVo record, CompanyModelCriteria example) throws Exception{
    try {
        return companyService.updateByExampleSelective(record,example);
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
  public int updateByExample(CompanyVo record, CompanyModelCriteria example) throws Exception{
    try {
        return companyService.updateByExample(record,example);
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
  public int updateByPrimaryKey(CompanyVo record) throws Exception{
    try {
        return companyService.updateByPrimaryKey(record);
    } catch (Exception e) {
        String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
        logger.error(msg, e);
        throw new Exception(msg, e);
    }
  }

}
