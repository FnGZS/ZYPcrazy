package com.crazyBird.dao.secondary;

import java.util.List;

import com.crazyBird.dao.secondary.dataobject.SellSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;

public interface SecondaryUserDao {

	int getSellSecondaryCountByUser(Long userId);

	List<SellSecondaryDTO> getSellSecondaryByUser(SellSecondaryPO po);

}
