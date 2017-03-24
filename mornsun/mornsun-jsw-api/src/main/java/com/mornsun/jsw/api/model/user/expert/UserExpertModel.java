package com.mornsun.jsw.api.model.user.expert;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 用户专家Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class UserExpertModel extends BaseModel  {

    /**
     * 用户ID
     */
    @Validate
    private String userId;

    /**
     * 姓名
     */
    @Validate
    private String name;

    /**
     * 所在城市
     */
    @Validate
    private String city;

    /**
     * 行业
     */
    @Validate
    private String industry;

    /**
     * 任职机构
     */
    @Validate
    private String company;

    /**
     * 头衔ID
     */
    @Validate
    private String positionId;

    /**
     * 认证费用
     */
    private Double expertMoney;

    /**
     * 回答问题数
     */
    private Integer answerCount;

    /**
     * 收益金额
     */
    private Double incomeMoney;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    public Double getExpertMoney() {
        return expertMoney;
    }

    public void setExpertMoney(Double expertMoney) {
        this.expertMoney = expertMoney;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Double getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(Double incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

}
