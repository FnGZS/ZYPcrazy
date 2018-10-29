package com.crazyBird.controller.affairs.model;

import java.util.List;

import com.crazyBird.controller.base.AbstractFlagModel;

public class AffairsTypeModel extends AbstractFlagModel{

	private List<AffairsTypeItem> tags;

	public List<AffairsTypeItem> getTags() {
		return tags;
	}

	public void setTags(List<AffairsTypeItem> tags) {
		this.tags = tags;
	}
	
}
