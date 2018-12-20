package com.crazyBird.service.user;

import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;

public interface UserPayService {
	int insertOrder(UserWxPayOrderDO orderDO);
}
