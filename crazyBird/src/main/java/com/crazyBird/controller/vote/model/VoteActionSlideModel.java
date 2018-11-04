package com.crazyBird.controller.vote.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class VoteActionSlideModel extends AbstractFlagModel{
	private List<VoteActionSlideItem> items;

	public List<VoteActionSlideItem> getItems() {
		return items;
	}

	public void setItems(List<VoteActionSlideItem> items) {
			this.items = items;
	}
}
