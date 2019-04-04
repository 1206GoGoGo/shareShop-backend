package whut.trade;

public interface SellerWithDraw {
	
	//用户提现接口
	//提现号、订单标题、总金额、支付方式、支付账号、操作员id
	public String commissionPay(String tradeNo ,String subject, String totalAmount,  String payWay, String payAccount, 
			String operatorId) ;
	
	//提现号、支付系统返回的订单号
	public String commissionQuery(String tradeNo,String PayNo) ;
	
	//批量付款接口

}
