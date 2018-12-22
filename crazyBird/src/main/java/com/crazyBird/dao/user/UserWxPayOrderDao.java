package com.crazyBird.dao.user;

import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;

public interface UserWxPayOrderDao {
	int insertOrder(UserWxPayOrderDO orderDO);
	int checkWxPayOrder(String transaction_id);

}
