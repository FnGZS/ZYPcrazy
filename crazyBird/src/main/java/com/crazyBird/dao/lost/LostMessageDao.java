package com.crazyBird.dao.lost;

import java.util.List;

import com.crazyBird.dao.lost.dataobject.LostTypeDO;

public abstract interface LostMessageDao {

	List<LostTypeDO> getLostMessage();

}
