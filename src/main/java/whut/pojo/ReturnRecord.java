package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退货/款记录
 * @author wangql
 *
 */
public class ReturnRecord implements Serializable{
    private Integer returnId; //退货id

    private Integer userId; // 用户id
    
    private Integer orderId;//订单id

    private Integer orderDetailId; // 订单详情id

    private Integer productSpecsId;  //单品ID

    private Byte returnType; // 0:退货   1:退款

    private BigDecimal returnMoney; //退款金额

    private Date createTime;  //申请时间

    private Byte status;  //申请退货21、同意退货22：1.收到商品退货完成（分为整单退货、单个商品退货）29
    					  //					   2.商品不符合退货失败（待发货）23、已发货24、已收货结束售后25

    private String reason;  //退货原因

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
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

    public Integer getProductSpecsId() {
        return productSpecsId;
    }

    public void setProductSpecsId(Integer productSpecsId) {
        this.productSpecsId = productSpecsId;
    }

    public Byte getReturnType() {
        return returnType;
    }

    public void setReturnType(Byte returnType) {
        this.returnType = returnType;
    }

    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}