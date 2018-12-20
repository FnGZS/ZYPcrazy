package com.crazyBird.dao.secondary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.secondary.dataobject.GoodsExistDO;
import com.crazyBird.dao.secondary.dataobject.PurchasePO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;

public interface SecondaryOrderDao {

	int getSellSecondaryCountByUser(SellSecondaryPO po);

	List<UserSecondaryDTO> getSellSecondaryByUser(SellSecondaryPO po);

	int getPurchaseCountByUser(PurchasePO po);

	List<UserSecondaryDTO> getPurchaseByUser(PurchasePO po);

	boolean createOrder(SecondaryOrderDO order);

	GoodsExistDO getSecondaryGoods(Long goodsId);

}
