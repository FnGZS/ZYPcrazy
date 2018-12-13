package com.crazyBird.controller.delivery.param;

import com.crazyBird.controller.base.AbstractOrderPageParam;

public class DeliveryPageParam extends AbstractOrderPageParam {

	private Integer typeId;
	private String key;
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
