package com.crazyBird.controller.luck.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class LuckPrizeModel extends AbstractPageFlagModel {
	private List<LuckPrizeItems> items;

	public List<LuckPrizeItems> getItems() {
		return items;
	}

	public void setItems(List<LuckPrizeItems> items) {
		this.items = items;
	}
}
