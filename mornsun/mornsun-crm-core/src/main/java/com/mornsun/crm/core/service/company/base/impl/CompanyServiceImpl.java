package com.mornsun.crm.core.service.company.base.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.mornsun.crm.core.service.company.base.ICompanyService;
import com.mornsun.jsw.api.api.company.base.ICompanyApi;
import com.mornsun.jsw.api.vo.company.base.CompanyVo;

/**
 * 公司Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class CompanyServiceImpl extends BasePageHelperApiServiceImpl<CompanyVo, ICompanyApi> implements ICompanyService {

}
