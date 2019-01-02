package com.crazyBird.service.live;

import java.util.List;

import com.crazyBird.dao.live.dataobject.LiveDO;
import com.crazyBird.dao.live.dataobject.LiveGiftDO;

public interface LiveService {
	List<LiveDO> getPlayList();
	LiveDO getPlayUrl(Integer id);
	void liveStatistical();
	List<LiveGiftDO> getLiveGiftList();
}
