package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SecondaryOrderListModel extends AbstractPageFlagModel{

	private List<SecondaryOrderItem> tags;

	public List<SecondaryOrderItem> getTags() {
		return tags;
	}

	public void setTags(List<SecondaryOrderItem> tags) {
		this.tags = tags;
	}
	
}
