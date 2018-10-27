package com.admin.dao.user;

import com.admin.dao.user.dataobject.BindingDO;
import com.admin.dao.user.dataobject.UserDO;

public interface UserDao {

	UserDO seletUser(String openId);

	String seletIsBinding(String openId);

	void updateBinding(BindingDO binding);



}
