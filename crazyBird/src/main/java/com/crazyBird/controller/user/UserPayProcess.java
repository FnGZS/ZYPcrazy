package com.crazyBird.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.user.model.UserPayModel;
import com.crazyBird.controller.user.param.UserAgainPayParam;
import com.crazyBird.controller.user.param.UserPayParam;
import com.crazyBird.controller.user.param.UserRefundParam;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.secondary.SecondaryOrderService;
import com.crazyBird.service.secondary.SecondaryService;
import com.crazyBird.service.user.UserPayService;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;
import com.crazyBird.service.user.dataobject.TestInfo;
import com.crazyBird.service.weixin.WeixinAppService;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.RandomUtil;
import com.crazyBird.utils.SignatureUtils;
import com.crazyBird.utils.XmlToMapUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

@Component
public class UserPayProcess extends BaseProcess {
	// 二手支付成功后的服务器回调url
	private static final String NOTIFY_URL = "https://www.sxscott.com/crazyBird/pay/wxNotify";
	// 二手支付成功后的服务器回调url
	private static final String NOTIFY_URL_GIFT = "https://www.sxscott.com/crazyBird/pay/wxNotify/gitf";

	@Autowired
	private UserPayService payService;
	@Autowired
	private SecondaryOrderService secondaryOrderService;
	@Autowired
	private SecondaryService secondaryService;

	public UserPayModel userPay(UserAgainPayParam param) throws IllegalAccessException {
		UserPayModel model = new UserPayModel();
		String ip = getIp();
		/*
		 * TestInfo test = new TestInfo(); test.setAppid("wxd930ea5d5a258f4f");
		 * test.setMch_id("10000100"); test.setDevice_info("1000"); test.setBd("test");
		 * test.setNonce_str("ibuaiVcKdpRxkhJA"); Map <String,Object> response = new
		 * HashMap <String,Object>(); response.put("appid", "wxd930ea5d5a258f4f");
		 * response.put("mch_id","10000100"); response.put("device_info", "1000");
		 * response.put("body","test"); response.put("nonce_str", "ibuaiVcKdpRxkhJA");
		 * System.out.println(ip); XStream xStream = new XStream(new XppDriver(new
		 * XmlFriendlyNameCoder("_-", "_"))); xStream.alias("xml",TestInfo.class);
		 * xStream.aliasField("body", TestInfo.class, "bd");
		 * test.setSign(SignatureUtils.getSign(response,
		 * "192006250b4c09247ec02edce69f6a2d"));
		 * 
		 * String xml=xStream.toXML(test); System.out.println(xml); Map <String,Object>
		 * map = new HashMap <String,Object>(); map=XmlToMapUtils.getResult(xml); String
		 * xml2=xStream.toXML(test); System.out.println(xml2);
		 * System.out.println(test.getSign()); System.out.println(1);
		 */
		Map<String, String> platUserInfoMap = param.getPlatUserInfoMap();
		ResponseDO<OrderResponseInfo> result = null;

		String orderId = param.getOrderId();
		UserPayParam userPay = new UserPayParam();
		userPay.setFee(param.getFee());
		userPay.setPlatCode(param.getPlatCode());
		userPay.setPlatUserInfoMap(param.getPlatUserInfoMap());
		if (param.getType() == 1) {
			result = WeixinAppService.wxPay(userPay, ip, orderId, NOTIFY_URL);
		}
		if (param.getType() == 2) {
			result = WeixinAppService.wxPay(userPay, ip, orderId, NOTIFY_URL_GIFT);
		}
		if (!result.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(result.getMessage());
			return model;
		}
		model.setNonceStr(result.getDataResult().getNonceStr());

		model.setPkg(result.getDataResult().getPkg());
		model.setSignType(result.getDataResult().getSignType());
		model.setTimeStamp(result.getDataResult().getTimeStamp());
		model.setPaySign(result.getDataResult().getPaySign());

		/*
		 * else { model.setCode(HttpCodeEnum.ERROR.getCode()); model.setMessage("支付失败");
		 * }
		 */

		return model;
	}

