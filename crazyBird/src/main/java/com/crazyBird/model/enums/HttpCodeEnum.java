package com.crazyBird.model.enums;

/**
 * @Desc httpCode
 * @author
 *
 */
public enum HttpCodeEnum {
	
	SUCCESS("200", "请求成功"),
	ERROR("400", "错误的请求"),
	LOGIN_REQUIRE("401", "请登录"),
	CHECKCODE_ERROR("402", "校验码错误"),
	OPERATE_FORBID("403", "没有权限操作"),
	EXCEPTION("500", "服务器异常");
	
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
