package com.mornsun.app.core.service.user.footprint.impl;

import org.springframework.stereotype.Service;

import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;

import com.mornsun.jsw.api.api.user.footprint.IUserFootprintApi;
import com.mornsun.app.core.service.user.footprint.IUserFootprintService;
import com.mornsun.jsw.api.vo.user.footprint.UserFootprintVo;

/**
 * 用户足迹Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class UserFootprintServiceImpl extends BasePageHelperApiServiceImpl<UserFootprintVo,IUserFootprintApi>  implements IUserFootprintService {

}
