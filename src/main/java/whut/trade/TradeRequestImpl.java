package whut.trade;

import org.springframework.stereotype.Component;

@Component
public class TradeRequestImpl implements TradeRequest {

	@Override
	public String tradePay(String tradeNo, String scene, String subject, String totalAmount, String body, String payWay,
			String operatorId, String timeoutExpress) {
		// TODO Auto-generated method stub
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
