package com.admin.dao.vote.dataobject;


public class VoteActionHotDTO extends VoteActionDO{
	public Long getReId() {
		return reId;
	}

	public void setReId(Long reId) {
		this.reId = reId;
	}

	private Long reId;
	private Integer type;
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	
}
