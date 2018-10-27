package com.crazyBird.service.base;

public class ResponseDO<T> {
	private String code = "0";

	private String message;

	private T dataResult;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getDataResult() {
		return (T) this.dataResult;
	}

	public void setDataResult(T dataResult) {
		this.dataResult = dataResult;
	}

	public void setResult(String code, String message) {
		this.message = message;
		this.code = code;
	}

	public void setResult(String code, String message, T dataResult) {
		this.message = message;
		this.code = code;
		this.dataResult = dataResult;
	}

	public boolean isSuccess() {
		return ResponseCode.isSuccess(this.code);
	}
}
