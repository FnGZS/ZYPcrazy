package com.crazyBird.dao.luck;

import java.util.List;

import com.crazyBird.dao.luck.dataobject.LuckDetailsDO;
import com.crazyBird.dao.luck.dataobject.LuckListDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;

public abstract interface LuckDrawDao {

	Integer getLuckListCount(LuckListPO po);

	List<LuckListDO> getLuckList(LuckListPO po);

	LuckDetailsDO getLuckDetails(Long luckId);

	
}
