package com.crazyBird.controller.lost.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractPageFlagModel;

public class LostCommentPageModel extends AbstractPageFlagModel{
	private List<LostCommentItem> items;

	public List<LostCommentItem> getItems() {
		return items;
	}

	public void setItems(List<LostCommentItem> items) {
		this.items = items;
	}
	
}
