package com.admin.core.service.role.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.role.SysRoleMenuVo;
import com.admin.core.dao.role.ISysRoleMenuDao;
import com.admin.core.service.role.ISysRoleMenuService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 角色菜单Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysRoleMenuServiceImpl extends BasePageHelperControllerServiceImpl<SysRoleMenuVo, ISysRoleMenuDao>
        implements ISysRoleMenuService {

}
