package com.crazyBird.service.secondary;

import java.util.List;

import com.crazyBird.dao.secondary.dataobject.CapitalUserDO;
import com.crazyBird.dao.secondary.dataobject.DeleteSecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.RefundApplyDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCapitalDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCashDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderListPO;
import com.crazyBird.dao.secondary.dataobject.VendorListPO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public interface SecondaryOrderService {

	ResponseDO<String> createOrder(SecondaryOrderDO order);

	ResponsePageQueryDO<List<SecondaryOrderDTO>> getOrderList(SecondaryOrderListPO po);

	ResponseDO<String> deleteSecondaryOrder(DeleteSecondaryOrderDO deleteOrder);
	
	int updateSecondaryOrderRefund(String out_trade_no);
	
	int checkSecondaryOrder(String out_trade_no);
	
	int updateSecondaryOrderAccept(SecondaryOrderDO	orderDO );
	
	int updateSecondaryOrderDelivery(SecondaryOrderDO	orderDO);
	
	int updateSecondaryOrderApplyRefund(SecondaryOrderDO	orderDO);
	
	boolean insertRefundApply(RefundApplyDO applyDO);
	
	ResponseDO<SecondaryCashDO> setSecondaryCash(SecondaryCashDO input);

	SecondaryCapitalDO getSecondaryCapital(Long id);
	
	int checkSecondaryGoodsPayStatus(Long id);

	ResponsePageQueryDO<List<SecondaryOrderDTO>> getVendorOrderList(VendorListPO po);

	SecondaryOrderDTO getOrderDetails(String orderId);
	
	boolean createCapitalUser(Long userId);
	
	SecondaryOrderDO  getSecondaryOrderDetail(String orderId);
	
	int updateCapitalUser(CapitalUserDO capitalUserDO);
	
	boolean cancelSecondaryOrder(DeleteSecondaryOrderDO deleteOrder);

}