	public SimpleFlagModel secondaryRefund(UserRefundParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		ResponseDO<UserRefundDO> response = WeixinAppService.refund(param);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		int flag1 = payService.insertRefundOrder(response.getDataResult());
		if(param.getType()==1) {
		int flag2 = secondaryOrderService.updateSecondaryOrderRefund(response.getDataResult().getOut_trade_no());
		if (flag1 <= 0 || flag2 <= 0) {
			model.setMessage("退款成功但更新或插入本地数据时出错");
			}
		}
		Long id = secondaryService.getSecondaryGoodsId(response.getDataResult().getOut_trade_no());
		secondaryService.updateSecondaryGoodsOnline(id);
		return model;
	}

	public boolean wxNotify(Map<String, Object> resultMap) {
		int secondaryFlag = payService.checkSecondaryOrder((String) resultMap.get("out_trade_no"));
		if (secondaryFlag == 0) {
			payService.updateSecondaryOrder((String) resultMap.get("out_trade_no"));
		}
		int count = payService.checkWxPayOrder((String) resultMap.get("transaction_id"));
		if (count > 0) {
			return true;
		}
		UserWxPayOrderDO orderDO = new UserWxPayOrderDO();
		orderDO.setAppid((String) resultMap.get("appid"));
		orderDO.setMch_id((String) resultMap.get("mch_id"));
		orderDO.setNonce_str((String) resultMap.get("nonce_str"));
		orderDO.setSign((String) resultMap.get("sign"));
		orderDO.setResult_code((String) resultMap.get("result_code"));
		orderDO.setOpenid((String) resultMap.get("openid"));
		orderDO.setTrade_type((String) resultMap.get("trade_type"));
		orderDO.setBank_type((String) resultMap.get("bank_type"));
		orderDO.setTotal_fee(Integer.valueOf((String) resultMap.get("total_fee")));
		orderDO.setCash_fee(Integer.valueOf((String) resultMap.get("cash_fee")));
		orderDO.setTransaction_id((String) resultMap.get("transaction_id"));
		orderDO.setOut_trade_no((String) resultMap.get("out_trade_no"));
		orderDO.setGmt_created(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		orderDO.setGmt_modified(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		int flag = payService.insertOrder(orderDO);
		if (flag <= 0) {
			return false;
		}
		return true;

	}

	public boolean wxNotifyGift(Map<String, Object> resultMap) {
		int liveFlag = payService.checkLiveOrder((String) resultMap.get("out_trade_no"));
		if (liveFlag == 0) {
			payService.updateLiveOrder((String) resultMap.get("out_trade_no"));
		}
		int count = payService.checkWxPayOrder((String) resultMap.get("transaction_id"));
		if (count > 0) {
			return true;
		}
		UserWxPayOrderDO orderDO = new UserWxPayOrderDO();
		orderDO.setAppid((String) resultMap.get("appid"));
		orderDO.setMch_id((String) resultMap.get("mch_id"));
		orderDO.setNonce_str((String) resultMap.get("nonce_str"));
		orderDO.setSign((String) resultMap.get("sign"));
		orderDO.setResult_code((String) resultMap.get("result_code"));
		orderDO.setOpenid((String) resultMap.get("openid"));
		orderDO.setTrade_type((String) resultMap.get("trade_type"));
		orderDO.setBank_type((String) resultMap.get("bank_type"));
		orderDO.setTotal_fee(Integer.valueOf((String) resultMap.get("total_fee")));
		orderDO.setCash_fee(Integer.valueOf((String) resultMap.get("cash_fee")));
		orderDO.setTransaction_id((String) resultMap.get("transaction_id"));
		orderDO.setOut_trade_no((String) resultMap.get("out_trade_no"));
		orderDO.setGmt_created(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		orderDO.setGmt_modified(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		int flag = payService.insertOrder(orderDO);
		if (flag <= 0) {
			return false;
		}
		return true;

	}
}
