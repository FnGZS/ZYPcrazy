package com.crazyBird.controller.help.model;

public class HelpItem{
	private String title;
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public HelpItem() {
	}
	public HelpItem(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
