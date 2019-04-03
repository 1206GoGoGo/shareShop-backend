package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ManagerInfo;
import whut.service.ManagerInfoService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/manager/info")
public class ManagerInfoController {
	@Autowired
	private ManagerInfoService managerInfoService;
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList(int status) {
		return  managerInfoService.getList(status);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseData add(@RequestBody ManagerInfo managerInfo) {
		return  managerInfoService.add(managerInfo);
	}
	
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseData getDetail(int id) {
		return  managerInfoService.getDetail(id);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody ResponseData modify(@RequestBody ManagerInfo managerInfo) {
		return  managerInfoService.modify(managerInfo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseData delete(@RequestBody String jsonString) {
		return  managerInfoService.delete(jsonString);
	}
}
