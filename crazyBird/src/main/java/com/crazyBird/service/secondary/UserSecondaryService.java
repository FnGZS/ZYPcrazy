package com.crazyBird.service.secondary;

import java.util.List;

import com.crazyBird.dao.secondary.dataobject.CollectionDO;
import com.crazyBird.dao.secondary.dataobject.CollectionSecondaryListPO;
import com.crazyBird.dao.secondary.dataobject.PurchasePO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public interface UserSecondaryService {

	ResponsePageQueryDO<List<UserSecondaryDTO>> getSellList(SellSecondaryPO po);

	ResponsePageQueryDO<List<UserSecondaryDTO>> getPurchaseSecondary(PurchasePO po);

	ResponseDO<String> collection(CollectionDO collection);

	ResponsePageQueryDO<List<UserSecondaryDTO>> getCollectionSecondaryList(CollectionSecondaryListPO po);

	ResponseDO<String> isCollection(CollectionDO collection);

	Integer getCollectionNum(Long goodsId);

}
