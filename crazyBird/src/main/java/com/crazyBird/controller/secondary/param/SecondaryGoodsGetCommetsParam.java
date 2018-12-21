package com.crazyBird.controller.secondary.param;

import com.crazyBird.controller.base.AbstractPageParam;

public class SecondaryGoodsGetCommetsParam extends AbstractPageParam{
	private Long id;
	private Long userId;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
