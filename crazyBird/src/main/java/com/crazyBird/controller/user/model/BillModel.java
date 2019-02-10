package com.crazyBird.controller.user.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class BillModel extends AbstractPageFlagModel{
	private  List<BillItem> list ;

	public List<BillItem> getList() {
		return list;
	}

	public void setList(List<BillItem> list) {
		this.list = list;
	}
	
}
