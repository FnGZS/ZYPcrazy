package com.crazyBird.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.live.LiveDao;
import com.crazyBird.dao.secondary.SecondaryOrderDao;
import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;
import com.crazyBird.service.user.UserPayService;

@Component("UserPayService")
public class UserPayServiceImpl implements UserPayService{
	@Autowired UserWxPayOrderDao orderDao;
	@Autowired LiveDao liveDao;
	@Autowired SecondaryOrderDao secondaryOrderDao;
	@Override
	public int insertOrder(UserWxPayOrderDO orderDO) {

		return orderDao.insertOrder(orderDO);
	}
	@Override
	public int checkWxPayOrder(String transaction_id) {

		return orderDao.checkWxPayOrder(transaction_id);
	}
	@Override
	public int updateSecondaryOrder(String out_trade_no) {

		return secondaryOrderDao.updateSecondaryOrder(out_trade_no);
	}
	@Override
	public int insertRefundOrder(UserRefundDO refundDO) {
		return orderDao.insertRefundOrder(refundDO);
	}
	@Override
	public int checkSecondaryOrder(String orderId) {
		
		return secondaryOrderDao.checkSecondaryOrder(orderId);
	}
	@Override
	public int checkLiveOrder(String orderId) {
		// TODO Auto-generated method stub
		return liveDao.checkLiveOrder(orderId);
	}
	@Override
	public int updateLiveOrder(String out_trade_no) {
		// TODO Auto-generated method stub
		return liveDao.updateLiveOrder(out_trade_no);
	}

}
