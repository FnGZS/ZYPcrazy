package com.crazyBird.service.secondary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.secondary.SecondaryOrderDao;
import com.crazyBird.dao.secondary.dataobject.DeleteSecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.GoodsExistDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCapitalDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCashDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderListPO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
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


	@Override
	public ResponsePageQueryDO<List<SecondaryOrderDTO>> getOrderList(SecondaryOrderListPO po) {
		ResponsePageQueryDO<List<SecondaryOrderDTO>> response = new ResponsePageQueryDO<>();
		if(po.getUserId() == null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("未登录");
			return response;
		}
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryOrderDao.getOrderListCount(po));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<SecondaryOrderDTO> list = secondaryOrderDao.getOrderList(po);
			response.setDataResult(list);
		} else {
			response.setMessage("没有更多了");
		}
		return response;
	}


	@Override
	public ResponseDO<String> deleteSecondaryOrder(DeleteSecondaryOrderDO deleteOrder) {
		ResponseDO<String> response = new ResponseDO<String>();
		if(deleteOrder.getUserId() == null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("未登录");
			return response;
		}
		if(secondaryOrderDao.updateOrder(deleteOrder)) {
			response.setMessage("删除成功");
			return response;
		}
		response.setCode(ResponseCode.ERROR);
		response.setMessage("删除失败");
		return response;
	}


	@Override
	public int updateSecondaryOrderRefund(String out_trade_no) {
		
		return updateSecondaryOrderRefund(out_trade_no);
	}


	@Override
	public int checkSecondaryOrder(String out_trade_no) {
		return checkSecondaryOrder(out_trade_no);
	}


	@Override
	public int updateSecondaryOrderAccept(String orderId) {
		
		return updateSecondaryOrderAccept(orderId);
	}
	@Override
	public SecondaryCapitalDO getSecondaryCapital(Long id) {
		return secondaryOrderDao.getSecondaryCapital(id);
	}


	@Override
	public ResponseDO<SecondaryCashDO> setSecondaryCash(SecondaryCashDO input) {
		ResponseDO<SecondaryCashDO> response = new ResponseDO<>();
		SecondaryCapitalDO capital = secondaryOrderDao.getSecondaryCapital(input.getUserId());
		if(capital.getRemainder().compareTo(input.getCash()) < 0) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("提现失败，余额不足");
			return response;
		}
		if(secondaryOrderDao.setSecondaryCash(input)) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("提现已申请，请耐心等待");
		}
		else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("提现失败");
		}
		return response;
	}


	@Override
	public int checkSecondaryGoodsPayStatus(Long id) {

		return secondaryOrderDao.checkSecondaryGoodsPayStatus(id);
	}
}
