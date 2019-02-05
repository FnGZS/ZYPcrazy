package com.crazyBird.dao.secondary.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class VendorListPO extends PageQueryDO{

	
	private Integer orderStatus;
	private Long sellerId;
	

	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
}
