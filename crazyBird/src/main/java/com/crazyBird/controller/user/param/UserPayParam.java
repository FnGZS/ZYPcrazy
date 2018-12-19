package com.crazyBird.controller.user.param;

import java.util.Map;

import com.crazyBird.validate.annotation.RequireAnno;
import com.ibm.icu.math.BigDecimal;

public class UserPayParam {
	@RequireAnno
	private String platCode;
	private Map<String, String> platUserInfoMap;
	private String fee;

	public String getPlatCode() {
		return this.platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}

	public Map<String, String> getPlatUserInfoMap() {
		return this.platUserInfoMap;
	}

	

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public void setPlatUserInfoMap(Map<String, String> platUserInfoMap) {
		this.platUserInfoMap = platUserInfoMap;
	}
}
