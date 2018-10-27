package com.admin.controller.affairs.model;

import java.util.List;

import com.admin.controller.base.AbstractPageFlagModel;

public class AffairsPageModel extends AbstractPageFlagModel{

	private List<AffairsItem> items ;

	public List<AffairsItem> getItems() {
		return items;
	}

	public void setItems(List<AffairsItem> items) {
		this.items = items;
	}
	
	
}
