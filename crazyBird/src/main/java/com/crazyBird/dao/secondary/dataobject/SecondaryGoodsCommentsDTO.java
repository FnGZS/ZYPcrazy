package com.crazyBird.dao.secondary.dataobject;

import java.util.Date;

public class SecondaryGoodsCommentsDTO {
	private Long id;
	private Long schoolNum;
	private String content;
	private Date gmtCreated;
	private String headImgUrl;
	private String replyName;
	private String replyedName;

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

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public String getReplyedName() {
		return replyedName;
	}

	public void setReplyedName(String replyedName) {
		this.replyedName = replyedName;
	}

}
