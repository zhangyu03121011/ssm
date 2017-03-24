package com.admin.core.service.role.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.role.SysRoleVo;
import com.admin.core.dao.role.ISysRoleDao;
import com.admin.core.service.role.ISysRoleService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 角色Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysRoleServiceImpl extends BasePageHelperControllerServiceImpl<SysRoleVo, ISysRoleDao>
        implements ISysRoleService {

}
