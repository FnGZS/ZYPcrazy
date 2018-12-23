package com.crazyBird.controller.luck.model;

import java.util.Date;

public class LuckPrizeItems {

	private Long id;
	private String luckPrize;
	private String num;
	private String luckPic;
	private Long luckDrawId;
	private String sponsor;
	private Date  gmtCreated;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLuckPrize() {
		return luckPrize;
	}
	public void setLuckPrize(String luckPrize) {
		this.luckPrize = luckPrize;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Long getLuckDrawId() {
		return luckDrawId;
	}
	public void setLuckDrawId(Long luckDrawId) {
		this.luckDrawId = luckDrawId;
	}
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public String getLuckPic() {
		return luckPic;
	}
	public void setLuckPic(String luckPic) {
		this.luckPic = luckPic;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	
}
