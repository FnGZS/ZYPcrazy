package com.crazyBird.service.base;

public class ResponseCode {
	public static final String SUCCESS = "0";

	public static final String ERROR = "-1";

	public static boolean isSuccess(String code) {
		return "0".equals(code);
	}
}
