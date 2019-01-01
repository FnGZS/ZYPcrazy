package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SecondaryCommetsMessageModel extends AbstractPageFlagModel{
	private List<SecondaryCommetsMessageItem> list;

	public List<SecondaryCommetsMessageItem> getList() {
		return list;
	}

	public void setList(List<SecondaryCommetsMessageItem> list) {
		this.list = list;
	}
	
}
