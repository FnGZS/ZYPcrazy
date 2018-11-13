package com.crazyBird.controller.vote;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.vote.model.VoteActionCheckRecordModel;
import com.crazyBird.controller.vote.model.VoteActionCustomModel;
import com.crazyBird.controller.vote.model.VoteActionDetailItem;
import com.crazyBird.controller.vote.model.VoteActionDetailListModel;
import com.crazyBird.controller.vote.model.VoteActionDetailModel;
import com.crazyBird.controller.vote.model.VoteActionDetailRankModel;
import com.crazyBird.controller.vote.model.VoteActionHotListModel;
import com.crazyBird.controller.vote.model.VoteActionListModel;
import com.crazyBird.controller.vote.model.VoteActionRecordModel;
import com.crazyBird.controller.vote.model.VoteActionSlideModel;
import com.crazyBird.controller.vote.param.VoteActionDetailListParam;
import com.crazyBird.controller.vote.param.VoteActionParam;
import com.crazyBird.controller.vote.param.VoteActionRecordParam;
import com.crazyBird.controller.vote.param.VoteActionSearchDetailParam;
import com.crazyBird.controller.vote.param.VoteRecordParam;
import com.crazyBird.service.vote.VoteService;

/**
 * 
 * @author zhengzc
 * @date 2018年9月25日18:46:47
 *
 */
@Controller
@RequestMapping("/vote")
public class VoteController {
	@Autowired
	private VoteProcess voteProcess;
	/**
	 * 获得活动列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getAction",method = RequestMethod.POST)
	@ResponseBody
	public VoteActionListModel getActionList(@RequestBody VoteActionParam param){
		return voteProcess.getActionList(param);
	}
	/**
	 * 获得活动详情
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/getAction/detail/{id}",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionDetailListModel getActionDetail(@PathVariable Long id) {
		return voteProcess.getActionDetailList(id);
	}
	/**
	 * 投票
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	@ResponseBody
	public SimpleFlagModel createVoteDetailNum(@RequestBody VoteActionDetailListParam param) {
		return voteProcess.createVoteDetailNum(param);	
	}
	/**
	 * 获得三个热门活动
	 * @return
	 */
	@RequestMapping(value = "/getAction/hot",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionHotListModel getVoteActionHotList() {
		return voteProcess.getVoteActionHotList();	
	}
	/**
	 * 获得特别推荐的活动
	 * @return
	 */
	@RequestMapping(value = "/getAction/special",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionHotListModel getVoteActionHot() {
		return voteProcess.getVoteActionHot();	
	}
	/**
	 * 获得投票界面幻灯片图
	 */
	@RequestMapping(value = "/getAction/slide",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionSlideModel getVoteActionSlide() {
		return voteProcess.getVoteActionSlide();	
	}
	/**
	  * 搜索候选人
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	@ResponseBody
	public VoteActionDetailRankModel searchActionDeatil(@RequestBody VoteActionSearchDetailParam param) {
		return voteProcess.selectActionDetailByName(param);
	}
	/**
	 * 获取单个候选人信息
	 */
	@RequestMapping(value = "/search/{id}",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionCustomModel searchActionDeatilById(@PathVariable Long id) {
		return voteProcess.searchActionDeatilById(id);
	}
	/**
	 * 获得活动详情排名
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/getAction/detail/rank/{id}",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionDetailRankModel getActionDetailByRank(@PathVariable Long id) {
		return voteProcess.getActionDetailByRank(id);
	}
	/**
	 * 检测投票
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/check",method = RequestMethod.POST)
	@ResponseBody
	public VoteActionCheckRecordModel checkVoteRecord(@RequestBody VoteRecordParam param) {
		return voteProcess.checkVoteRecord(param);	
	}
	/**
	 * 我参与的投票
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/record",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionRecordModel getVoteActionRecord(VoteActionRecordParam param) {
		return voteProcess.getVoteActionRecord(param);	
	}
	
}
