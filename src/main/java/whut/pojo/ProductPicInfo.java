package whut.pojo;

import java.io.Serializable;

/**
 * 商品图片
 * @author wangql
 *
 */
public class ProductPicInfo implements Serializable{
    private Integer productPicId; //商品图片ID

    private Integer productId;  //商品ID

    private String picDesc;  //图片描述

    private String picUrl;  //图片URL

    private Byte isMaster;  //是否是主图

    private Byte picStatus; //图片状态

    private Byte picOrder;  //图片排序

    public Integer getProductPicId() {
        return productPicId;
    }

    public void setProductPicId(Integer productPicId) {
        this.productPicId = productPicId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPicDesc() {
        return picDesc;
    }

    public void setPicDesc(String picDesc) {
        this.picDesc = picDesc == null ? null : picDesc.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Byte getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Byte isMaster) {
        this.isMaster = isMaster;
    }

    public Byte getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(Byte picStatus) {
        this.picStatus = picStatus;
    }

    public Byte getPicOrder() {
        return picOrder;
    }

    public void setPicOrder(Byte picOrder) {
        this.picOrder = picOrder;
    }
}