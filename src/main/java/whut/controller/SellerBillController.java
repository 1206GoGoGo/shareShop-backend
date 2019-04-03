package whut.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import whut.pojo.WithdrawRecord;
import whut.pojo.YieldDetail;
import whut.service.SellerBillService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/seller/bill")
public class SellerBillController {

	@Autowired
	public SellerBillService sellerBillService;
	
	//获取某一seller的账户余额变动信息
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(String id,int pageindex, int pagesize) {
		return sellerBillService.getList(id,pageindex,pagesize);
	}
	
	//获取某一seller所有的提现记录
	@RequestMapping(value = "/getWithdrawList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getWithdrawList(String id,int pageindex, int pagesize) {
		return sellerBillService.getWithdrawList(id,pageindex,pagesize);
	}
	
	//获取某一seller所有的收益记录
	@RequestMapping(value = "/getYieldList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getYieldList(String id,int pageindex, int pagesize) {
		return sellerBillService.getYieldList(id,pageindex,pagesize);
	}
	
	//添加提现记录
	@RequestMapping(value = "/addWithdraw", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseData addWithdraw(@RequestBody WithdrawRecord withdrawRecord) {
		return sellerBillService.addWithdraw(withdrawRecord);
		
	}
	
	//添加收益记录
	@RequestMapping(value = "/addYield", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseData addYield(@RequestBody YieldDetail yieldDetail) {
		return sellerBillService.addYield(yieldDetail);
		
	}
}
