package com.crazyBird.controller.help.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;



public class HelpModel extends AbstractFlagModel{
	private List<HelpItem> items;

	public List<HelpItem> getItems() {
		return items;
	}

	public void setItems(List<HelpItem> items) {
		this.items = items;
	}

	public HelpModel() {
	}

	public HelpModel(List<HelpItem> items) {
		this.items = items;
	}
}
