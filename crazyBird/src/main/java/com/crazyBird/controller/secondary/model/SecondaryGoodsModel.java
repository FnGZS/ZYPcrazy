package com.crazyBird.controller.secondary.model;

import java.util.List;


import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SecondaryGoodsModel extends AbstractPageFlagModel{
	private List<SecondaryGoodsItem> list;

	public List<SecondaryGoodsItem> getList() {
		return list;
	}

	public void setList(List<SecondaryGoodsItem> list) {
		this.list = list;
	}
	
}
