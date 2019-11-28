package com.crazyBird.controller.user.param;

import com.crazyBird.controller.base.AbstractPageFlagModel;
import java.util.List;

public class JYZPageModel extends AbstractPageFlagModel {
	private List<JYZParam> items;

	public List<JYZParam> getItems() {
		return this.items;
	}

	public void setItems(List<JYZParam> items) {
		this.items = items;
	}
}
