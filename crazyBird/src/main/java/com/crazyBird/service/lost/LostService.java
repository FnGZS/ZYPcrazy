package com.crazyBird.service.lost;

import java.util.List;

import com.crazyBird.dao.lost.dataobject.LostArticleDO;

import com.crazyBird.dao.lost.dataobject.LostDTO;
import com.crazyBird.dao.lost.dataobject.LostPO;
import com.crazyBird.dao.lost.dataobject.LostTypeDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public abstract interface LostService {


	ResponsePageQueryDO<List<LostDTO>> getLostList(LostPO po);

	List<LostTypeDO> getLostMessage();

	List<LostTypeDO> getLostType();

	LostDTO getLostDetails(Long id);

	ResponseDO<LostDTO> lostInput(LostArticleDO DO);

	ResponseDO<LostDTO> lostDelete(Long id);


}
