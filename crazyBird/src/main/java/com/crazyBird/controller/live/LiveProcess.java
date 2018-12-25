package com.crazyBird.controller.live;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
				item.setEndTime(DateUtil.formatDate(tag.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
				items.add(item);
			}
		}

		model.setList(items);
		return model;	
	}
}
