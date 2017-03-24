package com.mornsun.jsw.api.model.company.course;

import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 企业秒懂Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class CompanyCourseModel extends BaseModel {

	/**
	 * 企业ID
	 */
	@Validate
	private String companyId;

	/**
	 * 产品ID
	 */
	private String productId;

	/**
	 * 标题
	 */
	@Validate
	private String title;

	/**
	 * 支付金额
	 */
	@Validate
	private double payMoney;

	/**
	 * 教程类别：1-文字，2-声音，3-视频，4-图片
	 */
	@Validate
	private String courseType;

	/**
	 * 阅读人数
	 */
	private Integer readCount;

	/**
	 * 点赞数
	 */
	private int praiseCount;

	private static final long serialVersionUID = 1L;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId == null ? null : companyId.trim();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId == null ? null : productId.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType == null ? null : courseType.trim();
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

}
