package com.admin.controller.affairs;


import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.controller.affairs.model.AffairsPageModel;
import com.admin.controller.affairs.param.AffairsPageParam;
import com.admin.controller.affairs.model.AddAffairsModel;
import com.admin.controller.affairs.param.AddAffairsParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Type AffairsController.java
 * @Desc
 * @author zjw
 * @version V1.0
 */

@Controller
@RequestMapping("/affaris")
public class AffairsController {

	@Autowired
	private AffairsProcess affairsProcess;
	
	/**
	 * 后台管理的列表
	 * @param
	 * @return
	 * */
	@RequestMapping(value = "/affairsList", method = RequestMethod.GET)
    @ResponseBody
    public AffairsPageModel getAffairsList(AffairsPageParam param) throws UnsupportedEncodingException{
		if (StringUtils.isNotBlank(param.getKey())) {
			String key = new String(param.getKey().getBytes("iso-8859-1"), "utf-8");
			param.setKey(key);
		}
    	return affairsProcess.getAffairsList(param);
    }
	
	/**
	 * 添加时事
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/addAffairs", method = RequestMethod.POST)
	@ResponseBody
	public AddAffairsModel addAffair(@RequestBody AddAffairsParam param) {
		return affairsProcess.addAffair(param);
	}
	
}
