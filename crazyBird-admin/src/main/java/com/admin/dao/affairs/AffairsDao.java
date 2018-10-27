package com.admin.dao.affairs;

import java.util.List;

import com.admin.dao.affairs.dataobject.AffairsDO;
import com.admin.dao.affairs.dataobject.AffairsPO;

public interface AffairsDao {

	int getAffairsCount(AffairsPO po);

	List<AffairsDO> getAffairs(AffairsPO po);

}
