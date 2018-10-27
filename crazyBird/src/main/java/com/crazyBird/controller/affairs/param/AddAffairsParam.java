package com.crazyBird.controller.affairs.param;

public class AddAffairsParam {
	private String title;
	private String affairsPic;
	private String content;
	private Long typeId;
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
	
	
}
