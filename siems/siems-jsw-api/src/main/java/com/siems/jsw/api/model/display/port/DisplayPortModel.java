package com.siems.jsw.api.model.display.port;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 陈列区域端口Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class DisplayPortModel extends BaseModel  {

    /**
     * 区域ID
     */
    @Validate
    private String displayId;

    /**
     * 硬件ID
     */
    @Validate
    private String hardwareId;

    /**
     * 端口编号
     */
    @Validate
    private String portNo;

    /**
     * 品牌ID
     */
    @Validate
    private String brandId;

    /**
     * 商品ID
     */
    @Validate
    private String shopId;

    /**
     * 多媒体类别：1-图片，2-视频，3-声音
     */
    @Validate
    private String mediaType;

    /**
     * 多媒体ID
     */
    @Validate
    private String mediaId;

    private static final long serialVersionUID = 1L;

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId == null ? null : displayId.trim();
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId == null ? null : hardwareId.trim();
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo == null ? null : portNo.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType == null ? null : mediaType.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

}
