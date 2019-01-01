package com.crazyBird.controller.innovate.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class InnovateEnterpriseListModel extends AbstractFlagModel{
	private List<InnovateEnterpriseItem> items;

	public List<InnovateEnterpriseItem> getItems() {
		return items;
	}

	public void setItems(List<InnovateEnterpriseItem> items) {
		this.items = items;
	}
	
}
