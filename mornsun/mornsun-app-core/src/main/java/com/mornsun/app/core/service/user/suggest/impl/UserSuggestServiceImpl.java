package com.mornsun.app.core.service.user.suggest.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.user.suggest.IUserSuggestApi;
import com.mornsun.app.core.service.user.suggest.IUserSuggestService;
import com.mornsun.jsw.api.vo.user.suggest.UserSuggestVo;

/**
 * 用户意见Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserSuggestServiceImpl extends BasePageHelperApiServiceImpl<UserSuggestVo,IUserSuggestApi>  implements IUserSuggestService {

}
