package com.crazyBird.controller.delivery;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 跑腿
 * @author zjw
 *
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.affairs.param.AffairsPageParam;
import com.crazyBird.controller.delivery.model.DeliveryModel;
import com.crazyBird.controller.delivery.param.DeliveryPageParam;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	private DeliveryProcess deliveryProcess;
	
	@RequestMapping(value ="/getDeliveryList", method = RequestMethod.GET)
	@ResponseBody
	public DeliveryModel getDeliveryList(DeliveryPageParam param) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(param.getKey())) {
			String key = new String(param.getKey().getBytes("iso-8859-1"), "utf-8");
			param.setKey(key);
		}
		return deliveryProcess.getDeliveryList(param);
	}
	
	
}
