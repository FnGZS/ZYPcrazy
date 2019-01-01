package com.crazyBird.dao.secondary.dataobject;

import java.math.BigDecimal;

public class SecondaryCapitalDO {
	private Long id;
	private Long userId;
	private BigDecimal remainder;
	private String gmtCreated;
	private String gmtModified;
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
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	
}
