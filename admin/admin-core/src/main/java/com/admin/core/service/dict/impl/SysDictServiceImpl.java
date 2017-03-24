package com.admin.core.service.dict.impl;

import org.springframework.stereotype.Service;

import com.admin.api.vo.dict.SysDictVo;
import com.admin.core.dao.dict.ISysDictDao;
import com.admin.core.service.dict.ISysDictService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperControllerServiceImpl;

/**
 * 数据字典Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月21日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class SysDictServiceImpl extends BasePageHelperControllerServiceImpl<SysDictVo, ISysDictDao>
        implements ISysDictService {

}
