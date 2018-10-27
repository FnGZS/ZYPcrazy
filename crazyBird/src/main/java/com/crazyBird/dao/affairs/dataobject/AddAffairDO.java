package com.crazyBird.dao.affairs.dataobject;

public class AddAffairDO {

	private String title;
	private String affairsPic;
	private byte[] content;
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
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	
}
