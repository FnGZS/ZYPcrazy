package com.crazyBird.dao.secondary.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class SecondaryOrderListPO extends PageQueryDO{

	private Integer orderStatus;
	private Long userId;
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
