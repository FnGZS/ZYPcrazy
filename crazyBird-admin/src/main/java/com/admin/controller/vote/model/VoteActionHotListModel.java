package com.admin.controller.vote.model;

import java.util.List;

import com.admin.controller.base.AbstractFlagModel;


public class VoteActionHotListModel extends AbstractFlagModel{
	private List<VoteActionItem> voteList;

	public List<VoteActionItem> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<VoteActionItem> voteList) {
		this.voteList = voteList;
	}
}
