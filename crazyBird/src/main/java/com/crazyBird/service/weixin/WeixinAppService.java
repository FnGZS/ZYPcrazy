 package com.crazyBird.service.weixin;
 
 import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
 import com.crazyBird.service.user.dataobject.UserInfo;
 import com.crazyBird.utils.AesUtils;
 import com.crazyBird.utils.JsonUtils;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.URI;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.apache.http.HttpEntity;
 import org.apache.http.HttpResponse;
 import org.apache.http.StatusLine;
 import org.apache.http.client.ClientProtocolException;
 import org.apache.http.client.methods.HttpGet;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClientBuilder;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 @Component("weixinAppService")
 public class WeixinAppService
 {
   private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
   private static String APP_ID = "wxe39ca82b04cebded";
   private static String SECRET = "95673278ae2b2604e12206f823b4a31e";
   
   private static CloseableHttpClient httpClient = null;
   
   private static CloseableHttpClient getHttpClient() {
     if (httpClient == null) {
       httpClient = HttpClientBuilder.create().build();
     }
     return httpClient;
   }
   
   public static ResponseDO<UserInfo> getUserInfo(String platCode, Map<String, String> platUserInfoMap) {
	 ResponseDO<UserInfo> result = new ResponseDO<>();
	 String url = String.format(USER_INFO_URL, APP_ID,SECRET,platCode);
     URI uri = URI.create(url);
     HttpGet get = new HttpGet(uri);
     HttpResponse response;
     try
     {
    		response = getHttpClient().execute(get);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8")); 
				StringBuilder sb = new StringBuilder();
				
				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
					sb.append(temp);
				}
				Map<String, Object> resultMap = JsonUtils.getMap4Json(sb.toString().trim());
				String sessionkey = (String) resultMap.get("session_key");
				if(StringUtils.isBlank(sessionkey)) {
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
				userInfo.setOpenId((String)decryptedDataMap.get("openId"));
				userInfo.setHeadimgurl((String) decryptedDataMap.get("avatarUrl"));
				userInfo.setNickName((String) decryptedDataMap.get("nickName"));
				userInfo.setCountry((String) decryptedDataMap.get("country"));
				userInfo.setProvince((String) decryptedDataMap.get("province"));
				userInfo.setCity((String) decryptedDataMap.get("city"));
				userInfo.setSex((Integer) decryptedDataMap.get("gender"));
				if(StringUtils.isBlank(userInfo.getNickName()) || StringUtils.isBlank(userInfo.getOpenId())) {
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
 }


