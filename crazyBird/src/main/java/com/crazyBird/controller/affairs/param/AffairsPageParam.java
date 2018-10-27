package com.crazyBird.controller.affairs.param;

import com.crazyBird.controller.base.AbstractOrderPageParam;

public class AffairsPageParam extends AbstractOrderPageParam {
	private Integer typeId;
	private String key;

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
