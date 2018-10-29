package com.crazyBird.dao.affairs;

import java.util.List;

import com.crazyBird.dao.affairs.dataobject.AffairsTypeDO;

public abstract interface AffairsMenuDao {

	List<AffairsTypeDO> getAffairsType();

}
