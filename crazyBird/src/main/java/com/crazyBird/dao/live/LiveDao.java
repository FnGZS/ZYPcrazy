package com.crazyBird.dao.live;

import java.util.List;

import com.crazyBird.dao.live.dataobject.LiveDO;
import com.crazyBird.dao.live.dataobject.LiveGiftDO;

public interface LiveDao {
	List<LiveDO> getPlayList();

	LiveDO getPlayUrl(Integer id);
	
	List<LiveGiftDO> getLiveGiftList();
	
}
