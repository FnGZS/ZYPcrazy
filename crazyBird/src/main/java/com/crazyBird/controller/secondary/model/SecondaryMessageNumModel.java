package com.crazyBird.controller.secondary.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class SecondaryMessageNumModel extends AbstractFlagModel{
	private Integer sum;
	private List<SecondaryMessageNumItem> list;
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public List<SecondaryMessageNumItem> getList() {
		return list;
	}
	public void setList(List<SecondaryMessageNumItem> list) {
		this.list = list;
	}
	
}
