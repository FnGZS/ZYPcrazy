package com.crazyBird.service.base;

public class PageQueryDO {
	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int MAX_PAGE_SIZE = 200;

	protected int pageSize;

	protected int pageIndex;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}

	public static int getMaxPageSize() {
		return MAX_PAGE_SIZE;
	}

}

