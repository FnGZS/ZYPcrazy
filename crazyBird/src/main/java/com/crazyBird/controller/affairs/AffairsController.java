package com.crazyBird.controller.affairs;

import com.crazyBird.controller.affairs.model.AffairsPageModel;
import com.crazyBird.controller.affairs.param.AffairsPageParam;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/affaris" })
public class AffairsController {
	@Autowired
	private AffairsProcess affairsProcess;

	@RequestMapping(value = { "/getAffairsList" }, method = RequestMethod.GET)
	@ResponseBody
	public AffairsPageModel getAffairsList(AffairsPageParam param) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(param.getKey())) {
			String key = new String(param.getKey().getBytes("iso-8859-1"), "utf-8");
			param.setKey(key);
		}
		return affairsProcess.getAffairsList(param);
	}
}
