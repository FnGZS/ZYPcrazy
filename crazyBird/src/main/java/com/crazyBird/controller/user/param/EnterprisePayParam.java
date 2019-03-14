package com.crazyBird.controller.user.param;

import java.util.Map;

import com.crazyBird.validate.annotation.RequireAnno;

public class EnterprisePayParam {
	@RequireAnno
	private String platCode;
	private double fee;
	public String getPlatCode() {
		return platCode;
	}
	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
}
