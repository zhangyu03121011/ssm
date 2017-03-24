package com.mornsun.jsw.api.vo.user.catalog;

import com.mornsun.jsw.api.model.user.catalog.UserCatalogModel;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;

/**
 * 用户分类关系Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserCatalogVo extends UserCatalogModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 一级分类VO
	 */
	private CatalogVo catalogVo;

	public CatalogVo getCatalogVo() {
		return catalogVo;
	}

	public void setCatalogVo(CatalogVo catalogVo) {
		this.catalogVo = catalogVo;
	}
}
