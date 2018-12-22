package com.crazyBird.service.luck;

import java.util.List;

import com.crazyBird.dao.luck.dataobject.LuckDetailsDO;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.dao.luck.dataobject.LuckLuckPartakeDO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckPrizeDO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public abstract interface LuckService {

	ResponsePageQueryDO<List<LuckDetailsDO>> getLuckList(LuckListPO po);

	ResponsePageQueryDO<List<LuckWinnersDO>> getLuckWinners(LuckWinnersPO po);

	ResponsePageQueryDO<List<LuckLuckPartakeDO>> getLuckPartake(LuckPartakePO po);

	LuckDetailsDO getLuckDetails(Long luckId);

	List<LuckPrizeDO> getLuckPrize(Long luckId);

}
