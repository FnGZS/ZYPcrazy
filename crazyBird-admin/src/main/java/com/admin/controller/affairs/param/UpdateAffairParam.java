package com.admin.controller.affairs.param;

public class UpdateAffairParam {
	private Long id;
	private String title;
	private String affairsPic;
	private String content;
	private Long typeId;
	private String subordinate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAffairsPic() {
		return affairsPic;
	}
	public void setAffairsPic(String affairsPic) {
		this.affairsPic = affairsPic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getSubordinate() {
		return subordinate;
	}
	public void setSubordinate(String subordinate) {
		this.subordinate = subordinate;
	}
	
	
}
