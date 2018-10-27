package com.crazyBird.service.base;

/**
 * @Type PageQueryDO
 * @Desc
 * @author luogm
 * @date 2014年2月11日
 * @Version V1.0
 */
public class PageQueryDO {

    /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 每页最多显示的记录数
     */
    public static final int MAX_PAGE_SIZE = 200;

    /**
     * 一页大小
     */
    protected int pageSize;

    /**
     * 当前页数，从 0开始，0代表第一页
     */
    protected int pageIndex;

    /**
     * 获取一页的记录数
     * 
     * @return int
     */
    public int getPageSize() {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    /**
     * 设置一页的记录数
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置当前页面
     * 
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }
    
    /**
     * 获取分页起始位置
     */
    public int getStartPos() {
    	return getPageIndex() * getPageSize();
    }
}
