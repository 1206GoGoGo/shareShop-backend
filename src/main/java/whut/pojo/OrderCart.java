package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *   购物车
 * @author wangql
 * 
 */
public class OrderCart implements Serializable{
	
    private Integer cartId; //购物车ID
    
    private Integer productId;//商品ID

    private Integer userId;  //用户ID

    private Integer productSpecsId; //单品ID

    private Integer productQuantity; //商品数量

    private BigDecimal productPrice;  //商品价格

    private Date addTime;  //加入购物车时间

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductSpecsId() {
        return productSpecsId;
    }

    public void setProductSpecsId(Integer productSpecsId) {
        this.productSpecsId = productSpecsId;
    }


    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}