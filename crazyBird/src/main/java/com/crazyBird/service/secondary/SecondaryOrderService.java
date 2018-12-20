package com.crazyBird.service.secondary;

import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.service.base.ResponseDO;

public interface SecondaryOrderService {

	ResponseDO<String> createOrder(SecondaryOrderDO order);

}
