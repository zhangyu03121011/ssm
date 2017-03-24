package com.admin.core.service.menu.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.menu.SysMenuVo;
import com.admin.core.dao.menu.ISysMenuDao;
import com.admin.core.service.menu.ISysMenuService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 系统菜单Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysMenuServiceImpl extends BasePageHelperControllerServiceImpl<SysMenuVo, ISysMenuDao>
        implements ISysMenuService {

}
