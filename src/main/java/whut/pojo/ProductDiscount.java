package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品折扣
 * @author wangql
 *
 */
public class ProductDiscount implements Serializable{
    private Integer discountId; //折扣ID

    private Integer categoryId;  //商品分类id

    private BigDecimal discountRate;  //折扣率
    
    private BigDecimal sellerDiscountRate; //seller折扣率

    private BigDecimal yieldRate;  //收益率

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getSellerDiscountRate() {
        return sellerDiscountRate;
    }

    public void setSellerDiscountRate(BigDecimal sellerDiscountRate) {
        this.sellerDiscountRate = sellerDiscountRate;
    }

    public BigDecimal getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }
}