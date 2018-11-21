package com.crazyBird.controller.affairs;

import com.crazyBird.controller.affairs.model.AffairsDetailsModel;
import com.crazyBird.controller.affairs.model.AffairsPageModel;
import com.crazyBird.controller.affairs.model.AffairsTypeModel;
import com.crazyBird.controller.affairs.model.BroadModel;
import com.crazyBird.controller.affairs.model.RecommendModel;
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
	 * 获取时事类别信息
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/type", method = RequestMethod.GET)
	@ResponseBody
	public AffairsTypeModel getAffairsType() {
		return affairsProcess.getAffairsType();
	}
	
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
	 * 轮播图
	 * @param param
	 * @return
	 * */
	@RequestMapping(value ="/broad", method = RequestMethod.GET)
	@ResponseBody
	public BroadModel getBroad() {
		return affairsProcess.getBroad();
	}
	
	/**
	 * 时事的推荐
	 * @param param
	 * @return
	 * **/
	@RequestMapping(value ="/recommend", method = RequestMethod.GET)
	@ResponseBody
	public RecommendModel getRecommend() {
		return affairsProcess.getRecommend();
	}
}
