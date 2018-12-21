package com.crazyBird.dao.secondary.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class CollectionSecondaryListPO extends PageQueryDO{

	private Long userId;
	private Integer status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
