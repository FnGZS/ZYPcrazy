package com.crazyBird.dao.secondary.dataobject;

import java.math.BigDecimal;

public class SecondaryCashDO {
	private Long id;
	private Long userId;
	private String account;
	private BigDecimal cash;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
