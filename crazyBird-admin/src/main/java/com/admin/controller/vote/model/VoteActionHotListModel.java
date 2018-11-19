package com.admin.controller.vote.model;

import java.util.List;

import com.admin.controller.base.AbstractFlagModel;


public class VoteActionHotListModel extends AbstractFlagModel{
	private List<VoteActionHotItem> voteList;

	public List<VoteActionHotItem> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<VoteActionHotItem> voteList) {
		this.voteList = voteList;
	}
}
