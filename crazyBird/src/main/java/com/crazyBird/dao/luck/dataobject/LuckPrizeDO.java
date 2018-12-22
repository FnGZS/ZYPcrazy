package com.crazyBird.dao.luck.dataobject;

import java.util.Date;

public class LuckPrizeDO {

	private Long id;
	private Long luckPrize;
	private String num;
	private String luckDrawId;
	private Date  gmtCreated;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLuckPrize() {
		return luckPrize;
	}
	public void setLuckPrize(Long luckPrize) {
		this.luckPrize = luckPrize;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLuckDrawId() {
		return luckDrawId;
	}
	public void setLuckDrawId(String luckDrawId) {
		this.luckDrawId = luckDrawId;
	}
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	
}
