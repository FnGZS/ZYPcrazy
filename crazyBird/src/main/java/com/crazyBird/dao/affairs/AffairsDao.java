package com.crazyBird.dao.affairs;

import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import java.util.List;

public abstract interface AffairsDao {
	int getAffairsCount(AffairsPO paramAffairsPO);

	List<AffairsDO> getAffairs(AffairsPO paramAffairsPO);
}
