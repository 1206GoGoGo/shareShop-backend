package whut.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品信息表
 * @author wangql
 *
 */
public class ProductInfo implements Serializable{
    private Integer productId;//商品ID

    private String productName;//商品名称

    private String brandName;//品牌
    
    private String spu;//商品spu

    private Integer oneCategoryId;//一级分类ID

    private Integer twoCategoryId;//二级分类ID

    private Integer threeCategoryId;//三级分类ID

    private String mainImage;//主图

    private String attributeList;//属性列表

    private Byte publishStatus;//上下架状态

    private Byte auditStatus;//审核状态

    private Byte useCoupon;//是否可以使用优惠券

    private Integer discountRate;//折扣比率

    private Date productionDate;//生产日期

    private String description;//商品描述

    private Integer stock;//商品库存

    private Date inputTime;//商品录入时间

    private Date modifiedTime;//商品修改时间
    
    private List<ProductSpecs> productSpecs; //商品规格(单品详情)


	public List<ProductSpecs> getProductSpecs() {
		return productSpecs;
	}

	public void setProductSpecs(List<ProductSpecs> productSpecs) {
		this.productSpecs = productSpecs;
	}

	public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }
    
    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu == null ? null : spu.trim();
    }

    public Integer getOneCategoryId() {
        return oneCategoryId;
    }

    public void setOneCategoryId(Integer oneCategoryId) {
        this.oneCategoryId = oneCategoryId;
    }

    public Integer getTwoCategoryId() {
        return twoCategoryId;
    }

    public void setTwoCategoryId(Integer twoCategoryId) {
        this.twoCategoryId = twoCategoryId;
    }

    public Integer getThreeCategoryId() {
        return threeCategoryId;
    }

    public void setThreeCategoryId(Integer threeCategoryId) {
        this.threeCategoryId = threeCategoryId;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(String attributeList) {
        this.attributeList = attributeList == null ? null : attributeList.trim();
    }

    public Byte getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Byte publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Byte getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(Byte useCoupon) {
        this.useCoupon = useCoupon;
    }

    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}