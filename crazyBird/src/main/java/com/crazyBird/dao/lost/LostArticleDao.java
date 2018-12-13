package com.crazyBird.dao.lost;

import java.util.List;

import com.crazyBird.dao.lost.dataobject.LostArticleDO;
import com.crazyBird.dao.lost.dataobject.LostDTO;
import com.crazyBird.dao.lost.dataobject.LostPO;

public abstract interface LostArticleDao {

	Integer getLostCount(LostPO po);

	List<LostDTO> getLost(LostPO po);

	LostDTO getLostDetails(Long id);

	int insert(LostArticleDO DO);

	void updateBrow(LostDTO lost);

	int delete(Long id);

}
