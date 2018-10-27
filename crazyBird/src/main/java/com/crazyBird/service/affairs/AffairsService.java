package com.crazyBird.service.affairs;

import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import java.util.List;

public abstract interface AffairsService {
	ResponsePageQueryDO<List<AffairsDO>> getAffairsList(AffairsPO paramAffairsPO);
}
