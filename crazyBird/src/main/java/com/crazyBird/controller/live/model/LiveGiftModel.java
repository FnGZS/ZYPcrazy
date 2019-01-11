package com.crazyBird.controller.live.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class LiveGiftModel extends AbstractFlagModel{
	private List<LiveGiftItem> list;

	public List<LiveGiftItem> getList() {
		return list;
	}

	public void setList(List<LiveGiftItem> list) {
		this.list = list;
	}
	
}
