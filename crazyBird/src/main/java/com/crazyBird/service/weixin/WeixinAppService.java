package com.crazyBird.service.weixin;

import com.crazyBird.controller.user.param.UserPayParam;
import com.crazyBird.controller.user.param.UserRefundParam;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.user.dataobject.OrderInfo;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;
import com.crazyBird.service.user.dataobject.RefundInfo;
import com.crazyBird.service.user.dataobject.UserInfo;
import com.crazyBird.utils.AesUtils;
import com.crazyBird.utils.ArithUtils;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.JsonUtils;
import com.crazyBird.utils.PayUtils;
import com.crazyBird.utils.RandomUtil;
import com.crazyBird.utils.SignatureUtils;
import com.crazyBird.utils.XmlToMapUtils;

import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

@Component("weixinAppService")
public class WeixinAppService {
	// 获取用户信息的API_URL
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
	// 微信统一下单地址
	private static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信退款地址
	private static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 证书地址
	public static final String KEY_PATH = "/wwwroot/web/wechat/apiclient_cert.p12";
	// 支付类型
	private static final String TRADE_TYPE = "JSAPI";
	// 商户支付密钥
	private static String KEY = "wx201411101639507cbf6ffd8b0779950874";
	// 商户号
	private static String MCH_ID = "10000100";
	private static String APP_ID = "wx070db500b5e5740f";
	private static String SECRET = "d22b361c01e467afd5a55418a04ecb78";// 企业版
	// private static String APP_ID = "wx1b75aff38e9dad18";
	// private static String SECRET = "fef991718ea769444e3a09beee000668";//个人版
	// private static String APP_ID = "wxe39ca82b04cebded";
	// private static String SECRET = "95673278ae2b2604e12206f823b4a31e";

	private static CloseableHttpClient httpClient = null;

