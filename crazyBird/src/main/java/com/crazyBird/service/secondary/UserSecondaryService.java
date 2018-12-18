package com.crazyBird.service.secondary;

import java.util.List;

import com.crazyBird.dao.secondary.dataobject.SellSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public interface UserSecondaryService {

	ResponsePageQueryDO<List<SellSecondaryDTO>> getSellList(SellSecondaryPO po);

}
