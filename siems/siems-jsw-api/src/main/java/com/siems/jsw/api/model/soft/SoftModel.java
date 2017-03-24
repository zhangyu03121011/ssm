package com.siems.jsw.api.model.soft;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 终端软件Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class SoftModel extends BaseModel  {

    /**
     * 软件名称
     */
    @Validate
    private String softName;

    /**
     * 版本号
     */
    @Validate
    private String versionNo;

    /**
     * 版本说明
     */
    @Validate
    private String versionDesc;

    /**
     * 安装数量
     */
    @Validate
    private Integer installNum;

    private static final long serialVersionUID = 1L;

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName == null ? null : softName.trim();
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo == null ? null : versionNo.trim();
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc == null ? null : versionDesc.trim();
    }

    public Integer getInstallNum() {
        return installNum;
    }

    public void setInstallNum(Integer installNum) {
        this.installNum = installNum;
    }

}
