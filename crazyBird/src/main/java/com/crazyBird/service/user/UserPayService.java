package com.crazyBird.service.user;

import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;

public interface UserPayService {
	int insertOrder(UserWxPayOrderDO orderDO);
	int checkWxPayOrder(String transaction_id);
	int checkSecondaryOrder(String orderId);
	int updateSecondaryOrder(String out_trade_no);
	int insertRefundOrder(UserRefundDO refundDO);
}
