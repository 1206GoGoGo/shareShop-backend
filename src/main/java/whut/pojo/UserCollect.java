package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商品收藏
 * @author wangql
 *
 */
public class UserCollect implements Serializable{
    private Integer collectId; //收藏ID

    private Integer userId;  //用户id

    private Integer productId;  //商品id

    private Date collectTime;  //收藏时间
    
    private String productName;//商品名称
    
    private String mainImage;//主图
    
    private Byte publishStatus;//上下架状态
    
    private String description;//商品描述
    
    private BigDecimal minPrice;//销售价格

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Byte getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Byte publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}    
    
}