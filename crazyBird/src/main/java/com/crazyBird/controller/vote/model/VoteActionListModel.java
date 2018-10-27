package com.crazyBird.controller.vote.model;

import java.util.List;
import com.crazyBird.controller.base.AbstractPageFlagModel;

public class VoteActionListModel extends AbstractPageFlagModel{
	private List<VoteActionItem> voteList;

	public List<VoteActionItem> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<VoteActionItem> voteList) {
		this.voteList = voteList;
	}
}
