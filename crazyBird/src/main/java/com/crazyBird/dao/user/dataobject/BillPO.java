package com.crazyBird.dao.user.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class BillPO extends PageQueryDO{
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
