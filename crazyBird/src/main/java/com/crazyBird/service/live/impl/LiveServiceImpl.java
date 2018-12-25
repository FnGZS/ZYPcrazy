package com.crazyBird.service.live.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.live.LiveDao;
import com.crazyBird.dao.live.dataobject.LiveDO;
import com.crazyBird.service.live.LiveService;
@Component("LiveService")
public class LiveServiceImpl implements LiveService{
	@Autowired
	private LiveDao liveDao;

	@Override
	public List<LiveDO> getPlayList() {
		
		return liveDao.getPlayList();
	}


	@Override
	public LiveDO getPlayUrl(Integer id) {
		// TODO Auto-generated method stub
		return liveDao.getPlayUrl(id);
	}
	
}
