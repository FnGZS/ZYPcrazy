package com.crazyBird.dao.luck;

import java.util.List;

import com.crazyBird.dao.luck.dataobject.LuckLuckPartakeDO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;

public abstract interface LuckActorDao {
	Integer getLuckWinnersCount(LuckWinnersPO po);

	List<LuckWinnersDO> getLuckWinners(LuckWinnersPO po);

	Integer getLuckPartakeCount(LuckPartakePO po);

	List<LuckLuckPartakeDO> getLuckPartake(LuckPartakePO po);

}
