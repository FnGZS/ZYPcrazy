package com.crazyBird.dao.live;

import java.util.List;

import com.crazyBird.dao.live.dataobject.LiveDO;
import com.crazyBird.dao.live.dataobject.LiveGiftDO;
import com.crazyBird.dao.live.dataobject.LiveOrderDO;

public interface LiveDao {
	List<LiveDO> getPlayList();

	LiveDO getPlayUrl(Integer id);
	
	List<LiveGiftDO> getLiveGiftList();
	
	int createGiftOrder(LiveOrderDO orderDO);
	
	int checkLiveOrder(String orderId);
	
	int updateLiveOrder(String out_trade_no);
	
}
