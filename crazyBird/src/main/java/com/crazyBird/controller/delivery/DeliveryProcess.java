package com.crazyBird.controller.delivery;

import org.springframework.stereotype.Component;

import com.crazyBird.controller.delivery.model.DeliveryModel;
import com.crazyBird.controller.delivery.param.DeliveryPageParam;

@Component
public class DeliveryProcess {

	public DeliveryModel getDeliveryList(DeliveryPageParam param) {
		DeliveryModel model = new DeliveryModel();
		
		return model;
	}

}
