package com.admin.controller.vote.model;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.admin.controller.base.AbstractFlagModel;


public class VoteActionDetailListModel extends AbstractFlagModel{
	private List<VoteActionDetailItem> voteDetailList;
	public List<VoteActionDetailItem> getVoteDetailList() {
		return voteDetailList;
	}
	public void setVoteDetailList(List<VoteActionDetailItem> voteDetailList) {
		this.voteDetailList = voteDetailList;
	}
}
