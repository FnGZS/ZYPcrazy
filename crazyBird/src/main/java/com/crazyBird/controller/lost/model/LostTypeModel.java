package com.crazyBird.controller.lost.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class LostTypeModel  extends AbstractFlagModel{
	private List<LostTypeItem> lostTypeList;

	public List<LostTypeItem> getLostTypeList() {
		return lostTypeList;
	}

	public void setLostTypeList(List<LostTypeItem> lostTypeList) {
		this.lostTypeList = lostTypeList;
	}

	

}
