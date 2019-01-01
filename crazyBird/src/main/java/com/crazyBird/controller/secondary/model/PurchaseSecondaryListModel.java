package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class PurchaseSecondaryListModel extends AbstractPageFlagModel{

	private List<UserSecondaryItem> items;

	public List<UserSecondaryItem> getItems() {
		return items;
	}

	public void setItems(List<UserSecondaryItem> items) {
		this.items = items;
	}
	
}
