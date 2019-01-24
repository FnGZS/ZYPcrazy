package com.crazyBird.dao.secondary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.secondary.dataobject.CapitalUserDO;
import com.crazyBird.dao.secondary.dataobject.DeleteSecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.GoodsExistDO;
import com.crazyBird.dao.secondary.dataobject.PurchasePO;
import com.crazyBird.dao.secondary.dataobject.RefundApplyDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCapitalDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCashDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderListPO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.VendorListPO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;

public interface SecondaryOrderDao {

	int getSellSecondaryCountByUser(SellSecondaryPO po);

	List<UserSecondaryDTO> getSellSecondaryByUser(SellSecondaryPO po);

	int getPurchaseCountByUser(PurchasePO po);

	List<UserSecondaryDTO> getPurchaseByUser(PurchasePO po);

	boolean createOrder(SecondaryOrderDO order);

	GoodsExistDO getSecondaryGoods(Long goodsId);

	int getOrderListCount(SecondaryOrderListPO po);

	List<SecondaryOrderDTO> getOrderList(SecondaryOrderListPO po);

	boolean updateOrder(DeleteSecondaryOrderDO deleteOrder);
	
	int checkSecondaryOrder(String out_trade_no);
	
	boolean deleteSecondaryOrder(DeleteSecondaryOrderDO deleteOrder);
	
	int updateSecondaryOrder(String out_trade_no);
	
	int updateSecondaryOrderRefund(String out_trade_no);
	
	int updateSecondaryOrderAccept(SecondaryOrderDO	orderDO);
	
	int updateSecondaryOrderDelivery(SecondaryOrderDO	orderDO);

	int updateSecondaryOrderApplyRefund(SecondaryOrderDO	orderDO);
	
	boolean insertRefundApply(RefundApplyDO applyDO);

	SecondaryCapitalDO getSecondaryCapital(Long userId);

	boolean setSecondaryCash(SecondaryCashDO input);
	
	int checkSecondaryGoodsPayStatus(Long id);

	int getVendorOrderListCount(VendorListPO po);

	List<SecondaryOrderDTO> getVendorOrderList(VendorListPO po);

	SecondaryOrderDTO getOrderDetails(String orderId);
	
	boolean createCapitalUser(Long userId);
	
	SecondaryOrderDO  getSecondaryOrderDetail(String orderId);
	
	int updateCapitalUser(CapitalUserDO capitalUserDO);
	
	List<SecondaryOrderDO> getAutomaticAcceptList();
	



}
