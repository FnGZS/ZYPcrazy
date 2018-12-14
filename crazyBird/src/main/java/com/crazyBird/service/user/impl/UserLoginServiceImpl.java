package com.crazyBird.service.user.impl;

import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.dao.affairs.dataobject.CantBindingDO;
import com.crazyBird.dao.user.UserBackgroudDao;
import com.crazyBird.dao.user.UserDao;
import com.crazyBird.dao.user.UserLoginDao;
import com.crazyBird.dao.user.VerificationDao;
import com.crazyBird.dao.user.dataobject.BackgroundDO;
import com.crazyBird.dao.user.dataobject.BindingDO;
import com.crazyBird.dao.user.dataobject.BingDO;
import com.crazyBird.dao.user.dataobject.LoginDO;
import com.crazyBird.dao.user.dataobject.UserDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.dao.user.dataobject.VerificationDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.user.UserLoginService;
import com.crazyBird.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserLoginDao userLoginDao;
	@Autowired
	private VerificationDao verificationDao;
	@Autowired
	private UserBackgroudDao userBackgroudDao;
	
	@Override
	public ResponseDO<UserLoginDO> getUserLogin(Long shoolNum) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<>();
		UserDO user = userDao.seletUserBySnum(shoolNum);
		if (user != null) {
			UserLoginDO userLoginDO = userLoginDao.queryUserLogin(user.getOpenId());
			if (userLoginDO != null) {
				responseDO.setDataResult(userLoginDO);
			} else {
				responseDO.setCode(ResponseCode.ERROR);
				responseDO.setMessage("学号不存在");
			}
		} else {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("学号不存在");
		}
		return responseDO;
	}

	@Override
	public ResponseDO<UserLoginDO> userLogin(LoginDO wxUser) {
		ResponseDO<UserLoginDO> responseDO = new ResponseDO<>();
		UserDO user = userDao.seletUser(wxUser.getOpenId());
		if (user != null) {
			UserLoginDO userLoginDO = userLoginDao.seletUserByOpenId(wxUser.getOpenId());
			if (userLoginDO == null) {
				userLoginDO = new UserLoginDO();
				userLoginDO.setOpenId(wxUser.getOpenId());
				userLoginDO.setLoginAccount(wxUser.getNickName());
				userLoginDO.setHeadimgurl(wxUser.getHeadimgurl());
				userLoginDO.setSex(wxUser.getSex());
				userLoginDO.setAccessToken(TokenUtils.creatAesStr(user.getSchoolNum()));
				userLoginDO.setIsBound(Integer.valueOf(1));
				userLoginDao.insert(userLoginDO);
			} else {
				userLoginDO = new UserLoginDO();
				userLoginDO.setOpenId(wxUser.getOpenId());
				userLoginDO.setAccessToken(TokenUtils.creatAesStr(user.getSchoolNum()));
				userLoginDO.setIsBound(Integer.valueOf(1));
				userLoginDao.update(userLoginDO);
			}
			responseDO.setDataResult(userLoginDO);
		} else {
			UserLoginDO unbound = new UserLoginDO();
			unbound.setOpenId(wxUser.getOpenId());
			unbound.setAccessToken(wxUser.getOpenId());
			unbound.setLoginAccount(wxUser.getNickName());
			unbound.setHeadimgurl(wxUser.getHeadimgurl());
			unbound.setSex(wxUser.getSex());
			unbound.setIsBound(Integer.valueOf(2));
			userLoginDao.insert(unbound);
			responseDO.setMessage("未绑定学号");
			responseDO.setDataResult(unbound);
		}
		return responseDO;
	}

	@Override
	public ResponseDO<BindingDO> userBinding(BindingDO binding) {
		ResponseDO<BindingDO> responseDO = new ResponseDO<>();
		BingDO bing = new BingDO();
		UserDO userBinding = userDao.seletUserBySnum(binding.getSchoolNum());
		if(userBinding == null) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("无此学号");
			return responseDO;
		}
		if (!userBinding.getPassword().equals(binding.getPassword())) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("密码错误");
			return responseDO;
		}
		if (userBinding.getIsBinding().longValue() == 1L) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("学号未绑定");
			return responseDO;
		}
		if ((userBinding != null) && (userBinding.getIsBinding().longValue() == 2L)) {
			UserLoginDO userLoginDO = userLoginDao.seletUserByAs(binding.getAsToken());
			if (binding.getAsToken() != null) {
				UserLoginDO user = userLoginDao.seletIsBinding(binding.getAsToken());
				if (user.getIsBound().intValue() == 2) {
					bing.setSchoolNum(binding.getSchoolNum());
					bing.setUserName(user.getLoginAccount());
					bing.setPassword(binding.getPassword());
					bing.setUserId(user.getOpenId());
					bing.setSex(user.getSex());
					bing.setHeadimgurl(user.getHeadimgurl());
					bing.setPhone(binding.getPhone());
					userDao.updateBinding(bing);
					userLoginDO.setAccessToken(TokenUtils.creatAesStr(binding.getSchoolNum()));
					userLoginDO.setIsBound(Integer.valueOf(1));
					binding.setAsToken(TokenUtils.creatAesStr(binding.getSchoolNum()));
					userLoginDao.update(userLoginDO);
					responseDO.setDataResult(binding);
					responseDO.setMessage("绑定成功");
					return responseDO;
				}
			}
			return responseDO;
		}
		return responseDO;
	}

	@Override
	public void saveVerification(VerificationDO verification) {
		verificationDao.saveVerCode(verification);
	}

	@Override
	public ResponseDO<String> verifica(VerificationDO verification) {
		ResponseDO<String> responseVer = new ResponseDO<String>();
		VerificationDO verificationTure = verificationDao.seletVerification(verification.getPhone());
		if(!verificationTure.getCode().equals(verification.getCode())) {
			responseVer.setCode(ResponseCode.ERROR);
			responseVer.setMessage("验证失败");
			return responseVer;
		}
		return responseVer;
	}

	@Override
	public ResponseDO<CantBindingDO> cantBinding(CantBindingDO binding) {
		ResponseDO<CantBindingDO> responseDO = new ResponseDO<>();
		BingDO bing = new BingDO();
		UserDO userBinding = userDao.seletUserBySnum(binding.getSchoolNum());
		if(userBinding == null) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("无此学号");
			return responseDO;
		}
		if (!userBinding.getPassword().equals(binding.getPassword())) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("密码错误");
			return responseDO;
		}
		if (userBinding.getIsBinding().longValue() == 1L) {
			responseDO.setCode(ResponseCode.ERROR);
			responseDO.setMessage("学号未绑定");
			return responseDO;
		}
		if ((userBinding != null) && (userBinding.getIsBinding().longValue() == 2L)) {
			UserLoginDO userLoginDO = userLoginDao.seletUserByName(binding.getUserName());
			if (userLoginDO.getAccessToken() != null) {
				UserLoginDO user = userLoginDao.seletIsBinding(userLoginDO.getAccessToken());
				if (user.getIsBound().intValue() == 2) {
					bing.setSchoolNum(binding.getSchoolNum());
					bing.setPassword(binding.getPassword());
					bing.setUserName(user.getLoginAccount());
					bing.setUserId(user.getOpenId());
					bing.setSex(user.getSex());
					bing.setHeadimgurl(user.getHeadimgurl());
					bing.setPhone(binding.getPhone());
					userDao.updateBinding(bing);
					userLoginDO.setAccessToken(TokenUtils.creatAesStr(binding.getSchoolNum()));
					userLoginDO.setIsBound(Integer.valueOf(1));
					binding.setAsToken(TokenUtils.creatAesStr(binding.getSchoolNum()));
					userLoginDao.update(userLoginDO);
					responseDO.setDataResult(binding);
					responseDO.setMessage("绑定成功");
					return responseDO;
				}
			}
			return responseDO;
		}
		return responseDO;
	}

	@Override
	public BackgroundDO background() {
		return userBackgroudDao.getBackground();
	}
}