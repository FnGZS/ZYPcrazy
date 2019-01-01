package com.crazyBird.controller.luck.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class LuckWinnersModel extends AbstractPageFlagModel {

	private List<LuckWinnersItems> items;

	public List<LuckWinnersItems> getItems() {
		return items;
	}

	public void setItems(List<LuckWinnersItems> items) {
		this.items = items;
	}
	
	
}
