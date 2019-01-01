package com.crazyBird.controller.luck.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class LuckPartakeModel extends AbstractPageFlagModel {

	private List<LuckPartakeItems> items;
	public List<LuckPartakeItems> getItems() {
		return items;
	}

	public void setItems(List<LuckPartakeItems> items) {
		this.items = items;
	}
	
	
}
