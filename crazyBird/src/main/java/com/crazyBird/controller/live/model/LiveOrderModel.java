package com.crazyBird.controller.live.model;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;

public class LiveOrderModel extends AbstractFlagModel{
	private OrderResponseInfo orderInfo;

	public OrderResponseInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderResponseInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	
}
