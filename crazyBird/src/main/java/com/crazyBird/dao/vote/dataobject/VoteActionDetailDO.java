package com.crazyBird.dao.vote.dataobject;

public class VoteActionDetailDO {
	private Long id;
	private Long actionId;
	private Long serialId;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	private String imageUrl;
	private String className;
	public String getBaseInfor() {
		return baseInfor;
	}
	public void setBaseInfor(String baseInfor) {
		this.baseInfor = baseInfor;
	}
	private String baseInfor;
	private Long num;
	private String peopleName;
	public String getPeopleName() {
		return peopleName;
	}
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	public Long getSerialId() {
		return serialId;
	}
	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

}
