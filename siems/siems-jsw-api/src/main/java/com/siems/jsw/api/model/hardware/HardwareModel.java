package com.siems.jsw.api.model.hardware;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 硬件Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class HardwareModel extends BaseModel  {

    /**
     * 设备编号
     */
    @Validate
    private String hardwareNo;

    /**
     * 设备名称
     */
    @Validate
    private String hardwareName;

    /**
     * 设备类型
     */
    @Validate
    private String type;

    /**
     * 设备型号
     */
    @Validate
    private String hardwareModel;

    /**
     * 设备供应商
     */
    @Validate
    private String hardwareVendor;

    /**
     * 所属客户
     */
    @Validate
    private String customerId;

    /**
     * 所属门店
     */
    @Validate
    private String shopId;

    private static final long serialVersionUID = 1L;

    public String getHardwareNo() {
        return hardwareNo;
    }

    public void setHardwareNo(String hardwareNo) {
        this.hardwareNo = hardwareNo == null ? null : hardwareNo.trim();
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName == null ? null : hardwareName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getHardwareModel() {
        return hardwareModel;
    }

    public void setHardwareModel(String hardwareModel) {
        this.hardwareModel = hardwareModel == null ? null : hardwareModel.trim();
    }

    public String getHardwareVendor() {
        return hardwareVendor;
    }

    public void setHardwareVendor(String hardwareVendor) {
        this.hardwareVendor = hardwareVendor == null ? null : hardwareVendor.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

}
