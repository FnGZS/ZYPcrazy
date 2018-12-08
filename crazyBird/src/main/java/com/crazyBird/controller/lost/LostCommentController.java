package com.crazyBird.controller.lost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.lost.model.LostCommentPageModel;
import com.crazyBird.controller.lost.model.LostInputModel;
import com.crazyBird.controller.lost.param.LostCommentPageParam;
import com.crazyBird.controller.lost.param.LostCommentParam;


@Controller
@RequestMapping("/lost")
public class LostCommentController {
	
	@Autowired
	private LostCommentProcess lostCommentProcess;
	
	/**
	 * 用户评论
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/lostCommentIn", method = RequestMethod.POST)
	@ResponseBody
	public LostInputModel setLostCommentIn(@RequestBody LostCommentParam param) {
		return lostCommentProcess.lostCommentIn(param);
	}
	
	/**
	 * 获取详情品论评论
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/getLostComment", method = RequestMethod.GET)
	@ResponseBody
	public LostCommentPageModel getLostComment(LostCommentPageParam param) {
		return lostCommentProcess.getLostCommentList(param);
	}
}
