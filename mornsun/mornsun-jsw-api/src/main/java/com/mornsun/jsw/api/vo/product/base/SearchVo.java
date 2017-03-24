package com.mornsun.jsw.api.vo.product.base;

import java.util.List;

import com.mornsun.jsw.api.vo.brand.BrandVo;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;
import com.mornsun.jsw.api.vo.product.atta.ProductAttaVo;
import com.mornsun.jsw.api.vo.product.param.ProductParamVo;
import com.mornsun.jsw.api.vo.product.replace.ProductReplaceVo;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.jsw.api.vo.user.question.UserQuestionVo;

/**
 * 搜索Vo
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SearchVo extends ProductVo {

	private static final long serialVersionUID = 1L;

	/**
	 * 关键字
	 */
	private String keyword;

	/**
	 * 搜索记录ID
	 */
	private String searchRecordId;

	private BrandVo brandVo;

	private CatalogVo catalogVo;

	private ProductBaseVo productBaseVo;

	private List<ProductParamVo> productParamVos;

	private List<ProductTagVo> productTagVos;

	private List<ProductAttaVo> productAttaVos;

	private List<ProductReplaceVo> productReplaceVos;

	private List<ProductApplyAreaVo> productApplyAreaVos;

	private List<UserCourseVo> userCourseVos;

	private List<UserQuestionVo> userQuestionVos;

	private long productReplaceCount;
	
	private String isFavorite;

	public String getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(String isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ProductBaseVo getProductBaseVo() {
		return productBaseVo;
	}

	public void setProductBaseVo(ProductBaseVo productBaseVo) {
		this.productBaseVo = productBaseVo;
	}

	public List<ProductParamVo> getProductParamVos() {
		return productParamVos;
	}

	public void setProductParamVos(List<ProductParamVo> productParamVos) {
		this.productParamVos = productParamVos;
	}

	public List<ProductTagVo> getProductTagVos() {
		return productTagVos;
	}

	public void setProductTagVos(List<ProductTagVo> productTagVos) {
		this.productTagVos = productTagVos;
	}

	public List<ProductAttaVo> getProductAttaVos() {
		return productAttaVos;
	}

	public void setProductAttaVos(List<ProductAttaVo> productAttaVos) {
		this.productAttaVos = productAttaVos;
	}

	public List<ProductReplaceVo> getProductReplaceVos() {
		return productReplaceVos;
	}

	public void setProductReplaceVos(List<ProductReplaceVo> productReplaceVos) {
		this.productReplaceVos = productReplaceVos;
	}

	public List<ProductApplyAreaVo> getProductApplyAreaVos() {
		return productApplyAreaVos;
	}

	public void setProductApplyAreaVos(List<ProductApplyAreaVo> productApplyAreaVos) {
		this.productApplyAreaVos = productApplyAreaVos;
	}

	public String getSearchRecordId() {
		return searchRecordId;
	}

	public void setSearchRecordId(String searchRecordId) {
		this.searchRecordId = searchRecordId;
	}

	public BrandVo getBrandVo() {
		return brandVo;
	}

	public void setBrandVo(BrandVo brandVo) {
		this.brandVo = brandVo;
	}

	public CatalogVo getCatalogVo() {
		return catalogVo;
	}

	public void setCatalogVo(CatalogVo catalogVo) {
		this.catalogVo = catalogVo;
	}

	public List<UserCourseVo> getUserCourseVos() {
		return userCourseVos;
	}

	public void setUserCourseVos(List<UserCourseVo> userCourseVos) {
		this.userCourseVos = userCourseVos;
	}

	public List<UserQuestionVo> getUserQuestionVos() {
		return userQuestionVos;
	}

	public void setUserQuestionVos(List<UserQuestionVo> userQuestionVos) {
		this.userQuestionVos = userQuestionVos;
	}

	public long getProductReplaceCount() {
		return productReplaceCount;
	}

	public void setProductReplaceCount(long productReplaceCount) {
		this.productReplaceCount = productReplaceCount;
	}

}
