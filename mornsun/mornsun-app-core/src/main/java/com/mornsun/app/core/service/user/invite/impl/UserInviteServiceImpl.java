package com.mornsun.app.core.service.user.invite.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.user.invite.IUserInviteApi;
import com.mornsun.app.core.service.user.invite.IUserInviteService;
import com.mornsun.jsw.api.vo.user.invite.UserInviteVo;

/**
 * 用户邀请Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserInviteServiceImpl extends BasePageHelperApiServiceImpl<UserInviteVo,IUserInviteApi>  implements IUserInviteService {

}
