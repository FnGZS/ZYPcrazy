package com.crazyBird.service.secondary.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.user.param.EnterprisePayParam;
import com.crazyBird.dao.secondary.SecondaryOrderDao;
import com.crazyBird.dao.secondary.dataobject.CapitalUserDO;
import com.crazyBird.dao.secondary.dataobject.DeleteSecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.GoodsExistDO;
import com.crazyBird.dao.secondary.dataobject.RefundApplyDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCapitalDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCashDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderListPO;
import com.crazyBird.dao.secondary.dataobject.VendorListPO;
import com.crazyBird.dao.user.UserWxPayOrderDao;
import com.crazyBird.dao.user.dataobject.BillDO;
import com.crazyBird.dao.user.dataobject.EnterprisePayDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.SecondaryOrderService;
import com.crazyBird.service.user.dataobject.EnterprisePayInfo;
import com.crazyBird.service.weixin.WeixinAppService;

@Component("SecondaryOrderService")
public class SecondaryOrderServiceImpl implements SecondaryOrderService{

	@Autowired
	private SecondaryOrderDao secondaryOrderDao;
	@Autowired
	private UserWxPayOrderDao userWxPayOrderDao;

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
		
		return secondaryOrderDao.updateSecondaryOrderRefund(out_trade_no);
	}


	@Override
	public int checkSecondaryOrder(String out_trade_no) {
		return secondaryOrderDao.checkSecondaryOrder(out_trade_no);
	}


	@Override
	public int updateSecondaryOrderAccept(SecondaryOrderDO	orderDO ) {
		
		return secondaryOrderDao.updateSecondaryOrderAccept(orderDO);
	}
	@Override
	public SecondaryCapitalDO getSecondaryCapital(Long id) {
		return secondaryOrderDao.getSecondaryCapital(id);
	}


	@Override
	public ResponseDO<SecondaryCashDO> setSecondaryCash(SecondaryCashDO input) {
		ResponseDO<SecondaryCashDO> response = new ResponseDO<>();
		SecondaryCapitalDO capital = secondaryOrderDao.getSecondaryCapital(input.getUserId());
		//检查账户余额与提现金额是否符合
		
		if(capital.getRemainder().compareTo(input.getCash()) < 0) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("提现失败，余额不足");
			return response;
		}
		//存入提现记录
		if(secondaryOrderDao.setSecondaryCash(input)) {
			EnterprisePayParam param = new EnterprisePayParam();
			double fee = input.getCash().doubleValue();
			if(fee<1) {
				response.setCode(ResponseCode.ERROR);
				response.setMessage("提现金额必须大于等于1元");
				return response;
			}
			else {
				param.setFee(Math.round(fee*0.97*100)/100.0);
			}
			param.setPlatCode(input.getPlatCode());
			System.out.println(String.valueOf(param.getFee()));
			String orderId=UUID.randomUUID().toString().replace("-", "");
			//企业付款
			ResponseDO<EnterprisePayDO> result=WeixinAppService.enterprisePay(param, orderId, input.getIp());
			if(result.isSuccess()) {
				//更新用户余额
				CapitalUserDO capitalUserDO = new CapitalUserDO();
				capitalUserDO.setUserId(input.getUserId());
				capitalUserDO.setRemainder(input.getCash());
				secondaryOrderDao.updateCapitalUserByDelete(capitalUserDO);
				//插入账单
				BillDO billDO = new BillDO();
				billDO.setCash(input.getCash());
				billDO.setUserId(input.getUserId());
				billDO.setType(2);
				userWxPayOrderDao.insertBill(billDO);
				
				//存储企业付款记录
				secondaryOrderDao.insertEnterprisePay(result.getDataResult());
			}
			else {
				response.setCode(ResponseCode.ERROR);
				response.setMessage(result.getMessage());
			}
			
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


	@Override
	public ResponsePageQueryDO<List<SecondaryOrderDTO>> getVendorOrderList(VendorListPO po) {
		ResponsePageQueryDO<List<SecondaryOrderDTO>> response = new ResponsePageQueryDO<>();
		if(po.getSellerId() == null) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("未登录");
			return response;
		}
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(secondaryOrderDao.getVendorOrderListCount(po));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<SecondaryOrderDTO> list = secondaryOrderDao.getVendorOrderList(po);
			response.setDataResult(list);
		} else {
			response.setMessage("没有更多了");
		}
		return response;
	}


	@Override
	public SecondaryOrderDTO getOrderDetails(String orderId) {
		return secondaryOrderDao.getOrderDetails(orderId);
	}


	@Override
	public boolean createCapitalUser(Long userId) {

		return secondaryOrderDao.createCapitalUser(userId);
	}


	@Override
	public SecondaryOrderDO getSecondaryOrderDetail(String orderId) {
	
		return secondaryOrderDao.getSecondaryOrderDetail(orderId);
	}


	@Override
	public int updateCapitalUser(CapitalUserDO capitalUserDO) {

		return secondaryOrderDao.updateCapitalUser(capitalUserDO);
	}


	@Override
	public boolean cancelSecondaryOrder(DeleteSecondaryOrderDO deleteOrder) {
		
		return secondaryOrderDao.deleteSecondaryOrder(deleteOrder);
	}


	@Override
	public int updateSecondaryOrderDelivery(SecondaryOrderDO orderDO) {

		return secondaryOrderDao.updateSecondaryOrderDelivery(orderDO);
	}


	@Override
	public int updateSecondaryOrderApplyRefund(SecondaryOrderDO orderDO) {
	
		return secondaryOrderDao.updateSecondaryOrderApplyRefund(orderDO);
	}


	@Override
	public boolean insertRefundApply(RefundApplyDO applyDO) {
		// TODO Auto-generated method stub
		return secondaryOrderDao.insertRefundApply(applyDO);
	}
	
}
