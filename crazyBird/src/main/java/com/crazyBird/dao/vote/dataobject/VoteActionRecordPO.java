package com.crazyBird.dao.vote.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class VoteActionRecordPO extends PageQueryDO{
	private Long studentId;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}


}
