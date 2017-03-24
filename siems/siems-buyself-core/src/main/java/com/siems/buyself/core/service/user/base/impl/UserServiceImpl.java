package com.siems.buyself.core.service.user.base.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.siems.jsw.api.api.user.base.IUserApi;
import com.siems.buyself.core.service.user.base.IUserService;
import com.siems.jsw.api.vo.user.base.UserVo;

/**
 * 用户Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserServiceImpl extends BasePageHelperApiServiceImpl<UserVo,IUserApi>  implements IUserService {  

}
