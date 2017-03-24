package com.mornsun.crm.api.vo.catalog;

/**
 * 分类VO
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CatalogParamVo {
	/**
	 * 分类名称
	 */
	private String catalogName;

	/**
	 * 父级分类名称
	 */
	private String parentName;

	/**
	 * 参数名称
	 */
	private String paramName;

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}
