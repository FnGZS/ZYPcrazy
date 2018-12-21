package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class SecondaryUserAddressModel extends AbstractFlagModel{
	private List<SecondaryUserAddressItem> list;

	public List<SecondaryUserAddressItem> getList() {
		return list;
	}

	public void setList(List<SecondaryUserAddressItem> list) {
		this.list = list;
	}
	
}
