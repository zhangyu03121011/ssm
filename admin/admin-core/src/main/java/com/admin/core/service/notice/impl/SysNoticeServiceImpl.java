package com.admin.core.service.notice.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.notice.SysNoticeVo;
import com.admin.core.dao.notice.ISysNoticeDao;
import com.admin.core.service.notice.ISysNoticeService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 系统通知Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysNoticeServiceImpl extends BasePageHelperControllerServiceImpl<SysNoticeVo, ISysNoticeDao>
        implements ISysNoticeService {

}
