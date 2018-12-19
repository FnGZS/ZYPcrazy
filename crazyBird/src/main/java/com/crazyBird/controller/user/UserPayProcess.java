package com.crazyBird.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.user.model.UserPayModel;
import com.crazyBird.controller.user.param.UserPayParam;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.user.UserPayService;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;
import com.crazyBird.service.weixin.WeixinAppService;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.RandomUtil;

@Component
public class UserPayProcess extends BaseProcess{
	@Autowired 
	private UserPayService	payService;
	public UserPayModel userPay(UserPayParam param) throws IllegalAccessException {
		UserPayModel model = new UserPayModel();
		String ip = getIp();
		
		/*
		TestInfo test = new TestInfo();
		test.setAppid("wxd930ea5d5a258f4f");
		test.setMch_id("10000100");
		test.setDevice_info("1000");
		test.setBd("test");
		test.setNonce_str("ibuaiVcKdpRxkhJA");
		 Map <String,Object> response = new HashMap <String,Object>();
		 response.put("appid", "wxd930ea5d5a258f4f");
		 response.put("mch_id","10000100");
		 response.put("device_info", "1000");
		 response.put("body","test");
		 response.put("nonce_str", "ibuaiVcKdpRxkhJA");
		System.out.println(ip);
		XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
		xStream.alias("xml",TestInfo.class);
		xStream.aliasField("body", TestInfo.class, "bd");  
		test.setSign(SignatureUtils.getSign(response, "192006250b4c09247ec02edce69f6a2d"));

		String xml=xStream.toXML(test);
		System.out.println(xml);
		String xml2=xStream.toXML(test);
		System.out.println(xml2);
		System.out.println(test.getSign());*/
		System.out.println(1);
		Map<String, String> platUserInfoMap = param.getPlatUserInfoMap();
		ResponseDO<OrderResponseInfo> result;
		if ((platUserInfoMap != null) && (!platUserInfoMap.isEmpty())) {
			if(StringUtils.isBlank(platUserInfoMap.get("encryptedData")) || StringUtils.isBlank(platUserInfoMap.get("iv"))) {
				model.setCode(ResponseCode.ERROR);
				model.setMessage("微信交易异常，缺少必要参数");
				return model;
			}
			String orederId=RandomUtil.getRandomNumString(21)+DateUtil.formatDate(new Date(), DateUtil.dtLong);
			result=WeixinAppService.wxPay(param, ip, orederId);
			if(!result.isSuccess()) {
				model.setCode(HttpCodeEnum.ERROR.getCode());
				model.setMessage(result.getMessage());
				return model;
			}
			model.setNonceStr(result.getDataResult().getNonceStr());

			model.setPkg(result.getDataResult().getPkg());
			model.setSignType(result.getDataResult().getSignType());
			model.setTimeStamp(result.getDataResult().getTimeStamp());
			model.setPaySign(result.getDataResult().getPaySign());
		} else {
			result = null;
		}

		return model;
		
	}
}
