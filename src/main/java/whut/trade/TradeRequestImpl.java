package whut.trade;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import whut.dao.AccountCheckDao;
import whut.pojo.AccountCheck;

@Component
public class TradeRequestImpl implements TradeRequest {
	
	@Autowired
	private AccountCheckDao accountCheckDao;
	
//	type字段1表示用户支付   2表示用户退款  3表示seller提现
//    correlation_id字段可以是订单号、退款编号、提现编号
//    status字段1表示成功  0表示失败

	@Override
	//商户订单号、支付场景(手机、电脑等)、订单标题、总金额、类型（退换货）、对应类型的id、订单描述、支付方式、操作员id、订单过期时间(5分钟--)
	public String tradePay(String tradeNo ,String scene, String subject, BigDecimal totalAmount, Byte type, Integer correlationId,
			String body, String payWay, String operatorId, String timeoutExpress) {
		
		//插入对账表，该表中还有id,thirdPartyNo两个字段
		//对状态的修改，第三方单号的插入等炒作，都通过商家订单号字段完成。
		AccountCheck accountCheck = new AccountCheck();
		accountCheck.setTransactionCode(tradeNo);
		accountCheck.setMoney(totalAmount);
		accountCheck.setType(type);
		accountCheck.setCorrelationId(correlationId);
		accountCheck.setStatus((byte) 2);
		accountCheck.setNotes(subject);		//备注
		accountCheckDao.add(accountCheck);
		
		return null;
	}

	@Override
	public String tradeQuery(String tradeNo, String PayNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tradeRefund(String tradeNo, String PayNo, String refundAmount, String refundReason,
			String outRequestNo, String GoodsInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
