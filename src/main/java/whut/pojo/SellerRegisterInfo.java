package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.omg.PortableInterceptor.ServerRequestInfo;

/**
 * 店主注册信息
 * @author wangql
 *
 */
public class SellerRegisterInfo implements Serializable{
    private Integer registerId; //注册信息ID

    private Integer userId;//用户id

    private BigDecimal payment;//缴费金额

    private Date time;//注册时间

    private String cardNumber;//提现卡号

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }
}