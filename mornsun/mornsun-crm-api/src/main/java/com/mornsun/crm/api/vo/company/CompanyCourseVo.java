package com.mornsun.crm.api.vo.company;

/**
 * 企业秒懂VO
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CompanyCourseVo {

	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司品牌
	 */
	private String companyBrand;
	/**
	 * 公司联系人
	 */
	private String mobile;
	/**
	 * 产品型号
	 */
	private String productNo;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 教程类别
	 */
	private String courseType;
	/**
	 * 附件名称
	 */
	private String attaName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyBrand() {
		return companyBrand;
	}

	public void setCompanyBrand(String companyBrand) {
		this.companyBrand = companyBrand;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getAttaName() {
		return attaName;
	}

	public void setAttaName(String attaName) {
		this.attaName = attaName;
	}

}
