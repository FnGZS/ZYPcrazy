package com.crazyBird.dao.secondary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.secondary.dataobject.DeleteSecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.GoodsExistDO;
import com.crazyBird.dao.secondary.dataobject.PurchasePO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCapitalDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCashDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderListPO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;
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
	
	int updateSecondaryOrder(String out_trade_no);
	
	int updateSecondaryOrderRefund(String out_trade_no);
	
	int updateSecondaryOrderAccept(String orderId);

	SecondaryCapitalDO getSecondaryCapital(Long userId);

	boolean setSecondaryCash(SecondaryCashDO input);
	
	int checkSecondaryGoodsPayStatus(Long id);


}
