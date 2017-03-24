package com.mornsun.jsw.api.vo.user.attention;

import com.mornsun.jsw.api.model.user.attention.UserAttentionModel;
import com.mornsun.jsw.api.vo.user.base.UserVo;

/**
 * 用户关注Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserAttentionVo extends UserAttentionModel {

    private static final long serialVersionUID = 1L;

    private UserVo userVo;

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

}
