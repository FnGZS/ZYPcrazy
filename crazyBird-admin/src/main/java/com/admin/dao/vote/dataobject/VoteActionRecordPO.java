package com.admin.dao.vote.dataobject;

import com.admin.service.base.PageQueryDO;

public class VoteActionRecordPO extends PageQueryDO{
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Integer type;
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}



}
