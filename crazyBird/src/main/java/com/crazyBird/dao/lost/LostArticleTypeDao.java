package com.crazyBird.dao.lost;

import java.util.List;

import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.lost.dataobject.LostDO;
import com.crazyBird.dao.lost.dataobject.LostPO;
import com.crazyBird.dao.lost.dataobject.LostTypeDO;

public abstract interface LostArticleTypeDao {

	List<LostTypeDO> getLostType();
}
