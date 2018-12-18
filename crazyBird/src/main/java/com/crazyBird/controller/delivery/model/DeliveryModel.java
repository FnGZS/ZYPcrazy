package com.crazyBird.controller.delivery.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class DeliveryModel extends AbstractFlagModel{

	private List<DeliveryItem> tags;

	public List<DeliveryItem> getTags() {
		return tags;
	}

	public void setTags(List<DeliveryItem> tags) {
		this.tags = tags;
	}
	
}
