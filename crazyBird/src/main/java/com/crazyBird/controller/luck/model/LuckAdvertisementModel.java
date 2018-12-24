package com.crazyBird.controller.luck.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class LuckAdvertisementModel extends AbstractFlagModel{

	private List<LuckAdvertisementItem> items;

	public List<LuckAdvertisementItem> getItems() {
		return items;
	}

	public void setItems(List<LuckAdvertisementItem> items) {
		this.items = items;
	}
	
}
