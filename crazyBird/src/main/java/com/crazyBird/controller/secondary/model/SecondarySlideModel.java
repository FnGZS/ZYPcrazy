package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class SecondarySlideModel extends AbstractFlagModel{
	private List<SecondarySlideItem> list;

	public List<SecondarySlideItem> getList() {
		return list;
	}

	public void setList(List<SecondarySlideItem> list) {
		this.list = list;
	}
	
}
