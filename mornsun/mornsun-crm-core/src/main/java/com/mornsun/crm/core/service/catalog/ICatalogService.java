package com.mornsun.crm.core.service.catalog;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageHelperApiService;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;

/**
 * 分类Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface ICatalogService extends IBasePageHelperApiService<CatalogVo> {

	/**
	 * 导入Excel文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResultVo<CatalogVo> importExcelFile(HttpServletRequest request) throws Exception;

}
