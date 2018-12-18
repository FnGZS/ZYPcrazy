package com.crazyBird.controller.secondary.model;

import java.util.List;

public class SecondaryGoodsCommentItem {
	private Long id;
	private Long schoolNum;
	private String content;
	private String gmtCreated;
	private String headImgUrl;
	private String commentName;
	private List<SecondaryGoodsReplyItem> items;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(Long schoolNum) {
		this.schoolNum = schoolNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public List<SecondaryGoodsReplyItem> getItems() {
		return items;
	}
	public void setItems(List<SecondaryGoodsReplyItem> items) {
		this.items = items;
	}
	
	
}
