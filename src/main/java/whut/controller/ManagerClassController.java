package whut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import whut.pojo.ManagerCategory;
import whut.service.ManagerClassService;
import whut.utils.ResponseData;

@Controller
@RequestMapping(value = "/manager/class")
public class ManagerClassController {
	
	@Autowired
	private ManagerClassService managerClassService;
	
	/**
	 * 获取列表
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public @ResponseBody ResponseData getList() {
		return managerClassService.getList();
	}
	/**
	 * 添加新的管理员
	 * @param managerCategory
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody ResponseData add(@RequestBody ManagerCategory managerCategory) {
		return managerClassService.add(managerCategory);
	}
	
	/**
	 * 修改管理员信息 ，包括分类
	 * @param managerCategory
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody ResponseData modify(@RequestBody ManagerCategory managerCategory) {
		return managerClassService.modify(managerCategory);
	}
	
	/**
	 * 删除某管理员
	 * jsonString包含删除分类——id
	 * 是否通过改状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseData delete(@RequestBody String jsonString) {
		return managerClassService.delete(jsonString);
	}
}
