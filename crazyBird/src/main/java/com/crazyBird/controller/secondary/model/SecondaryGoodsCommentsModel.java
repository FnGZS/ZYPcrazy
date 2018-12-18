package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SecondaryGoodsCommentsModel extends AbstractPageFlagModel{
	private List<SecondaryGoodsCommentItem> list;

	public List<SecondaryGoodsCommentItem> getList() {
		return list;
	}

	public void setList(List<SecondaryGoodsCommentItem> list) {
		this.list = list;
	}
	
}
