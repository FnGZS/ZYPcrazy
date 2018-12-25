package com.crazyBird.dao.luck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crazyBird.dao.luck.dataobject.IsPartDO;
import com.crazyBird.dao.luck.dataobject.JoinListPO;
import com.crazyBird.dao.luck.dataobject.LuckActorDO;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDTO;
import com.crazyBird.dao.luck.dataobject.LuckPartakeDTO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckPrizeDO;
import com.crazyBird.dao.luck.dataobject.LuckPrizePO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDTO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;

public abstract interface LuckActorDao {
	Integer getLuckWinnersCount(LuckWinnersPO po);

	List<LuckWinnersDTO> getLuckWinners(LuckWinnersPO po);

	Integer getLuckPartakeCount(LuckPartakePO po);

	List<LuckPartakeDTO> getLuckPartake(LuckPartakePO po);

	LuckPartakeDTO seletPart(IsPartDO isPart);

	Integer getJoinListCount(JoinListPO po);

	List<LuckDetailsDTO> getJoinList(JoinListPO po);
	
	Integer getAwardListCount(LuckPrizePO po);

	List<LuckPrizeDO> getAwardList(LuckPrizePO po);

	LuckActorDO getLuckRandom(@Param("luckId") Long luckId);

	boolean updateActor(LuckActorDO random);



}
