package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SellSecondaryListModel extends AbstractPageFlagModel{

	private List<UserSecondaryItem> tags;

	public List<UserSecondaryItem> getTags() {
		return tags;
	}

	public void setTags(List<UserSecondaryItem> tags) {
		this.tags = tags;
	}
	
}
