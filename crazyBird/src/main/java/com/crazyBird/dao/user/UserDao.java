package com.crazyBird.dao.user;

import com.crazyBird.dao.user.dataobject.BindingDO;
import com.crazyBird.dao.user.dataobject.BingDO;
import com.crazyBird.dao.user.dataobject.UserDO;

public abstract interface UserDao {
	 UserDO seletUser(String paramString);

	 void updateBinding(BindingDO paramBindingDO);

	 void updateBinding(BingDO paramBingDO);

	 UserDO seletUserBySnum(Long paramLong);

	UserDO seletUserByPhone(String phone);

	void updateUser(UserDO phoneUser);
}
