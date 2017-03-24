package com.mornsun.jsw.api.vo.catalog;

import java.util.List;

import com.mornsun.jsw.api.model.catalog.CatalogModel;

/**
 * 分类Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CatalogVo extends CatalogModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 产品数量
	 */
	private int productCount;

	/**
	 * 品牌ID
	 */
	private String brandId;

	/**
	 * 父级分类名称
	 */
	private String parentCatalogName;

	/**
	 * 分类子列表
	 */
	private List<CatalogVo> catalogVos;

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public List<CatalogVo> getCatalogVos() {
		return catalogVos;
	}

	public void setCatalogVos(List<CatalogVo> catalogVos) {
		this.catalogVos = catalogVos;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getParentCatalogName() {
		return parentCatalogName;
	}

	public void setParentCatalogName(String parentCatalogName) {
		this.parentCatalogName = parentCatalogName;
	}
}
