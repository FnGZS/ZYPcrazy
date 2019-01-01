package com.crazyBird.controller.opinion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.opinion.model.OpinionCreatModel;
import com.crazyBird.controller.opinion.param.OpinionParam;


@Controller
@RequestMapping("/opinion")
public class OpinionController {

	@Autowired
	private OpinionProcess opinionProcess;
	
	/**
	 * 添加意见信息
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/creat", method = RequestMethod.POST)
	@ResponseBody
	public OpinionCreatModel creat(@RequestBody OpinionParam param) {
		return opinionProcess.creat(param);
	}
}
