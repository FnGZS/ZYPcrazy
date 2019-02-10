package com.crazyBird.service.user;

import java.util.List;

import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.BillDO;
import com.crazyBird.dao.user.dataobject.BillDTO;
import com.crazyBird.dao.user.dataobject.BillPO;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public interface UserPayService {
	int insertOrder(UserWxPayOrderDO orderDO);
	int checkWxPayOrder(String transaction_id);
	int checkSecondaryOrder(String orderId);
	int updateSecondaryOrder(String out_trade_no);
	
	int checkLiveOrder(String orderId);
	int updateLiveOrder(String out_trade_no);
	int insertRefundOrder(UserRefundDO refundDO);
	
	//插入账单
	boolean insertBill(BillDO billDO);
	//得到账单
	ResponsePageQueryDO<List<BillDTO>> getBillList(BillPO po);;
}
