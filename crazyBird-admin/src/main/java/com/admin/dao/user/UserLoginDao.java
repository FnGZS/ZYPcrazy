package com.admin.dao.user;

import com.admin.dao.user.dataobject.UserLoginDO;
import com.admin.dao.user.dataobject.UserLoginPO;

/**
 * @Type UserLoginDao
 * @date 2018�?10�?5�?
 */
public interface UserLoginDao {

    UserLoginDO queryUserLogin(String openId);

	UserLoginDO seletUserByUnionid(String openId);

	void insert(UserLoginDO userLoginDO);

	void update(UserLoginDO userLoginDO);
}
