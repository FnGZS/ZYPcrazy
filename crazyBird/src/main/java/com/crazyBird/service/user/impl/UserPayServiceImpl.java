package com.crazyBird.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.live.LiveDao;
import com.crazyBird.dao.secondary.SecondaryOrderDao;
import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.BillDO;
import com.crazyBird.dao.user.dataobject.BillDTO;
import com.crazyBird.dao.user.dataobject.BillPO;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
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

		return liveDao.checkLiveOrder(orderId);
	}
	@Override
	public int updateLiveOrder(String out_trade_no) {

		return liveDao.updateLiveOrder(out_trade_no);
	}
	@Override
	public boolean insertBill(BillDO billDO) {
	
		return orderDao.insertBill(billDO);
	}
	@Override
	public ResponsePageQueryDO<List<BillDTO>> getBillList(BillPO po) {
		ResponsePageQueryDO<List<BillDTO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(orderDao.getBillCount(po.getUserId()));
	
		if(response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<BillDTO> list = orderDao.getBillList(po);
			response.setDataResult(list);
		}
		else {
			response.setMessage("没有更多了");
		}
		return response;
	}

}
