package com.crazyBird.controller.luck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.luck.model.LuckDetailsModel;
import com.crazyBird.controller.luck.model.LuckListModel;
import com.crazyBird.controller.luck.model.LuckPartakeModel;
import com.crazyBird.controller.luck.model.LuckWinnersModel;
import com.crazyBird.controller.luck.param.LuckListPageParam;
import com.crazyBird.controller.luck.param.LuckPartakePageParam;
import com.crazyBird.controller.luck.param.LuckWinnersPageParam;

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
	@RequestMapping(value ="/luckWinner", method = RequestMethod.GET)
	@ResponseBody
	public LuckWinnersModel getLuckWinners(LuckWinnersPageParam param) {
		return luckProcess.getLuckWinners(param);
	}
	
	/**
	 * 获取参与者名单
	 * @param
	 * @return
	 * */
	@RequestMapping(value ="/luckPartake", method = RequestMethod.GET)
	@ResponseBody
	public LuckPartakeModel getLuckPartake(LuckPartakePageParam param) {
		return luckProcess.getLuckPartake(param);
	}
	
	/**
	 * 获取抽奖详情
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/luckDetails", method = RequestMethod.GET)
	@ResponseBody
	public LuckDetailsModel getLuckDetails(Long luckId) {
		return luckProcess.getLuckDetails(luckId);
	}
	
	
	
}
