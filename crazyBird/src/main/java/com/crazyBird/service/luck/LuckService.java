package com.crazyBird.service.luck;

import java.util.List;

import com.crazyBird.dao.luck.dataobject.LuckListDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public abstract interface LuckService {

	ResponsePageQueryDO<List<LuckListDO>> getLuckList(LuckListPO po);

}
