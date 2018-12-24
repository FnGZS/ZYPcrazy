package com.crazyBird.controller.luck.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class LuckListModel extends AbstractPageFlagModel {

	private List<LuckListItem> items;

	public List<LuckListItem> getItems() {
		return items;
	}

	public void setItems(List<LuckListItem> items) {
		this.items = items;
	}
	
}
 