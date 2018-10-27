package com.admin.controller.vote.model;

import java.util.List;
import com.admin.controller.base.AbstractPageFlagModel;

public class VoteActionRecordModel extends AbstractPageFlagModel{
	private List<VoteActionRecordItem> voteRecord ;

	public List<VoteActionRecordItem> getVoteRecord() {
		return voteRecord;
	}

	public void setVoteRecord(List<VoteActionRecordItem> voteRecord) {
		this.voteRecord = voteRecord;
	}	
}
