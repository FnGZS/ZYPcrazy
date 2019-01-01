package com.crazyBird.controller.base;

import java.util.Map;

public class AbstractSearchPageParam extends AbstractOrderPageParam {
	
	private Map<String, String> searchMap;

	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
}
