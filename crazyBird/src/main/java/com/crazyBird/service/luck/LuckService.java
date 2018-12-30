package com.crazyBird.service.luck;

import java.util.List;

import com.crazyBird.dao.luck.dataobject.AdvertisementDO;
import com.crazyBird.dao.luck.dataobject.DeleasePO;
import com.crazyBird.dao.luck.dataobject.IsPartDO;
import com.crazyBird.dao.luck.dataobject.JoinListPO;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDTO;
import com.crazyBird.dao.luck.dataobject.LuckDrawDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.dao.luck.dataobject.LuckPartakeDTO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckPrizeDO;
import com.crazyBird.dao.luck.dataobject.LuckPrizePO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDTO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public abstract interface LuckService {

	ResponsePageQueryDO<List<LuckDetailsDTO>> getLuckList(LuckListPO po);

	ResponsePageQueryDO<List<LuckWinnersDTO>> getLuckWinners(LuckWinnersPO po);

	ResponsePageQueryDO<List<LuckPartakeDTO>> getLuckPartake(LuckPartakePO po);

	LuckDetailsDTO getLuckDetails(Long luckId);

	List<LuckPrizeDO> getLuckPrize(Long luckId);

	ResponseDO<String> isPart(IsPartDO isPart);

	ResponseDO<String> addPrize(LuckPrizeDO luckPrize);

	ResponseDO<String> AddLuck(LuckDrawDO luckDraw);

	List<AdvertisementDO> getAdvertisement();

	ResponsePageQueryDO<List<LuckDetailsDTO>> delease(DeleasePO po);

	ResponsePageQueryDO<List<LuckDetailsDTO>> getJoinList(JoinListPO po);

	ResponsePageQueryDO<List<LuckPrizeDO>> awardList(LuckPrizePO po);

	LuckDetailsDTO getDetailsByPrize(Long prizeId);

	ResponseDO<String> autoLottery();

	ResponseDO<String> manualLottery(Long luckId);

	ResponseDO<String> part(IsPartDO isPart);

}
