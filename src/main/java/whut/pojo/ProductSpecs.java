package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品规格表
 * @author wangql
 *
 */
public class ProductSpecs implements Serializable{
    private Integer productSpecsId;//商品规格ID

    private Integer productId;//商品id

    private String name;//单品名称

    private String productCode;//商品编码

    private String specs;//单品的属性

    private Byte specsSort;//属性排序

    private String picUrl;//图片

    private BigDecimal averageCost;//成本

    private BigDecimal originalPrice;//原价

    private BigDecimal price;//销售价格

    private String description;//描述

    private Integer productStock;//单品库存

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Integer getProductSpecsId() {
        return productSpecsId;
    }

    public void setProductSpecsId(Integer productSpecsId) {
        this.productSpecsId = productSpecsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs == null ? null : specs.trim();
    }

    public Byte getSpecsSort() {
        return specsSort;
    }

    public void setSpecsSort(Byte specsSort) {
        this.specsSort = specsSort;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public BigDecimal getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(BigDecimal averageCost) {
        this.averageCost = averageCost;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}