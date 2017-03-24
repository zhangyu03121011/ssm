package com.mornsun.crm.api.vo.product;

/**
 * 产品基本信息VO
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductBaseVo {
	/**
	 * 产品信息名称
	 */
	private String productNoName;

	/**
	 * 分类名称
	 */
	private String catalogName;

	/**
	 * 品牌名称
	 */
	private String brandName;

	/**
	 * 标签名称
	 */
	private String tagName;

	/**
	 * 应用领域名称
	 */
	private String productApplyArea;

	/**
	 * 产品状态
	 */
	private String productState;

	/**
	 * 产品描述
	 */
	private String productDesc;

	public String getProductNoName() {
		return productNoName;
	}

	public void setProductNoName(String productNoName) {
		this.productNoName = productNoName;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getProductApplyArea() {
		return productApplyArea;
	}

	public void setProductApplyArea(String productApplyArea) {
		this.productApplyArea = productApplyArea;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

}
