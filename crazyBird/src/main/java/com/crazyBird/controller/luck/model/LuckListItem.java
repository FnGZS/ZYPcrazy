package com.crazyBird.controller.luck.model;

import java.util.Date;
import java.util.List;

public class LuckListItem {

	private Long id;
	private Long userId;
	private String luckName;
	private String luckPic;
	private String explain;
	private String lotteryTime;
	private Integer status;
	private Integer mode;
	private String prizeExplain;
	private String gmtCreated;
	private String userName;
	private String headImgUrl;
	
	private List<LuckPrizeItems> items;
	
	public String getPrizeExplain() {
		return prizeExplain;
	}
	public void setPrizeExplain(String prizeExplain) {
		this.prizeExplain = prizeExplain;
	}
	public List<LuckPrizeItems> getItems() {
		return items;
	}
	public void setItems(List<LuckPrizeItems> items) {
		this.items = items;
	}
	
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
	public String getLuckName() {
		return luckName;
	}
	public void setLuckName(String luckName) {
		this.luckName = luckName;
	}
	public String getLuckPic() {
		return luckPic;
	}
	public void setLuckPic(String luckPic) {
		this.luckPic = luckPic;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getLotteryTime() {
		return lotteryTime;
	}
	public void setLotteryTime(String lotteryTime) {
		this.lotteryTime = lotteryTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
}
