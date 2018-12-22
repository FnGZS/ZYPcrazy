package com.crazyBird.dao.luck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.luck.dataobject.LuckPrizeDO;

public abstract interface LuckPrizeDao {

	List<LuckPrizeDO> getLuckPrize(@Param("luckId") Long luckId);

}
