package com.crazyBird.controller.user.param;

import com.crazyBird.validate.annotation.RequireAnno;
import java.util.Map;

public class LoginParam {
	@RequireAnno
	private String platCode;
	private Map<String, String> platUserInfoMap;

	public String getPlatCode() {
		return this.platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}

	public Map<String, String> getPlatUserInfoMap() {
		return this.platUserInfoMap;
	}

	public void setPlatUserInfoMap(Map<String, String> platUserInfoMap) {
		this.platUserInfoMap = platUserInfoMap;
	}
}
