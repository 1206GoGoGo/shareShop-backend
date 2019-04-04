package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 州税表
 * @author wangql
 *
 */
public class StateTax implements Serializable{
    private Integer stateTaxId; //州税id

    private String state;  //州名称

    private BigDecimal taxRate; //税率

    public Integer getStateTaxId() {
        return stateTaxId;
    }

    public void setStateTaxId(Integer stateTaxId) {
        this.stateTaxId = stateTaxId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

}