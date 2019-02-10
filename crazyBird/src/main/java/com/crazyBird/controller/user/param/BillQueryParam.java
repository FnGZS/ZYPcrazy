package com.crazyBird.controller.user.param;

import com.crazyBird.controller.base.AbstractPageParam;

public class BillQueryParam extends AbstractPageParam{
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
