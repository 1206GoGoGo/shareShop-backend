package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券消费记录表
 * @author wangql
 *
 */
public class CouponLogs implements Serializable{
    private Integer id; //消费id

    private Integer userId;//用户id

    private Integer couponId;//优惠券id

    private Long orderNumber;//订单编号

    private BigDecimal orderOriginalMoney; //原订单金额

    private BigDecimal couponMoney;//优惠券面值

    private BigDecimal orderFinalMoney;//付款金额

    private BigDecimal fullMoney;//金额满多少

    private Date time;//消费时间

    private Byte status;//状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderOriginalMoney() {
        return orderOriginalMoney;
    }

    public void setOrderOriginalMoney(BigDecimal orderOriginalMoney) {
        this.orderOriginalMoney = orderOriginalMoney;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getOrderFinalMoney() {
        return orderFinalMoney;
    }

    public void setOrderFinalMoney(BigDecimal orderFinalMoney) {
        this.orderFinalMoney = orderFinalMoney;
    }

    public BigDecimal getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(BigDecimal fullMoney) {
        this.fullMoney = fullMoney;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}