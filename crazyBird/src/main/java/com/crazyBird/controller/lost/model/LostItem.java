package com.crazyBird.controller.lost.model;

import java.sql.Date;

public class LostItem {
	private Integer id;
	private String title;
	private Integer publisher;
	private String foundPic;
	private String content;
	private Integer typeId;
	private Integer isExamine;
	private Integer brow;
	private Integer isSolve;
	private String gmtCreated;
	
	private Integer latitude;
	private Integer longitude;
	private Integer messageId;

	
	
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public Integer getLongitude() {
		return longitude;
	}
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getPublisher() {
		return publisher;
	}
	public void setPublisher(Integer publisher) {
		this.publisher = publisher;
	}
	public String getFoundPic() {
		return foundPic;
	}
	public void setFoundPic(String foundPic) {
		this.foundPic = foundPic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getIsExamine() {
		return isExamine;
	}
	public void setIsExamine(Integer isExamine) {
		this.isExamine = isExamine;
	}
	public Integer getBrow() {
		return brow;
	}
	public void setBrow(Integer brow) {
		this.brow = brow;
	}
	public Integer getIsSolve() {
		return isSolve;
	}
	public void setIsSolve(Integer isSolve) {
		this.isSolve = isSolve;
	}

	
}
