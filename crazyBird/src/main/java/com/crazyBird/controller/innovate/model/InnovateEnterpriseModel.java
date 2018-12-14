package com.crazyBird.controller.innovate.model;

import com.crazyBird.controller.base.AbstractFlagModel;

public class InnovateEnterpriseModel extends AbstractFlagModel{
	private Integer id;
	private String logo;
	private String name;
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
