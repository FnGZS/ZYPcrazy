package com.crazyBird.dao.live;

import java.util.List;

import com.crazyBird.dao.live.dataobject.LiveDO;

public interface LiveDao {
	List<LiveDO> getPlayList();
	
}
