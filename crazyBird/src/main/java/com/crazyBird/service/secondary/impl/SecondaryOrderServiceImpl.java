package com.crazyBird.service.secondary.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.secondary.SecondaryOrderDao;
import com.crazyBird.dao.secondary.dataobject.GoodsExistDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.secondary.SecondaryOrderService;

@Component("SecondaryOrderService")
public class SecondaryOrderServiceImpl implements SecondaryOrderService{

	@Autowired
	private SecondaryOrderDao secondaryOrderDao;
	

	@Override
	public ResponseDO<String> createOrder(SecondaryOrderDO order) {
		ResponseDO<String> response = new ResponseDO<String>();
		GoodsExistDO goods = secondaryOrderDao.getSecondaryGoods(order.getGoodsId());
		if(goods == null || goods.getStatus() == 3) {
			response.setMessage("商品已下架");
			response.setCode(ResponseCode.ERROR);
			return response;
		}
		order.setSeller(goods.getUserName());
		order.setSellerId(goods.getUserId());
		if(secondaryOrderDao.createOrder(order)) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("下单成功");
			return response;
		}
		response.setMessage("下单失败");
		response.setCode(ResponseCode.ERROR);
		return response;
	}
}
