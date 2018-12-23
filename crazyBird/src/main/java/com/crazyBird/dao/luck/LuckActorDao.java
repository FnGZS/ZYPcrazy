package com.crazyBird.dao.luck;

import java.util.List;

import com.crazyBird.dao.luck.dataobject.IsPartDO;
import com.crazyBird.dao.luck.dataobject.LuckPartakeDTO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDTO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;

public abstract interface LuckActorDao {
	Integer getLuckWinnersCount(LuckWinnersPO po);

	List<LuckWinnersDTO> getLuckWinners(LuckWinnersPO po);

	Integer getLuckPartakeCount(LuckPartakePO po);

	List<LuckPartakeDTO> getLuckPartake(LuckPartakePO po);

	LuckPartakeDTO seletPart(IsPartDO isPart);

}
