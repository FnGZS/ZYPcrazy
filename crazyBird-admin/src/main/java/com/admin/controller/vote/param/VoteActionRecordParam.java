package com.admin.controller.vote.param;

import com.admin.controller.base.AbstractPageParam;

import ch.qos.logback.core.subst.Token.Type;

public class VoteActionRecordParam extends AbstractPageParam{
	private Long id;
	private Integer type;
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
