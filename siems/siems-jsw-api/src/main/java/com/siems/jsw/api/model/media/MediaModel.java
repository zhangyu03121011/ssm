package com.siems.jsw.api.model.media;


import com.common.base.annotation.Validate;
import com.common.base.model.base.BaseModel;

/**
 * 多媒体Model
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public class MediaModel extends BaseModel  {

    /**
     * 多媒体名称
     */
    @Validate
    private String mediaName;

    /**
     * 多媒体类别：1-图片，2-视频，3-声音
     */
    @Validate
    private String mediaType;

    /**
     * 品牌ID
     */
    @Validate
    private String brandId;

    /**
     * 商品ID
     */
    @Validate
    private String productId;

    /**
     * 是否公用：1-公用，0-不公用
     */
    @Validate
    private String isPublic;

    private static final long serialVersionUID = 1L;

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName == null ? null : mediaName.trim();
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType == null ? null : mediaType.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

}
