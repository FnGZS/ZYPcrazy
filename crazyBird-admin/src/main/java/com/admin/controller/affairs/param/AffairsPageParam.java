package com.admin.controller.affairs.param;

import com.admin.controller.base.AbstractOrderPageParam;

public class AffairsPageParam extends AbstractOrderPageParam{

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
