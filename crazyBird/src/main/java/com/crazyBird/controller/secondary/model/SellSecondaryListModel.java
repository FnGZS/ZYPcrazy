package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SellSecondaryListModel extends AbstractPageFlagModel{

	private List<SellSecondaryItem> tags;

	public List<SellSecondaryItem> getTags() {
		return tags;
	}

	public void setTags(List<SellSecondaryItem> tags) {
		this.tags = tags;
	}
	
}
