package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class SecondaryTypeModel extends AbstractFlagModel{
	private List<SecondaryTypeItem> list;

	public List<SecondaryTypeItem> getList() {
		return list;
	}

	public void setList(List<SecondaryTypeItem> list) {
		this.list = list;
	}
	
}
