package com.crazyBird.controller.lost.model;

import java.util.List;

public class LostCommentItem {
	private Integer id;
	private String comment;
	private Integer articleId;
	private String gmtCreated;
	private List<LostCommentListItem> item;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public List<LostCommentListItem> getItem() {
		return item;
	}
	public void setItem(List<LostCommentListItem> item) {
		this.item = item;
	}

}
