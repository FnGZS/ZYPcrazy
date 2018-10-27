package com.crazyBird.controller.affairs.model;

public class AffairsItem {
	private Long id;
	private String title;
	private String affairsPic;
	private String content;
	private Long typeId;
	private Long brows;
	private String subordinate;
	private String gmtCreated;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getBrows() {
		return brows;
	}
	public void setBrows(Long brows) {
		this.brows = brows;
	}
	public String getSubordinate() {
		return subordinate;
	}
	public void setSubordinate(String subordinate) {
		this.subordinate = subordinate;
	}
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}


}