package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SecondaryGoodsCommentsModel extends AbstractPageFlagModel{
	private int commentsNum;
	

	public int getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}

	private List<SecondaryGoodsCommentItem> list;

	public List<SecondaryGoodsCommentItem> getList() {
		return list;
	}

	public void setList(List<SecondaryGoodsCommentItem> list) {
		this.list = list;
	}
	
}
