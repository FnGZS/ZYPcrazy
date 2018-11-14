package com.crazyBird.controller.vote.model;

import org.apache.lucene.util.StringHelper;

import com.crazyBird.controller.base.AbstractFlagModel;

public class VoteActionCustomModel extends AbstractFlagModel {

	private Long id;
	private String imageUrl;
	private String className;
	private String peopleName;
	private String detail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}


	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
