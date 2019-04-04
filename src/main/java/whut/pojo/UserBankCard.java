package whut.pojo;

import java.io.Serializable;


/**
 * 用户银行卡
 * @author wangql
 *
 */
public class UserBankCard implements Serializable{
    private Integer cardId;//用户银行卡id

    private Integer userId;//用户id

    private String cardNumber;//卡号

    private Byte type;//卡的类型

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}