package whut.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SellerBill implements Serializable{
	private BigDecimal money; //账单金额
	
	private Date createTime;  //时间
	
	private String type;  //支出 收入
	
	
	public BigDecimal getMoney() {
	      return money;
	}

	public void setMoney(BigDecimal money) {
	     this.money = money;
	}
	    
	public Date getCreateTime() {
	     return createTime;
	}

	public void setCreateTime(Date createTime) {
	     this.createTime = createTime;
	}
	
	public String getType() {
	     return type;
    }

	public void setType(String type) {
	    this.type = type == null ? null : type.trim();
	}
	
}
