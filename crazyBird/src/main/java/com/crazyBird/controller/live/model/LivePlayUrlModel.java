package com.crazyBird.controller.live.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class LivePlayUrlModel extends AbstractFlagModel{
	private List<LivePlayUrlItem> list;

	public List<LivePlayUrlItem> getList() {
		return list;
	}

	public void setList(List<LivePlayUrlItem> list) {
		this.list = list;
	}
	
}
