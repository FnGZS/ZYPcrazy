package com.crazyBird.controller.secondary.model;

import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;

public class SecondaryOrderModel  extends AbstractFlagModel{

	private OrderResponseInfo orderInfo;

	public OrderResponseInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderResponseInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	
}
