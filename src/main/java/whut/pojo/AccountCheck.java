package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *账目核对表
 * @author wangql
 *
 */
public class AccountCheck implements Serializable{
    private Integer accountCheckId;  //账目核对id

    private String thirdPartyNo;//第三方支付订单号

    private String transactionCode;//交易单号

    private BigDecimal money;//金额

    private Byte type;//类型

    private Integer correlationId;//关联id

    private String notes;//备注

    private Byte status;//状态

    public Integer getAccountCheckId() {
        return accountCheckId;
    }

    public void setAccountCheckId(Integer accountCheckId) {
        this.accountCheckId = accountCheckId;
    }

    public String getThirdPartyNo() {
        return thirdPartyNo;
    }

    public void setThirdPartyNo(String thirdPartyNo) {
        this.thirdPartyNo = thirdPartyNo == null ? null : thirdPartyNo.trim();
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode == null ? null : transactionCode.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(Integer correlationId) {
        this.correlationId = correlationId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}