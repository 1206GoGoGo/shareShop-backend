package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 店主提现记录
 * @author wangql
 *
 */
public class WithdrawRecord implements Serializable{
    private Integer withdrawId;  //提现记录ID

    private Integer userId;  //用户id

    private BigDecimal withdrawMoney;  //提现金额
    
    private String notes;//备注

    private Date createTime; //提现时间

    private Byte status; //提现状态

    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
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