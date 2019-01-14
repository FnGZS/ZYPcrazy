package com.crazyBird.dao.user;

import java.util.List;

import com.crazyBird.dao.user.dataobject.BillDO;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;

public interface UserWxPayOrderDao {
	int insertOrder(UserWxPayOrderDO orderDO);
	int checkWxPayOrder(String transaction_id);
	int insertRefundOrder(UserRefundDO refundDO);
	//插入账单
	boolean insertBill(BillDO billDO);
	//得到账单
	List<BillDO> getBillList(Long userId);
}
