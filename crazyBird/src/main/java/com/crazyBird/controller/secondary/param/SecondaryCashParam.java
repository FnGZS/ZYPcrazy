package com.crazyBird.controller.secondary.param;

import java.math.BigDecimal;

public class SecondaryCashParam {
	private Long userId;
	private String account;
	private BigDecimal cash;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	
}