	private static CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = HttpClientBuilder.create().build();
		}
		return httpClient;
	}

	public static ResponseDO<UserInfo> getUserInfo(String platCode, Map<String, String> platUserInfoMap) {
		ResponseDO<UserInfo> result = new ResponseDO<>();
		// String APP_ID = "wxe39ca82b04cebded";
		// String SECRET = "95673278ae2b2604e12206f823b4a31e";
		// String USER_INFO_URL =
		// "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
		String url = String.format(USER_INFO_URL, APP_ID, SECRET, platCode);
		URI uri = URI.create(url);
		HttpGet get = new HttpGet(uri);
		HttpResponse response;
		try {
			response = getHttpClient().execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();

				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
				StringBuilder sb = new StringBuilder();

				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
					sb.append(temp);
				}
				Map<String, Object> resultMap = JsonUtils.getMap4Json(sb.toString().trim());
				String sessionkey = (String) resultMap.get("session_key");
				if (StringUtils.isBlank(sessionkey)) {
					result.setCode(ResponseCode.ERROR);
					result.setMessage("微信小程序登录异常，code换取session_key失败");
					return result;
				}
				// STEP2 获取解密用户关键信息
				String encryptedData = platUserInfoMap.get("encryptedData");
				String iv = platUserInfoMap.get("iv");
				String decryptedData = AesUtils.decrypt(encryptedData, sessionkey, iv);
				Map<String, Object> decryptedDataMap = JsonUtils.getMap4Json(decryptedData.trim());
				UserInfo userInfo = new UserInfo();
				userInfo.setOpenId((String) decryptedDataMap.get("openId"));
				userInfo.setHeadimgurl((String) decryptedDataMap.get("avatarUrl"));
				userInfo.setNickName((String) decryptedDataMap.get("nickName"));
				userInfo.setCountry((String) decryptedDataMap.get("country"));
				userInfo.setProvince((String) decryptedDataMap.get("province"));
				userInfo.setCity((String) decryptedDataMap.get("city"));
				userInfo.setSex((Integer) decryptedDataMap.get("gender"));
				userInfo.setSessionKey(sessionkey);
				if (StringUtils.isBlank(userInfo.getNickName()) || StringUtils.isBlank(userInfo.getOpenId())) {
					result.setCode(ResponseCode.ERROR);
					result.setMessage("微信小程序登录异常，返回信息不全");
					return result;
				}
				result.setDataResult(userInfo);
			}
		} catch (ClientProtocolException e) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage("微信小程序登录异常");
		} catch (IOException e) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage("微信小程序登录异常");
		} catch (Exception e) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	/**
	 * 微信支付
	 * 
	 * @param platCode
	 * @param platUserInfoMap
	 * @param ip
	 * @param orederId
	 * @return
	 */
	public static ResponseDO<OrderResponseInfo> wxPay(UserPayParam param, String ip, String orederId,
			String notifyUrl) {
		ResponseDO<OrderResponseInfo> result = new ResponseDO<>();
		// 将钱 转换成 分
		double times = 100.00;
		double cost = param.getFee();
		double sum = ArithUtils.mul(cost, times);
		int fee = (int) sum;
		System.out.println(fee);
		OrderInfo orderInfo = new OrderInfo();
		Map<String, String> platUserInfoMap = param.getPlatUserInfoMap();
		String platCode = param.getPlatCode();
		ResponseDO<UserInfo> userInfo = getUserInfo(platCode, platUserInfoMap);
		if (!userInfo.isSuccess()) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage(userInfo.getMessage());
			return result;
		}
		URI uri = URI.create(PAY_URL);
		HttpPost post = new HttpPost(uri);
		HttpResponse response;
		try {
			// 构建小程序下单接口需要的数据
			String openid = userInfo.getDataResult().getOpenId();
			orderInfo.setAppid(APP_ID);
			orderInfo.setMch_id(MCH_ID);
			orderInfo.setNonce_str(RandomUtil.getRandomCharString(32));
			orderInfo.setBody("测试");
			// 测试订单号
			orderInfo.setOut_trade_no(orederId);
			orderInfo.setTotal_fee(fee);
			orderInfo.setSpbill_create_ip(ip);// ip
			orderInfo.setNotify_url(notifyUrl);
			orderInfo.setTrade_type(TRADE_TYPE);
			orderInfo.setOpenid(openid);
			orderInfo.setSign_type("MD5");
			String sign = SignatureUtils.getSign(orderInfo, KEY);
			System.out.println(sign);
			// 签名
			orderInfo.setSign(sign);
			// 将数据转换成xml格式
			XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
			xStream.alias("xml", OrderInfo.class);
			String xml = xStream.toXML(orderInfo);
			post.setEntity(new StringEntity(xml, "utf-8"));
			// 调用支付统一下单api
			response = getHttpClient().execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println("成功");
				HttpEntity entity = response.getEntity();

				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
				StringBuilder sb = new StringBuilder();

				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
					sb.append(temp);
				}
				Map<String, Object> resultMap = XmlToMapUtils.getResult(sb.toString().trim());
				String returnCode = (String) resultMap.get("return_code");
				if (returnCode == "SUCCESS" || returnCode.equals("SUCCESS")) {
					OrderResponseInfo info = new OrderResponseInfo();
					String pkg = (String) resultMap.get("prepay_id");
					info.setNonceStr((String) resultMap.get("nonce_str"));
					info.setPkg(pkg);
					info.setSignType("MD5");
					info.setAppId(APP_ID);
					String dateTime = DateUtil.formatDate(new Date(), DateUtil.dtLong);
					info.setTimeStamp(dateTime);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("nonceStr", (String) resultMap.get("nonce_str"));
					map.put("package", (String) resultMap.get("prepay_id"));
					map.put("signType", "MD5");
					map.put("appId", APP_ID);
					map.put("timeStamp", dateTime);
					// 再次签名
					info.setPaySign((String) resultMap.get(SignatureUtils.getSign(map, KEY)));
					System.out.println(info.getPaySign());
					result.setDataResult(info);

				}
			}
		} catch (ClientProtocolException e) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage("小程序支付异常");
			e.printStackTrace();
		} catch (IOException e) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage("小程序支付异常");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage("小程序支付异常");
		}

		return result;
	}

	/**
	 * 微信退款
	 * 
	 * @param orederId
	 * @return
	 */
	public static ResponseDO<UserRefundDO> refund(UserRefundParam param) {
		ResponseDO<UserRefundDO> result = new ResponseDO<>();
		double times = 100.00;
		double refundFee = param.getRefundFee();
		double totalFee = param.getTotalFee();
		double refund_sum = ArithUtils.mul(refundFee, times);
		double total_sum = ArithUtils.mul(totalFee, times);
		int refund_fee = (int) refund_sum;
		int total_fee = (int) total_sum;
		RefundInfo refundInfo = new RefundInfo();
		refundInfo.setAppid(APP_ID);
		refundInfo.setMch_id(MCH_ID);
		refundInfo.setNonce_str(RandomUtil.getRandomCharString(32));
		refundInfo.setOut_refund_no(param.getOrderId());
		refundInfo.setOut_trade_no(param.getOrderId());
		refundInfo.setRefund_fee(refund_fee);
		refundInfo.setTotal_fee(total_fee);
		String sign = "";
		try {
			sign = SignatureUtils.getSign(refundInfo, KEY);
			refundInfo.setSign(sign);
			XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
			xStream.alias("xml", RefundInfo.class);
			String xml = xStream.toXML(refundInfo);
			String resultStr = PayUtils.post(REFUND_URL, xml, KEY_PATH);
			Map map = XmlToMapUtils.getResult(resultStr);
			String returnCode = map.get("return_code").toString();
			if (returnCode.equals("SUCCESS")) {
				UserRefundDO refundDO = new UserRefundDO();
				refundDO.setAppid((String) map.get("appid"));
				refundDO.setMch_id((String) map.get("mch_id"));
				refundDO.setNonce_str((String) map.get("nonce_str"));
				refundDO.setSign((String) map.get("sign"));
				refundDO.setTransaction_id((String) map.get("transaction_id"));
				refundDO.setOut_refund_no((String) map.get("out_trade_no"));
				refundDO.setOut_trade_no((String) map.get("out_refund_no"));
				refundDO.setRefund_id((String) map.get("refund_id"));
				refundDO.setRefund_fee((Integer) map.get("refund_fee"));
				refundDO.setTotal_fee((Integer) map.get("total_fee"));
				refundDO.setCash_fee((Integer) map.get("cash_fee"));
				result.setDataResult(refundDO);
				
			}
			else {
				result.setCode(ResponseCode.ERROR);
				result.setMessage("退款失败");
			}

		} catch (IllegalAccessException e) {
			result.setCode(ResponseCode.ERROR);
			result.setMessage("退款时签名出错");
		}

		// refundInfo.setOut_refund_no(out_refund_no);
		return result;
	}

	public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		return new String(cipher.doFinal(encData), "UTF-8");
	}
}
