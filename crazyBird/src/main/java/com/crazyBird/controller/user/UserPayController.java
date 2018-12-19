package com.crazyBird.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.user.model.UserPayModel;
import com.crazyBird.controller.user.param.UserPayParam;

@Controller
@RequestMapping("/pay")
public class UserPayController {
	@Autowired
	private UserPayProcess payProcess;
	/**
	 * 充值
	 * @param param
	 * @return
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value="/recharge", method = RequestMethod.POST)
	@ResponseBody
	public UserPayModel userPay(@RequestBody UserPayParam param) throws IllegalAccessException {
		return payProcess.userPay(param);
	}
	/**
	 * 支付回调
	 */
	@RequestMapping(value="/callback", method = RequestMethod.GET)
	@ResponseBody
	public SimpleFlagModel callback() {
		long fee=Long.parseLong("10");
		System.out.println(fee);
		System.out.println();
		return null;
	}
	
}
