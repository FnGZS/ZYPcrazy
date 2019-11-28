package com.crazyBird.service.user;

import com.crazyBird.controller.user.param.JYZParam;
import com.crazyBird.dao.affairs.dataobject.CantBindingDO;
import com.crazyBird.dao.user.dataobject.BackgroundDO;
import com.crazyBird.dao.user.dataobject.BindingChangeDO;
import com.crazyBird.dao.user.dataobject.BindingDO;
import com.crazyBird.dao.user.dataobject.HavePhoneUserDO;
import com.crazyBird.dao.user.dataobject.LoginDO;
import com.crazyBird.dao.user.dataobject.UserFormDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.dao.user.dataobject.VerificationDO;
import com.crazyBird.service.base.ResponseDO;

public abstract interface UserLoginService {
	ResponseDO<UserLoginDO> getUserLogin(Long paramLong);

	ResponseDO<UserLoginDO> userLogin(LoginDO paramLoginDO);

	ResponseDO<BindingDO> userBinding(BindingDO paramBindingDO);

	void saveVerification(VerificationDO verification);

	ResponseDO<String> verifica(VerificationDO verification);

	ResponseDO<CantBindingDO> cantBinding(CantBindingDO binding);

	BackgroundDO background();

	ResponseDO<HavePhoneUserDO> getHavePhoneUser(String phone, String accessToken);

	ResponseDO<BindingDO> changeBind(BindingChangeDO bindingChange);
	
	void insertFormId(UserFormDO fromDO);
	
	UserFormDO getFormId(Long userId);
	
	void deleteFormId(Long id);

	void addjyz(JYZParam param);
}
