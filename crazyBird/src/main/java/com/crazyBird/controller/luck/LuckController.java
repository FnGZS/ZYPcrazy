package com.crazyBird.controller.luck;
/**
 * 
 * @author zjw
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.luck.model.AddLuckModel;
import com.crazyBird.controller.luck.model.AddPrizeModel;
import com.crazyBird.controller.luck.model.LuckAdvertisementModel;
import com.crazyBird.controller.luck.model.LuckDetailsModel;
import com.crazyBird.controller.luck.model.LuckIsPartModel;
import com.crazyBird.controller.luck.model.LuckListModel;
import com.crazyBird.controller.luck.model.LuckPartakeModel;
import com.crazyBird.controller.luck.model.LuckPrizeModel;
import com.crazyBird.controller.luck.model.LuckWinnersModel;
import com.crazyBird.controller.luck.param.AddLuckParam;
import com.crazyBird.controller.luck.param.AwardParam;
import com.crazyBird.controller.luck.param.DeleaseParam;
import com.crazyBird.controller.luck.param.IsPartParam;
import com.crazyBird.controller.luck.param.JoinListParam;
import com.crazyBird.controller.luck.param.LuckListPageParam;
import com.crazyBird.controller.luck.param.LuckPartakePageParam;
import com.crazyBird.controller.luck.param.LuckPrizeParam;
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
	
	/**
	 * 是否已参加
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/isPart", method = RequestMethod.POST)
	@ResponseBody
	public LuckIsPartModel getIsPart(@RequestBody IsPartParam param) {
		return luckProcess.getIsPart(param);
	}
	
	/**
	 * 添加奖品
	 * **/
	@RequestMapping(value ="/addPrize", method = RequestMethod.POST)
	@ResponseBody
	public AddPrizeModel AddPrize(@RequestBody LuckPrizeParam param) {
		return luckProcess.AddPrize(param);
	}
	
	/**
	 * 添加抽奖
	 * **/
	@RequestMapping(value ="/addLuck", method = RequestMethod.POST)
	@ResponseBody
	public AddLuckModel AddLuck(@RequestBody AddLuckParam param) {
		return luckProcess.AddLuck(param);
	}
	/**
	 * 广告
	 * */
	@RequestMapping(value ="/advertisement", method = RequestMethod.GET)
	@ResponseBody
	public LuckAdvertisementModel Advertisement() {
		return luckProcess.Advertisement();
	}
	
	/**
	 * 我发布的抽奖
	 * */
	@RequestMapping(value ="/delease", method = RequestMethod.GET)
	@ResponseBody
	public LuckListModel delease(DeleaseParam param) {
		return luckProcess.delease(param);
	}
	
	/**
	 * 我参与的抽奖
	 * */
	@RequestMapping(value ="/joinList", method = RequestMethod.GET)
	@ResponseBody
	public LuckListModel joinList(JoinListParam param) {
		return luckProcess.getJoinList(param);
	}
	
	/**
	 * 我中奖的记录
	 * **/
	@RequestMapping(value ="/award", method = RequestMethod.GET)
	@ResponseBody
	public LuckPrizeModel awardList(AwardParam param) {
		return luckProcess.awardList(param);
	}
	
	/**
	 * 抽奖
	 * */
	
}
