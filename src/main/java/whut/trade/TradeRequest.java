package whut.trade;

public interface TradeRequest {
	
	//商户订单号、支付场景(手机、电脑等)、订单标题、总金额、订单描述、支付方式、操作员id、订单过期时间(5分钟--)
	public String tradePay(String tradeNo ,String scene, String subject, String totalAmount, String body, 
			String payWay, String operatorId, String timeoutExpress) ;
	
	//商户订单编号、支付系统生成的订单号（二选一）
	public String tradeQuery(String tradeNo,String PayNo) ;
	
	//商户订单编号、支付系统生成的订单号（二选一）、退款金额、退款原因、同一订单多次退款编号（订单部分退款）、退款商品信息
	public String tradeRefund(String tradeNo,String PayNo, String refundAmount, String refundReason,String outRequestNo,String GoodsInfo) ;
	

}
