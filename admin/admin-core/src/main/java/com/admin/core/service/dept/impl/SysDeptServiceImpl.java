package com.admin.core.service.dept.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.dept.SysDeptVo;
import com.admin.core.dao.dept.ISysDeptDao;
import com.admin.core.service.dept.ISysDeptService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 字典Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysDeptServiceImpl extends BasePageHelperControllerServiceImpl<SysDeptVo,ISysDeptDao> implements ISysDeptService {

}
