package com.crazyBird.controller.user.model;

import com.crazyBird.controller.base.AbstractFlagModel;

public class GetPhoneModel extends AbstractFlagModel{
	 private Integer result;
	 private String phone;
	 private String asToken;
	 
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getAsToken() {
		return asToken;
	}
	public void setAsToken(String asToken) {
		this.asToken = asToken;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
