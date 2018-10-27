package com.crazyBird.dao.affairs.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class AffairsPO extends PageQueryDO {
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
