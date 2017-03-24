package com.siems.buyself.core.service.role.base.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.siems.jsw.api.api.role.base.IRoleApi;
import com.siems.buyself.core.service.role.base.IRoleService;
import com.siems.jsw.api.vo.role.base.RoleVo;

/**
 * 角色Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class RoleServiceImpl extends BasePageHelperApiServiceImpl<RoleVo,IRoleApi>  implements IRoleService {  

}
