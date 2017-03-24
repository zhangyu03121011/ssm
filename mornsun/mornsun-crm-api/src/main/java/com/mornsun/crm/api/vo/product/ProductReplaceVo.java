package com.mornsun.crm.api.vo.product;

/**
 * 产品替换信息VO
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductReplaceVo {
	/**
	 * 产品信息名称
	 */
	private String productNoName;

	/**
	 * 替换型号名称
	 */
	private String replaceProductNoName;

	/**
	 * 替换型号分类名称
	 */
	private String replaceCatalogName;

	/**
	 * 替换型号品牌名称
	 */
	private String replaceBrandName;

	/**
	 * 替换产品状态
	 */
	private String replaceProductState;

	/**
	 * 替换型号产品描述
	 */
	private String replaceProductDesc;

	/**
	 * 替换型号匹配度
	 */
	private String matchType;

	public String getProductNoName() {
		return productNoName;
	}

	public void setProductNoName(String productNoName) {
		this.productNoName = productNoName;
	}

	public String getReplaceProductNoName() {
		return replaceProductNoName;
	}

	public void setReplaceProductNoName(String replaceProductNoName) {
		this.replaceProductNoName = replaceProductNoName;
	}

	public String getReplaceCatalogName() {
		return replaceCatalogName;
	}

	public void setReplaceCatalogName(String replaceCatalogName) {
		this.replaceCatalogName = replaceCatalogName;
	}

	public String getReplaceBrandName() {
		return replaceBrandName;
	}

	public void setReplaceBrandName(String replaceBrandName) {
		this.replaceBrandName = replaceBrandName;
	}

	public String getReplaceProductDesc() {
		return replaceProductDesc;
	}

	public void setReplaceProductDesc(String replaceProductDesc) {
		this.replaceProductDesc = replaceProductDesc;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getReplaceProductState() {
		return replaceProductState;
	}

	public void setReplaceProductState(String replaceProductState) {
		this.replaceProductState = replaceProductState;
	}

}
