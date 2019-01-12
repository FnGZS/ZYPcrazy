package com.crazyBird.dao.user;

import com.crazyBird.dao.user.dataobject.BindingChangeDO;
import com.crazyBird.dao.user.dataobject.UserLoginDO;

public abstract interface UserLoginDao {
	UserLoginDO queryUserLogin(String paramString);

	UserLoginDO seletUserByOpenId(String paramString);

	void insert(UserLoginDO paramUserLoginDO);

	void update(UserLoginDO paramUserLoginDO);

	UserLoginDO seletIsBinding(String paramString);

	UserLoginDO seletUserByAs(String paramString);

	UserLoginDO seletUserByName(String userName);

	void changePhone(BindingChangeDO bindingChange);
}
