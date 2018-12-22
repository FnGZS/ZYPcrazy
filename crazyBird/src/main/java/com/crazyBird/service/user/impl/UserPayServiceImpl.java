package com.crazyBird.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;
import com.crazyBird.service.user.UserPayService;

@Component("UserPayService")
public class UserPayServiceImpl implements UserPayService{
	@Autowired UserWxPayOrderDao orderDao;
	@Override
	public int insertOrder(UserWxPayOrderDO orderDO) {
		// TODO Auto-generated method stub
		return orderDao.insertOrder(orderDO);
	}
	@Override
	public int checkWxPayOrder(String transaction_id) {
		// TODO Auto-generated method stub
		return orderDao.checkWxPayOrder(transaction_id);
	}
	@Override
	public int updateSecondaryOrder(String out_trade_no) {
		// TODO Auto-generated method stub
		return orderDao.updateSecondaryOrder(out_trade_no);
	}

}
