package com.crazyBird.controller.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 二手市场 用户
 * @author zjw
 *
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.secondary.model.SellSecondaryListModel;
import com.crazyBird.controller.secondary.param.SellSecondaryParam;

@Controller
@RequestMapping("/secondary")
public class SecondaryUserController {
	
	@Autowired
	private SecondaryUserProcess secondaryUserProcess;
	
	/**
	 * 我卖出的
	 * @param id
	 * **/
	@ResponseBody
	@RequestMapping(value="/sellList",method=RequestMethod.GET)
	public SellSecondaryListModel getSecondarySellListByUser(SellSecondaryParam Param) {
		return secondaryUserProcess.getSellList(Param);
	}
}
