package com.crazyBird.controller.affairs.model;

import com.crazyBird.controller.base.AbstractPageFlagModel;
import java.util.List;

public class AffairsPageModel extends AbstractPageFlagModel {
	private List<AffairsItem> items;

	public List<AffairsItem> getItems() {
		return this.items;
	}

	public void setItems(List<AffairsItem> items) {
		this.items = items;
	}
}
