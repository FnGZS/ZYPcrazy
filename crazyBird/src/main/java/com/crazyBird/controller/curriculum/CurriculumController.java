package com.crazyBird.controller.curriculum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {
	@Autowired 
	private CurriculumProcess curriculumProcess;
	/**
	 * 获取课程表
	 */
	@RequestMapping(value="/get",method = RequestMethod.GET)
	@ResponseBody
	public SimpleFlagModel getCurriculum() {
		return curriculumProcess.getCurriculum();
		
	}
	
}
