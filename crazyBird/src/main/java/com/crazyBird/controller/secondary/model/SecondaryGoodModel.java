package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class SecondaryGoodModel extends AbstractFlagModel{
	private List<SecondaryGoodsItem> list;

	public List<SecondaryGoodsItem> getList() {
		return list;
	}

	public void setList(List<SecondaryGoodsItem> list) {
		this.list = list;
	}
	
}
