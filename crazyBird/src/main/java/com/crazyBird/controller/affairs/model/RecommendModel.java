package com.crazyBird.controller.affairs.model;

import com.crazyBird.controller.base.AbstractFlagModel;

public class RecommendModel extends AbstractFlagModel{

	private Long id;
	private String pic;
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
	
}
