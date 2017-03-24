package com.admin.core.service.dict.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.dict.SysDictItemVo;
import com.admin.core.dao.dict.ISysDictItemDao;
import com.admin.core.service.dict.ISysDictItemService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 字典明细Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysDictItemServiceImpl extends BasePageHelperControllerServiceImpl<SysDictItemVo, ISysDictItemDao>
        implements ISysDictItemService {

}
