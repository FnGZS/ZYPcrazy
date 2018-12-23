package com.crazyBird.controller.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderDeleteModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderListModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderModel;
import com.crazyBird.controller.secondary.param.OrderListParam;
import com.crazyBird.controller.secondary.param.OrderParam;

/**
 * 二手市场
 * @author zjw
 *
 */
@Controller
@RequestMapping("/secondary/order")
public class SecondaryOrderController {

	@Autowired
	private SecondaryOrderProcess secondaryOrderProcess;
	
	/**
	 * 创造订单
	 * */
	@ResponseBody
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public SecondaryOrderModel createSecondaryOrder(@RequestBody OrderParam param){
		return secondaryOrderProcess.getCreateOrder(param);
	}
	
	/**
	 * 订单列表
	 * **/
	@ResponseBody
	@RequestMapping(value="/orderList",method=RequestMethod.GET)
	public SecondaryOrderListModel secondaryOrderList(OrderListParam param){
		return secondaryOrderProcess.getOrderList(param);
	}
	
	/**
	 * 删除订单
	 * **/
	@ResponseBody
	@RequestMapping(value="/orderDelete",method=RequestMethod.GET)
	public SecondaryOrderDeleteModel deleteSecondaryOrder(Long id){
		return secondaryOrderProcess.deleteSecondaryOrder(id);
	}
	/**
	 * 确认收货
	 */
	@ResponseBody
	@RequestMapping(value="/orderAccept",method=RequestMethod.PUT)
	public SimpleFlagModel updateSecondaryOrderAccept(String orderId) {
		return secondaryOrderProcess.updateSecondaryOrderAccept(orderId);	
	}
	
}
