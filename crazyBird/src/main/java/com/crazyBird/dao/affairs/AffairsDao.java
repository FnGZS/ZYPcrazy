package com.crazyBird.dao.affairs;

import com.crazyBird.dao.affairs.dataobject.AddAffairDO;
import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import java.util.List;

public abstract interface AffairsDao {
	Integer getAffairsCount(AffairsPO po);

	List<AffairsDO> getAffairs(AffairsPO po);

	void addAffairs(AddAffairDO affair);

	AffairsDO getAffairsDetails(Long id);

	void update(AffairsDO affairs);
}
