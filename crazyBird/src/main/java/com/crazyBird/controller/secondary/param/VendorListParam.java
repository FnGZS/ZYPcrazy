package com.crazyBird.controller.secondary.param;

import com.crazyBird.controller.base.AbstractPageParam;

public class VendorListParam extends AbstractPageParam{

	private Integer logistics;
	private Integer orderStats;
	

	public Integer getOrderStats() {
		return orderStats;
	}

	public void setOrderStats(Integer orderStats) {
		this.orderStats = orderStats;
	}

	public Integer getLogistics() {
		return logistics;
	}

	public void setLogistics(Integer logistics) {
		this.logistics = logistics;
	}
	
	
	
}
