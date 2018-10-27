package com.admin.service.user;


import com.admin.dao.user.dataobject.BindingDO;
import com.admin.dao.user.dataobject.LoginDO;
import com.admin.dao.user.dataobject.UserLoginDO;
import com.admin.service.base.ResponseDO;


public interface UserLoginService {
	
	ResponseDO<UserLoginDO> getUserLogin(String unionId);
//	ResponseDO<UserLoginDO> getUserLogin(Long userId, Integer deviceType);
//	ResponseDO<UserLoginDTO> doPasswordLogin(PasswordLoginPO loginPO);

	ResponseDO<UserLoginDO> userLogin(LoginDO wxUser);

	ResponseDO<String> userBinding(BindingDO binding);

//	ResponseDO<UserLoginDTO> doPlatLogin(PlatLoginPO platLoginPO);
	
//	void doLoginOut(UserLoginPO userLoginPO);

	//ResponseDO<UserLoginDTO> login(UserAccountDO user, LoginPO loginPO);
	
	//String updateAccessToken(Long userId, String account, String agent);
}
