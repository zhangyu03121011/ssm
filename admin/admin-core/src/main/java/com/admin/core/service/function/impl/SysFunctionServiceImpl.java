package com.admin.core.service.function.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.function.SysFunctionVo;
import com.admin.core.dao.function.ISysFunctionDao;
import com.admin.core.service.function.ISysFunctionService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 系统功能Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysFunctionServiceImpl extends BasePageHelperControllerServiceImpl<SysFunctionVo, ISysFunctionDao>
        implements ISysFunctionService {

}
