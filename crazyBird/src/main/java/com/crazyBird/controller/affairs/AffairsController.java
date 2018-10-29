package com.crazyBird.controller.affairs;

import com.crazyBird.controller.affairs.model.AddAffairsModel;
import com.crazyBird.controller.affairs.model.AffairsDetailsModel;
import com.crazyBird.controller.affairs.model.AffairsPageModel;
import com.crazyBird.controller.affairs.param.AddAffairsParam;
import com.crazyBird.controller.affairs.param.AffairsPageParam;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/affaris")
public class AffairsController {
	@Autowired
	private AffairsProcess affairsProcess;

	/** 
	 * 获得时事列表
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/getAffairsList", method = RequestMethod.GET)
	@ResponseBody
	public AffairsPageModel getAffairsList(AffairsPageParam param) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(param.getKey())) {
			String key = new String(param.getKey().getBytes("iso-8859-1"), "utf-8");
			param.setKey(key);
		}
		return affairsProcess.getAffairsList(param);
	}
	
	/**
	 *时事详情
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/affairsDetails/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AffairsDetailsModel getAffairsDetails(@PathVariable Long id) {
		return affairsProcess.getAffairsDetails(id);
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
