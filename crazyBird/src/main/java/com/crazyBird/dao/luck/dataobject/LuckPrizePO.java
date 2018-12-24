package com.crazyBird.dao.luck.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class LuckPrizePO extends PageQueryDO {

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
