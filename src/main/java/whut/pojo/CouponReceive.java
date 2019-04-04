package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券领取表
 * @author wangql
 *
 */
public class CouponReceive implements Serializable{
    private Integer id;//优惠券领取id

    private Integer userId;//用户id

    private Integer couponId;//优惠券id

    private BigDecimal couponMoney;//优惠券面值

    private BigDecimal fullMoney;//金额满多少

    private Date receiveTime;//领取时间

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

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(BigDecimal fullMoney) {
        this.fullMoney = fullMoney;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}