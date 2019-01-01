package com.crazyBird.controller.secondary.model;

import java.math.BigDecimal;

public class SecondaryCapitalItem {
	private Long id;
	private Long userId;
	private BigDecimal remainder;	
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
	public BigDecimal getRemainder() {
		return remainder;
	}
	public void setRemainder(BigDecimal remainder) {
		this.remainder = remainder;
	}

}
