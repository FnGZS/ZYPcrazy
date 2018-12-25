package com.crazyBird.service.live;

import java.util.List;

import com.crazyBird.dao.live.dataobject.LiveDO;

public interface LiveService {
	List<LiveDO> getPlayList();
}
