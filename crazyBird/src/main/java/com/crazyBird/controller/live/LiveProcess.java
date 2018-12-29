package com.crazyBird.controller.live;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.live.model.LivePlayUrlDetailModel;
import com.crazyBird.controller.live.model.LivePlayUrlItem;
import com.crazyBird.controller.live.model.LivePlayUrlModel;
import com.crazyBird.dao.live.dataobject.LiveDO;
import com.crazyBird.service.live.LiveService;
import com.crazyBird.utils.DateUtil;
@Component
public class LiveProcess {
	@Autowired
	private LiveService liveService;
	public LivePlayUrlModel getPlayUrl() {
		LivePlayUrlModel model = new LivePlayUrlModel();
		List<LiveDO> tags = liveService.getPlayList();
		List<LivePlayUrlItem> items = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(tags)) {
			for (LiveDO tag : tags) {
				LivePlayUrlItem item = new LivePlayUrlItem();
				item.setId(tag.getId());
				item.setStatus(tag.getStatus());
				item.setPlayUrl(tag.getPlayUrl());
				item.setTitle(tag.getTitle());
				item.setStreamId(tag.getStreamId());
				item.setEndTime(DateUtil.formatDate(tag.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
				items.add(item);
			}
		}
	
		model.setList(items);
		return model;	
	}
	public LivePlayUrlDetailModel getPlayUrlDetail(Integer id) {
		LivePlayUrlDetailModel model = new LivePlayUrlDetailModel();
		LiveDO liveDO = liveService.getPlayUrl(id);
		model.setId(liveDO.getId());
		model.setStatus(liveDO.getStatus());
		model.setPlayUrl(liveDO.getPlayUrl());
		model.setTitle(liveDO.getTitle());
		model.setStreamId(liveDO.getStreamId());
		model.setEndTime(DateUtil.formatDate(liveDO.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
		return model;
		
	}
	public SimpleFlagModel getPlayStatic() {
		SimpleFlagModel model = new SimpleFlagModel();
		liveService.liveStatistical();
		return  model;
	}
	
}
