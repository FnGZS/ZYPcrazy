package com.crazyBird.controller.innovate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.innovate.model.InnovateBackgroundModel;
import com.crazyBird.controller.innovate.model.InnovateEnterpriseListModel;
import com.crazyBird.controller.innovate.model.InnovateEnterpriseModel;
/**
 * 
 * @author zzc
 * 
 */
@Controller
@RequestMapping("/innovate")
public class InnovateController {
	@Autowired InnovateProcess innovateProcess;
		
	/**
	 * 得到企业列表
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	@ResponseBody
	public InnovateEnterpriseListModel getInnovateEnterpriseList() {
		return innovateProcess.getInnovateEnterpriseList();
	}
	/**
	 * 得到企业详情
	 */
	@RequestMapping(value="/list/{id}",method = RequestMethod.GET)
	@ResponseBody
	public InnovateEnterpriseModel getInnovateEnterprise(@PathVariable Integer id) {
		return innovateProcess.getInnovateEnterprise(id);
	}
	/**
	 * 得到创业园首页背景图片
	 */
	@RequestMapping(value="/background",method = RequestMethod.GET)
	@ResponseBody
	public InnovateBackgroundModel  getInnovateBackground() {
		return innovateProcess.getInnovateBackground();
		
	}
	
}
