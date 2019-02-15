package com.crazyBird.controller.user.param;

public class MessagePutParam {
	private Long userId;
	private String template_id;
	private String page;
	private String form_id;
	private String data;
	private String emphasis_keyword;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getForm_id() {
		return form_id;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

	public String getEmphasis_keyword() {
		return emphasis_keyword;
	}
	public void setEmphasis_keyword(String emphasis_keyword) {
		this.emphasis_keyword = emphasis_keyword;
	}
	
}
