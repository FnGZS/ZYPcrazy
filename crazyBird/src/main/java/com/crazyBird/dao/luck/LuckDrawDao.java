package com.crazyBird.dao.luck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.luck.dataobject.DeleasePO;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDTO;
import com.crazyBird.dao.luck.dataobject.LuckDrawDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;

public abstract interface LuckDrawDao {

	Integer getLuckListCount(LuckListPO po);

	List<LuckDetailsDTO> getLuckList(LuckListPO po);

	LuckDetailsDTO getLuckDetails(@Param("luckId") Long luckId);

	boolean addLuck(LuckDrawDO luckDraw);

	Integer getDeleaseCount(DeleasePO po);

	List<LuckDetailsDTO> getDelease(DeleasePO po);

	LuckDetailsDTO getDetailsByPrize(@Param("prizeId") Long prizeId);

	
}
