package com.crazyBird.controller.affairs.model;

import com.crazyBird.controller.base.AbstractFlagModel;

public class RecommendModel extends AbstractFlagModel{

	private Long id;
	private String pic;
	private String title;
	private String gmtCreated;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	
}
