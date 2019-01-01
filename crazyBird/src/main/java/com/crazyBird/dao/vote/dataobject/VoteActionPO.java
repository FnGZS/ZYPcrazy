package com.crazyBird.dao.vote.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class VoteActionPO extends PageQueryDO{
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}