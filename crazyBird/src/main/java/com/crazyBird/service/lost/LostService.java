package com.crazyBird.service.lost;

import java.util.List;

import com.crazyBird.dao.lost.dataobject.LostArticleDO;

import com.crazyBird.dao.lost.dataobject.LostDO;
import com.crazyBird.dao.lost.dataobject.LostPO;
import com.crazyBird.dao.lost.dataobject.LostTypeDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public abstract interface LostService {


	ResponsePageQueryDO<List<LostDO>> getLostList(LostPO po);

	List<LostTypeDO> getLostMessage();

	List<LostTypeDO> getLostType();

	LostDO getLostDetails(Long id);

	ResponseDO<LostDO> lostInput(LostArticleDO DO);

	ResponseDO<LostDO> lostDelete(Long id);


}
