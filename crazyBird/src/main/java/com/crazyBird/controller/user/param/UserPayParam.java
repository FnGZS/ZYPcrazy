package com.crazyBird.controller.user.param;

import java.math.BigDecimal;
import java.util.Map;

import com.crazyBird.validate.annotation.RequireAnno;


public class UserPayParam {
	@RequireAnno
	private String platCode;
	private Map<String, String> platUserInfoMap;
	private double fee;

	public String getPlatCode() {
		return this.platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}

	public Map<String, String> getPlatUserInfoMap() {
		return this.platUserInfoMap;
	}

	

	

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public void setPlatUserInfoMap(Map<String, String> platUserInfoMap) {
		this.platUserInfoMap = platUserInfoMap;
	}
}
