package com.crazyBird.model.enums;

/**
 * @Desc httpCode
 *
 */
public enum HttpCodeEnum {
	
	SUCCESS("200", "请求成功"),
	ERROR("400", "错误的请�??"),
	LOGIN_REQUIRE("401", "请登�??"),
	CHECKCODE_ERROR("402", "校验码错�??"),
	OPERATE_FORBID("403", "没有权限操作"),
	EXCEPTION("500", "服务器异�??");
	
	private HttpCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;

	private String desc;

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
