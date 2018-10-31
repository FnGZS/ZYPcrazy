package com.admin.controller.vote;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.controller.base.SimpleFlagModel;
import com.admin.controller.vote.model.VoteActionDetailListModel;
import com.admin.controller.vote.model.VoteActionDetailRankModel;
import com.admin.controller.vote.model.VoteActionHotListModel;
import com.admin.controller.vote.model.VoteActionListModel;
import com.admin.controller.vote.model.VoteActionRecordModel;
import com.admin.controller.vote.param.VoteActionDetailParam;
import com.admin.controller.vote.param.VoteActionParam;
import com.admin.controller.vote.param.VoteActionStatusParam;
import com.admin.controller.vote.param.VoteRecordParam;
import com.admin.controller.vote.param.VoteActionRecordParam;
import com.admin.controller.vote.param.VoteActionSearchDetailParam;

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
	public VoteActionListModel getActionList(@RequestBody VoteActionStatusParam param){
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
	 * 获得进行中的三个热门活动
	 * @return
	 */
	@RequestMapping(value = "/getAction/hot",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionHotListModel getVoteActionHotList() {
		return voteProcess.getVoteActionHotList();	
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
	  *  获得活动详情排名
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getAction/detail/rank/{id}",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionDetailRankModel getActionDetailByRank(@PathVariable Long id) {
		return voteProcess.getActionDetailByRank(id);
	}
	/**
	 * 某个用户或活动投票记录
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	@ResponseBody
	public VoteActionRecordModel getVoteActionUserRecord(VoteActionRecordParam param) {
		return voteProcess.getVoteActionRecord(param);	
	}
	/**
	  * 添加活动
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/action/add",method = RequestMethod.POST)
	@ResponseBody
	public SimpleFlagModel addVoteAction(@RequestBody VoteActionParam param) {
		return null;
	}
	/**
	 * 添加活动详情(候选人)
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/detail/add",method = RequestMethod.POST)
	@ResponseBody
	public SimpleFlagModel addVoteActionDetail(@RequestBody VoteActionDetailParam param) {
		return null;
	}
	/**
	 * 修改活动
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/action/update",method = RequestMethod.PUT)
	@ResponseBody
	public SimpleFlagModel updateVoteAction(@RequestBody VoteActionParam param) {
		return null;
	}
	
	/**
	 * 修改活动详情(候选人)
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/detail/update",method = RequestMethod.PUT)
	@ResponseBody
	public SimpleFlagModel updateVoteActionDetail(@RequestBody VoteActionDetailParam param) {
		return null;
	}
	/**
	 * 删除活动
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public SimpleFlagModel daleteVoteAction(@PathVariable Long id) {
		return null;
	}
	/**
	 * 删除活动详情(候选人)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/detail/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public SimpleFlagModel daleteVoteActionDetail(@PathVariable Long id) {
		return null;
	}
}