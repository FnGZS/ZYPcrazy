package com.crazyBird.controller.user.model;



public class BillItem {
	private Long id;
	private Long userId;
	private String message;
	private String title;
	private Integer type;
	private String cash;
	private String gmtCreated;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCash() {
		return cash;
	}
	public void setCash(String cash) {
		this.cash = cash;
	}
	
	
}
