package com.crazyBird.controller.secondary.param;

import com.crazyBird.controller.base.AbstractPageParam;

public class SecondaryGoodsByUserListParam extends AbstractPageParam{
	private Long id;
	private Integer status;

	public Long getId() {
		return id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
