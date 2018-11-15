package com.admin.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admin.controller.base.BaseProcess;
import com.admin.controller.user.model.AdminModel;
import com.admin.controller.user.model.BindingModel;
import com.admin.controller.user.model.LoginModel;
import com.admin.controller.user.param.AdminParam;
import com.admin.controller.user.param.BindingParam;
import com.admin.controller.user.param.LoginParam;
import com.admin.dao.user.UserDao;
import com.admin.dao.user.dataobject.AdminDO;
import com.admin.dao.user.dataobject.BindingDO;
import com.admin.dao.user.dataobject.LoginDO;
import com.admin.dao.user.dataobject.UserLoginDO;
import com.admin.model.enums.HttpCodeEnum;
import com.admin.service.base.ResponseCode;
import com.admin.service.base.ResponseDO;
import com.admin.service.user.UserLoginService;
import com.admin.service.user.dataobject.UserInfo;
import com.admin.service.weixin.WeixinAppService;

import org.apache.commons.lang3.StringUtils;

@Component
public class UserLoginProcess extends BaseProcess{

	@Autowired
	private UserLoginService userLoginService;


	public LoginModel doLogin(LoginParam param) {
		LoginModel model = new LoginModel();
		ResponseDO<UserInfo> userInfoResult;
		Map<String, String> platUserInfoMap = param.getPlatUserInfoMap();
		if(platUserInfoMap != null && !platUserInfoMap.isEmpty()) { //小程序登�?
			if(StringUtils.isBlank(platUserInfoMap.get("encryptedData")) || StringUtils.isBlank(platUserInfoMap.get("iv"))) {
				model.setCode(ResponseCode.ERROR);
				model.setMessage("微信小程序登录异常，缺少必要参数");
				return model;
			}
			userInfoResult = WeixinAppService.getUserInfo(param.getPlatCode(), platUserInfoMap);
		}else {
			userInfoResult = null;
		}
		if(!userInfoResult.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(userInfoResult.getMessage());
			return model;
		}
		UserInfo userInfo = userInfoResult.getDataResult();
		String openId = userInfo.getOpenId();
		String nickName = userInfo.getNickName();
		String headimgurl = userInfo.getHeadimgurl();
		Integer sex = userInfo.getSex();
		LoginDO wxUser = new LoginDO();
		wxUser.setHeadimgurl(headimgurl);
		wxUser.setNickName(nickName);
		wxUser.setOpenId(openId);
		ResponseDO<UserLoginDO> responseDO = userLoginService.userLogin(wxUser);
		UserLoginDO login = responseDO.getDataResult();
		model.setSex(sex);
		model.setAvatar(headimgurl);
		model.setUserName(nickName);
		model.setOpenAccount(login.getLoginAccount());
		model.setIsbound(login.getIsBound());
		return model;
	}

	public BindingModel binding(BindingParam param) {
		BindingModel model = new BindingModel();
		BindingDO binding = new BindingDO();
		if(param!=null) {
			binding.setUnionId(param.getUnionId());;
			binding.setSchoolNum(param.getSchoolNum());;
			binding.setPassword(param.getPassword());
			ResponseDO<String> responseDO = userLoginService.userBinding(binding);
			model.setResult(responseDO.getDataResult());
			model.setCode(responseDO.getCode());
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		return model;
	}

	public AdminModel adminLogin(AdminParam param) {
		AdminModel model = new AdminModel();
		AdminDO admin = new AdminDO();
		admin.setAdminName(param.getAdminName());
		admin.setPassword(param.getPassword());
		ResponseDO<AdminDO> responseDO = userLoginService.adminLogin(admin);
		if(!responseDO.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("登录失败");
			return model;
		}
		model.setAdminUser(responseDO.getDataResult().getAdminUser());
		model.setAdminName(responseDO.getDataResult().getAdminName());
		return model;
	}




}
