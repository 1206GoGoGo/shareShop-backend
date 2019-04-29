package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.solr.client.solrj.beans.Field;

/**
 * 商品信息表
 * @author wangql
 *
 */
public class ProductInfoForSearch{
	
	@Field
    private Integer productId;//商品ID

	@Field
    private String productName;//商品名称

	@Field
    private String brandName;//品牌

	@Field
	private Integer oneCategoryId;//一级分类ID

	@Field
    private Integer twoCategoryId;//二级分类ID

	@Field
    private Integer threeCategoryId;//三级分类ID

	@Field
    private String mainImage;//主图

	@Field
    private String attributeList;//属性列表

	@Field
    private Integer publishStatus;//上下架状态

	@Field
    private Integer auditStatus;//审核状态

	@Field
    private Integer useCoupon;//是否可以使用优惠券

	@Field
    private Integer discountRate;//折扣比率

	@Field
    private Date productionDate;//生产日期

	@Field
    private String description;//商品描述

	@Field
    private Integer stock;//商品库存

	@Field
    private Date inputTime;//商品录入时间

	@Field
    private Date modifiedTime;//商品修改时间
    
    //private List<ProductSpecs> productSpecs; //商品规格(单品详情)
    
    //--计算出的数据
	@Field
	private int view;//用户浏览记录
	
	@Field
    private Double score;//商品评分
	
    //price
	@Field
    private BigDecimal minPrice;//最低价格
    
	@Field
    private BigDecimal maxPrice;//最高价格
    
	@Field
    private int collect;//收藏数
    
	@Field
    private int cart;//加入购物车数
	
	@Field
    private int sales;//出售的数量（一个月的/不含退货失败订单）

	public ProductInfoForSearch() {	}

	public ProductInfoForSearch(Integer productId, String productName, String brandName, Integer oneCategoryId,
			Integer twoCategoryId, Integer threeCategoryId, String mainImage, String attributeList, Integer publishStatus,
			Integer auditStatus, Integer useCoupon, Integer discountRate, Date productionDate, String description,
			Integer stock, Date inputTime, Date modifiedTime,int view, Double score, BigDecimal minPrice, BigDecimal maxPrice, int collect, int cart, int sales) {
		this.productId = productId;
		this.productName = productName;
		this.brandName = brandName;
		this.oneCategoryId = oneCategoryId;
		this.twoCategoryId = twoCategoryId;
		this.threeCategoryId = threeCategoryId;
		this.mainImage = mainImage;
		this.attributeList = attributeList;
		this.publishStatus = publishStatus;
		this.auditStatus = auditStatus;
		this.useCoupon = useCoupon;
		this.discountRate = discountRate;
		this.productionDate = productionDate;
		this.description = description;
		this.stock = stock;
		this.inputTime = inputTime;
		this.modifiedTime = modifiedTime;
		this.view = view;
		this.score = score;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.collect = collect;
		this.cart = cart;
		this.sales = sales;
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

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(Integer useCoupon) {
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
    

    public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}


	public int getCollect() {
		return collect;
	}

	public void setCollect(int collect) {
		this.collect = collect;
	}
	
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}


	public int getCart() {
		return cart;
	}

	public void setCart(int cart) {
		this.cart = cart;
	}
	
    
    public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

//	@Override
//    public String toString() {
//    	return null;
//    }
}