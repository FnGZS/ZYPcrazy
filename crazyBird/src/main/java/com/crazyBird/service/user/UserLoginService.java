package com.crazyBird.service.user;

import com.crazyBird.dao.user.dataobject.BindingDO;
import com.crazyBird.dao.user.dataobject.LoginDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;
import com.crazyBird.dao.user.dataobject.VerificationDO;
import com.crazyBird.service.base.ResponseDO;

public abstract interface UserLoginService {
	ResponseDO<UserLoginDO> getUserLogin(Long paramLong);

	ResponseDO<UserLoginDO> userLogin(LoginDO paramLoginDO);

	ResponseDO<BindingDO> userBinding(BindingDO paramBindingDO);

	void saveVerification(VerificationDO verification);

	ResponseDO<String> verifica(VerificationDO verification);
}
