 package com.crazyBird.controller.vote.model;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.lowagie.text.Image;

public class VoteActionSlideItem {
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	private String actionId;
	private String picUrl;
}
