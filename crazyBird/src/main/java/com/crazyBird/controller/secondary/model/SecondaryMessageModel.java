package com.crazyBird.controller.secondary.model;

import java.util.Date;
import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class SecondaryMessageModel extends AbstractFlagModel{
	private List<SecondaryMessageItem> list;

	public List<SecondaryMessageItem> getList() {
		return list;
	}

	public void setList(List<SecondaryMessageItem> list) {
		this.list = list;
	}
	
	
}
