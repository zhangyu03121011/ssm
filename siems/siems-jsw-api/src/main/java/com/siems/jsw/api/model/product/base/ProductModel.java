package com.siems.jsw.api.model.product.base;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 商品Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class ProductModel extends BaseModel  {

    /**
     * 商品名称
     */
    @Validate
    private String shopName;

    /**
     * 商品类别
     */
    @Validate
    private String shopTypeId;

    /**
     * 商品品牌
     */
    @Validate
    private String brandId;

    /**
     * 商品型号
     */
    @Validate
    private String shopNo;

    /**
     * 所属门店
     */
    @Validate
    private String shopId;

    /**
     * 操作系统
     */
    @Validate
    private String os;

    /**
     * 屏幕
     */
    @Validate
    private String screen;

    /**
     * 摄像头
     */
    @Validate
    private String camera;

    /**
     * 处理器
     */
    @Validate
    private String cpu;

    /**
     * 内存
     */
    @Validate
    private String memory;

    /**
     * 电池
     */
    @Validate
    private String battery;

    /**
     * 网络
     */
    @Validate
    private String network;

    /**
     * 颜色
     */
    @Validate
    private String color;

    /**
     * 是否公用：1-公用，0-不公用
     */
    @Validate
    private String isPublic;

    private static final long serialVersionUID = 1L;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(String shopTypeId) {
        this.shopTypeId = shopTypeId == null ? null : shopTypeId.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen == null ? null : screen.trim();
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera == null ? null : camera.trim();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu == null ? null : cpu.trim();
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory == null ? null : memory.trim();
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery == null ? null : battery.trim();
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

}
