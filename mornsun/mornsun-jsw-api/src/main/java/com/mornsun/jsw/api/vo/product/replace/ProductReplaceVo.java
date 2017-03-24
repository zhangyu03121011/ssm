package com.mornsun.jsw.api.vo.product.replace;

import com.common.base.annotation.Validate;
import com.mornsun.jsw.api.model.product.replace.ProductReplaceModel;
import com.mornsun.jsw.api.vo.product.base.ProductCommonVo;

/**
 * 产品替换Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductReplaceVo extends ProductReplaceModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 元器件部件号
	 */
	@Validate
	private String productNo;

	/**
	 * 产品分类
	 */
	@Validate
	private String catalogId;

	/**
	 * 品牌ID
	 */
	@Validate
	private String brandId;

	/**
	 * 产品描述
	 */
	private String productDesc;

	/**
	 * 元器件部件号
	 */
	@Validate
	private String replaceProductNo;

	/**
	 * 产品分类
	 */
	@Validate
	private String replaceCatalogId;

	/**
	 * 品牌ID
	 */
	@Validate
	private String replaceBrandId;

	/**
	 * 产品描述
	 */
	@Validate
	private String replaceProductDesc;

	private ProductCommonVo productCommonVo;

	private ProductCommonVo productCommonReplaceVo;

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo == null ? null : productNo.trim();
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId == null ? null : catalogId.trim();
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId == null ? null : brandId.trim();
	}

	public String getReplaceProductNo() {
		return replaceProductNo;
	}

	public void setReplaceProductNo(String replaceProductNo) {
		this.replaceProductNo = replaceProductNo;
	}

	public String getReplaceCatalogId() {
		return replaceCatalogId;
	}

	public void setReplaceCatalogId(String replaceCatalogId) {
		this.replaceCatalogId = replaceCatalogId;
	}

	public String getReplaceBrandId() {
		return replaceBrandId;
	}

	public void setReplaceBrandId(String replaceBrandId) {
		this.replaceBrandId = replaceBrandId;
	}

	public String getReplaceProductDesc() {
		return replaceProductDesc;
	}

	public void setReplaceProductDesc(String replaceProductDesc) {
		this.replaceProductDesc = replaceProductDesc;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public ProductCommonVo getProductCommonVo() {
		return productCommonVo;
	}

	public void setProductCommonVo(ProductCommonVo productCommonVo) {
		this.productCommonVo = productCommonVo;
	}

	public ProductCommonVo getProductCommonReplaceVo() {
		return productCommonReplaceVo;
	}

	public void setProductCommonReplaceVo(ProductCommonVo productCommonReplaceVo) {
		this.productCommonReplaceVo = productCommonReplaceVo;
	}

}
