package com.crazyBird.dao.lost;

import java.util.List;

import com.crazyBird.dao.lost.dataobject.LostArticleDO;
import com.crazyBird.dao.lost.dataobject.LostDO;
import com.crazyBird.dao.lost.dataobject.LostPO;

public abstract interface LostArticleDao {

	Integer getLostCount(LostPO po);

	List<LostDO> getLost(LostPO po);

	LostDO getLostDetails(Long id);

	int insert(LostArticleDO DO);

	void updateBrow(LostDO lost);

	int delete(Long id);

}
