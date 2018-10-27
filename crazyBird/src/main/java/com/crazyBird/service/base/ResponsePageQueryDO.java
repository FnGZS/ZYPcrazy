package com.crazyBird.service.base;

public class ResponsePageQueryDO<T> extends ResponseDO<T> {
	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final int MAX_PAGE_SIZE = 200;

	private int total = 0;

	private int pageSize;

	private int pageIndex;

	private int totalPage;

	public int getTotal() {
		return this.total;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotal(int total) {
		this.totalPage = ((total + getPageSize() - 1) / getPageSize());
		this.total = total;
	}

	public int getPageSize() {
		if (this.pageSize < 1) {
			this.pageSize = 20;
		}
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}
