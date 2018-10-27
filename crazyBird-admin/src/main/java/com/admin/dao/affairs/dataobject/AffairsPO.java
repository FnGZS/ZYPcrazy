package com.admin.dao.affairs.dataobject;

import com.admin.service.base.PageQueryDO;

public class AffairsPO extends PageQueryDO{
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
