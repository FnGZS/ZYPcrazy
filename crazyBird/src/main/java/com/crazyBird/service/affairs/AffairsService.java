package com.crazyBird.service.affairs;

import com.crazyBird.dao.affairs.dataobject.AddAffairDO;
import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import com.crazyBird.dao.affairs.dataobject.AffairsTypeDO;
import com.crazyBird.dao.affairs.dataobject.BroadDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import java.util.List;

public abstract interface AffairsService {
	ResponsePageQueryDO<List<AffairsDO>> getAffairsList(AffairsPO paramAffairsPO);

	ResponseDO<Long> addAffair(AddAffairDO affair);

	AffairsDO getAffairsDetails(Long id);

	List<AffairsTypeDO> getAffairsType();

	List<BroadDO> getBroad();


}
