package com.crazyBird.controller.luck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.luck.model.LuckListModel;
import com.crazyBird.controller.luck.param.LuckListPageParam;

@Controller
@RequestMapping("/luck")
public class LuckController {

	@Autowired
	private LuckProcess luckProcess;
	
	/**
	 * 获取抽奖列表信息
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/luckList", method = RequestMethod.GET)
	@ResponseBody
	public LuckListModel getLuckList(LuckListPageParam param) {
		return luckProcess.getLuckList(param);
	}
	
	/**
	 * 获取中奖者名单
	 * @param param
	 * @return
	 * */
	
}
