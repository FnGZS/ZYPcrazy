package com.crazyBird.dao.luck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.luck.dataobject.LuckDetailsDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;

public abstract interface LuckDrawDao {

	Integer getLuckListCount(LuckListPO po);

	List<LuckDetailsDO> getLuckList(LuckListPO po);

	LuckDetailsDO getLuckDetails(@Param("luckId") Long luckId);

	
}
