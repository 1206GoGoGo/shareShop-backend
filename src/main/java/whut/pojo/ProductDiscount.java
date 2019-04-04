package whut.pojo;

import java.io.Serializable;

/**
 * 商品折扣
 * @author wangql
 *
 */
public class ProductDiscount implements Serializable{
    private Integer discountId; //折扣ID

    private Integer categoryId;  //商品分类id

    private Byte discountRate;  //折扣率
    
    private Byte sellerDiscountRate; //seller折扣率

    private Byte yieldRate;  //收益率

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

    public Byte getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Byte discountRate) {
        this.discountRate = discountRate;
    }
    
    public Byte getSellerDiscountRate() {
        return sellerDiscountRate;
    }

    public void setSellerDiscountRate(Byte sellerDiscountRate) {
        this.sellerDiscountRate = sellerDiscountRate;
    }

    public Byte getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(Byte yieldRate) {
        this.yieldRate = yieldRate;
    }
}