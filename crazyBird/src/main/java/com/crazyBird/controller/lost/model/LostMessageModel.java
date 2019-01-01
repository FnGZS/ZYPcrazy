package com.crazyBird.controller.lost.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class LostMessageModel extends AbstractFlagModel{
	
	private List<LostMessageItem> lostTypeList;

	public List<LostMessageItem> getLostTypeList() {
		return lostTypeList;
	}

	public void setLostTypeList(List<LostMessageItem> lostTypeList) {
		this.lostTypeList = lostTypeList;
	}


	
}
