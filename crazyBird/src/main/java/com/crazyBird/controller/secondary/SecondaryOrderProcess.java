package com.crazyBird.controller.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.secondary.model.SecondaryOrderModel;
import com.crazyBird.controller.secondary.param.OrderParam;
import com.crazyBird.controller.user.param.UserPayParam;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.secondary.SecondaryOrderService;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;
import com.crazyBird.service.weixin.WeixinAppService;
import com.crazyBird.utils.OrderUtils;
import com.crazyBird.utils.TokenUtils;

@Component
public class SecondaryOrderProcess extends BaseProcess{

	@Autowired
	private SecondaryOrderService secondaryOrderService;
	
	public SecondaryOrderModel getCreateOrder(OrderParam param) {
		SecondaryOrderModel model = new SecondaryOrderModel();
		SecondaryOrderDO order = new SecondaryOrderDO();
		Long userId = (long) 0;
		try {
			userId = TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userId.longValue() == 0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("添加失败");
			return model;
		}
		
		order.setOrderId(OrderUtils.getOrderCode(userId));
		order.setGoodsId(param.getGoodsId());
		order.setUserId(userId);
		order.setPrice(param.getPrice());
		order.setConsignee(param.getConsignee());
		order.setReceivePhone(param.getReceivePhone());
		order.setReceiveAddress(param.getReceiveAddress());
		ResponseDO<String> response = secondaryOrderService.createOrder(order);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		UserPayParam payParam = new UserPayParam();
		payParam.setPlatCode(param.getPlatCode());
		payParam.setPlatUserInfoMap(param.getPlatUserInfoMap());
		payParam.setFee(param.getFee());
		String ip = getReqParam().getIp();
		String orederId = order.getOrderId();
		ResponseDO<OrderResponseInfo> responsePay = WeixinAppService.wxPay(payParam, ip, orederId);
		if (!responsePay.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setOrderInfo(responsePay.getDataResult());
		model.setMessage(response.getMessage());
		return model;
	}

}
