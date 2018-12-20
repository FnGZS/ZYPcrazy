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

}
