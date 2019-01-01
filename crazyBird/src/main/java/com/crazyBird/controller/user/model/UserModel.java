package com.crazyBird.controller.user.model;

import com.crazyBird.controller.base.AbstractFlagModel;

public class UserModel extends AbstractFlagModel {
	private Long userId;
	private String loginAccount;
	private String userName;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginAccount() {
		return this.loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
