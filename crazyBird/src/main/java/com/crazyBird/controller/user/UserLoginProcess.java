package com.crazyBird.controller.user;

import com.crazyBird.service.base.ResponseCode;
import com.aliyuncs.exceptions.ClientException;
import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.user.model.BackgroundModel;
import com.crazyBird.controller.user.model.BindingModel;
import com.crazyBird.controller.user.model.GetPhoneModel;
import com.crazyBird.controller.user.model.LoginModel;
import com.crazyBird.controller.user.model.MessageModel;
import com.crazyBird.controller.user.param.BindChangeParam;
import com.crazyBird.controller.user.param.BindParam;
import com.crazyBird.controller.user.param.BindingParam;
import com.crazyBird.controller.user.param.JYZPageParam;
import com.crazyBird.controller.user.param.JYZParam;
import com.crazyBird.controller.user.param.LoginParam;
import com.crazyBird.controller.user.param.MessageParam;
import com.crazyBird.controller.user.param.MessagePutParam;
import com.crazyBird.controller.user.param.UserFormParam;
import com.crazyBird.dao.affairs.dataobject.CantBindingDO;
import com.crazyBird.dao.user.dataobject.BackgroundDO;
import com.crazyBird.dao.user.dataobject.BindingChangeDO;
import com.crazyBird.dao.user.dataobject.BindingDO;
import com.crazyBird.dao.user.dataobject.HavePhoneUserDO;
import com.crazyBird.dao.user.dataobject.LoginDO;
import com.crazyBird.dao.user.dataobject.UserFormDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.dao.user.dataobject.VerificationDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.model.reqinfo.ReqHead;
import com.crazyBird.model.reqinfo.ReqParam;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.user.UserLoginService;
import com.crazyBird.service.user.dataobject.MessageInfo;
import com.crazyBird.service.user.dataobject.UserInfo;
import com.crazyBird.service.weixin.WeixinAppService;
import com.crazyBird.utils.JsonUtils;
import com.crazyBird.utils.Md5Utils;
import com.crazyBird.utils.SMSUtils;
import com.crazyBird.utils.TokenUtils;
import com.crazyBird.utils.VerificationUtils;
import com.crazyBird.utils.XmlToMapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLoginProcess extends BaseProcess {
	@Autowired
	private UserLoginService userLoginService;

	public SimpleFlagModel messageput(MessagePutParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		UserFormDO formDO = userLoginService.getFormId(param.getUserId());
		if(formDO==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("发送失败");
			return model;
		}
		MessageInfo info = new MessageInfo();
		info.setTouser(formDO.getOpenId());
		info.setEmphasis_keyword(param.getEmphasis_keyword());
		info.setForm_id(formDO.getFormId());
		info.setPage(param.getPage());
		Map<String, Object> map = JsonUtils.getMap4Json(param.getData());
		info.setTemplate_id(param.getTemplate_id());
		info.setData(map);
		ResponseDO response = WeixinAppService.messagePut(info);
		if(response.isSuccess()) {
			
			model.setMessage("发送成功");
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		System.out.println(JsonUtils.toJSON(formDO));
		userLoginService.deleteFormId(formDO.getId());
		return model;
	}

	public SimpleFlagModel insertFormId(UserFormParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		UserFormDO formDO = new UserFormDO();
		formDO.setFormId(param.getFormId());
		formDO.setOpenId(param.getOpenId());
		formDO.setUserId(param.getUserId());
		userLoginService.insertFormId(formDO);
		return model;

	}

	public LoginModel doLogin(LoginParam param) {
		LoginModel model = new LoginModel();
		String openId = null;

		Map<String, String> platUserInfoMap = param.getPlatUserInfoMap();
		ResponseDO<UserInfo> userInfoResult;
		if ((platUserInfoMap != null) && (!platUserInfoMap.isEmpty())) {
			if (StringUtils.isBlank(platUserInfoMap.get("encryptedData"))
					|| StringUtils.isBlank(platUserInfoMap.get("iv"))) {
				model.setCode(ResponseCode.ERROR);
				model.setMessage("微信小程序登录异常，缺少必要参数");
				return model;
			}
			userInfoResult = WeixinAppService.getUserInfo(param.getPlatCode(), platUserInfoMap);
		} else {
			userInfoResult = null;
		}
		if (!userInfoResult.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(userInfoResult.getMessage());
			return model;
		}
		UserInfo userInfo = userInfoResult.getDataResult();
		try {
			openId = Md5Utils.getMD5(userInfo.getOpenId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String nickName = userInfo.getNickName();
		String headimgurl = userInfo.getHeadimgurl();
		Integer sex = userInfo.getSex();
		String unionId = userInfo.getUnionId();
		LoginDO wxUser = new LoginDO();
		wxUser.setHeadimgurl(headimgurl);
		wxUser.setNickName(nickName);
		wxUser.setOpenId(openId);
		wxUser.setSex(sex);
		wxUser.setUnionId(unionId);
		ResponseDO<UserLoginDO> responseDO = userLoginService.userLogin(wxUser);
		UserLoginDO login = responseDO.getDataResult();
		model.setAuthorization(login.getAccessToken());
		model.setSex(sex);
		model.setAvatar(headimgurl);
		model.setUserName(nickName);
		model.setSessionKey(userInfo.getSessionKey());
		model.setOpenAccount(login.getLoginAccount());
		model.setPhone(login.getTelephone());
		model.setIsbound(login.getIsBound());
		model.setOpenId(userInfo.getOpenId());
		if (model.getIsbound() == 1) {
			try {
				model.setUserId(TokenUtils.getIdFromAesStr(login.getAccessToken()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			model.setUserId(null);
		}
		return model;
	}

	public BindingModel binding(BindingParam param) {
		BindingModel model = new BindingModel();
		BindingDO binding = new BindingDO();
		if (param != null) {
			binding.setAsToken(getReqParam().getReqHead().getAccessToken());
			binding.setSchoolNum(param.getSchoolNum());
			binding.setPassword(param.getPassword());
			ResponseDO<BindingDO> responseDO = userLoginService.userBinding(binding);
			if (responseDO.isSuccess()) {
				model.setResult(Integer.valueOf(1));
				model.setAsToken(responseDO.getDataResult().getAsToken());
			}
			model.setMessage(responseDO.getMessage());
			return model;
		}
		model.setResult(Integer.valueOf(2));
		model.setCode(HttpCodeEnum.ERROR.getCode());
		model.setMessage("缺少必要参数");
		return model;
	}

	public MessageModel smsget(MessageParam param) {
		MessageModel model = new MessageModel();
		String phone = param.getPhone();
		String code = VerificationUtils.random();
		String result = "success";
		try {
			result = SMSUtils.sendSms(phone, code);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == "fail") {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("验证码发送失败");
			return model;
		}
		VerificationDO verification = new VerificationDO();
		verification.setPhone(phone);
		verification.setCode(code);
		userLoginService.saveVerification(verification);
		return model;
	}

	public BindingModel bind(BindParam param) {
		BindingModel model = new BindingModel();
		if (param.getPhone() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("手机号不能为空");
			return model;
		}
		if (param.getCode() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("验证码不能为空");
			return model;
		}
		VerificationDO verification = new VerificationDO();
		verification.setPhone(param.getPhone());
		verification.setCode(param.getCode());
		ResponseDO<String> responseVer = userLoginService.verifica(verification);
		if (!responseVer.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(responseVer.getMessage());
			return model;
		}
		CantBindingDO binding = new CantBindingDO();
		if (param != null) {
			binding.setAsToken(getReqParam().getReqHead().getAccessToken());
			binding.setSchoolNum(param.getSchoolNum());
			binding.setPassword(param.getPassword());
			binding.setPhone(param.getPhone());
			binding.setUserName(param.getUserName());
			ResponseDO<CantBindingDO> responseDO = userLoginService.cantBinding(binding);
			if (responseDO.isSuccess()) {
				model.setResult(Integer.valueOf(1));
				model.setAsToken(responseDO.getDataResult().getAsToken());
			}
			model.setMessage(responseDO.getMessage());
			return model;
		}
		model.setResult(Integer.valueOf(2));
		model.setCode(HttpCodeEnum.ERROR.getCode());
		model.setMessage("缺少必要参数");
		return model;
	}

	public BackgroundModel getBackgroud() {
		BackgroundModel model = new BackgroundModel();
		BackgroundDO background = userLoginService.background();
		model.setBackground(background.getBackground());
		return model;
	}

	public GetPhoneModel getPhone(String str) {
		GetPhoneModel model = new GetPhoneModel();
		Map<String, String> map = new HashMap<String, String>();
		String data = str.replace("\"", "");
		String cms = data.replace("{", "").replace("}", "");
		String[] countryMapStr = cms.split(",");
		for (String s : countryMapStr) {
			String[] ms = s.split(":");
			map.put(ms[0], ms[1]);
		}
		String phone = map.get("purePhoneNumber");
		if (phone == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("未成功取得手机号");
			return model;
		}
		String accessToken = getReqParam().getReqHead().getAccessToken();
		ResponseDO<HavePhoneUserDO> response = userLoginService.getHavePhoneUser(phone, accessToken);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setAsToken(response.getDataResult().getAsToken());
		model.setResult(response.getDataResult().getResult());
		model.setMessage(response.getMessage());
		model.setPhone(phone);
		return model;
	}

	public BindingModel changeBind(BindChangeParam param) {
		BindingModel model = new BindingModel();
		if (param.getPhone() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("手机号不能为空");
			return model;
		}
		if (param.getCode() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("验证码不能为空");
			return model;
		}
		VerificationDO verification = new VerificationDO();
		verification.setPhone(param.getPhone());
		verification.setCode(param.getCode());
		ResponseDO<String> responseVer = userLoginService.verifica(verification);
		if (!responseVer.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(responseVer.getMessage());
			return model;
		}
		BindingChangeDO bindingChange = new BindingChangeDO();
		bindingChange.setAsToken(getReqParam().getReqHead().getAccessToken());
		bindingChange.setPhone(param.getPhone());
		ResponseDO<BindingDO> responseDO = userLoginService.changeBind(bindingChange);
		if (responseDO.isSuccess()) {
			model.setResult(Integer.valueOf(1));
			model.setAsToken(responseDO.getDataResult().getAsToken());
		}
		model.setMessage(responseDO.getMessage());
		return model;
	}

	public void addjyz(JYZParam param) {
		userLoginService.addjyz(param);
	}

	public List<JYZParam> findAll(JYZPageParam params) {
		// TODO Auto-generated method stub
		return null;
	}
}
