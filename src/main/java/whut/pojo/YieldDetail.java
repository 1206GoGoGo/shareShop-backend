package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 店主收益详细
 * @author wangql
 *
 */
public class YieldDetail implements Serializable{
    private Integer yieldId; //收益ID

    private Integer userId;  //店主id

    private Integer orderId;  //订单id
    
    private Integer orderDetailId;//商品详情id

    private BigDecimal actualPaidMoney;//用户实际付款金额
    
    private BigDecimal receivedMoney; //实际所得金额

    private Byte yieldRate; //当前收益率

    private Integer purchaserId;  //买方用户id

    private Date createTime;  //收益时间
    
    private Byte status;//收益转态

    public Integer getYieldId() {
        return yieldId;
    }

    public void setYieldId(Integer yieldId) {
        this.yieldId = yieldId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public BigDecimal getActualPaidMoney() {
        return actualPaidMoney;
    }

    public void setActualPaidMoney(BigDecimal actualPaidMoney) {
        this.actualPaidMoney = actualPaidMoney;
    }

    public BigDecimal getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(BigDecimal receivedMoney) {
        this.receivedMoney = receivedMoney;
    }
    
    public Byte getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(Byte yieldRate) {
        this.yieldRate = yieldRate;
    }

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}