package com.crazyBird.dao.user.dataobject;

public class BindingDO {
	private Long id;
	private Long schoolNum;
	private String password;
	private String asToken;
	private String userId;
	private String phone;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAsToken() {
		return asToken;
	}

	public void setAsToken(String asToken) {
		this.asToken = asToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	
}
