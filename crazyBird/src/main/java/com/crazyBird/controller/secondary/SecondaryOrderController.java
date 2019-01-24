package com.crazyBird.controller.secondary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.secondary.model.OrderDetailsModel;
import com.crazyBird.controller.secondary.model.SecondaryCapitalModel;
import com.crazyBird.controller.secondary.model.SecondaryCashModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderDeleteModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderListModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderModel;
import com.crazyBird.controller.secondary.param.OrderListParam;
import com.crazyBird.controller.secondary.param.OrderParam;
import com.crazyBird.controller.secondary.param.RefundApplyParam;
import com.crazyBird.controller.secondary.param.SecondaryCashParam;
import com.crazyBird.controller.secondary.param.SecondaryOrderParam;
import com.crazyBird.controller.secondary.param.VendorListParam;

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
	 * 订单列表（买家的）
	 * **/
	@ResponseBody
	@RequestMapping(value="/orderList",method=RequestMethod.GET)
	public SecondaryOrderListModel secondaryOrderList(OrderListParam param){
		return secondaryOrderProcess.getOrderList(param);
	}
	
	/**
	 * 订单列表（卖家的）
	 * **/
	@ResponseBody
	@RequestMapping(value="/vendorOrderList",method=RequestMethod.GET)
	public SecondaryOrderListModel vendorOrderList(VendorListParam param){
		return secondaryOrderProcess.vendorList(param);
	}
	
	/**
	 * 订单详情
	 * **/
	@ResponseBody
	@RequestMapping(value="/orderDetails",method=RequestMethod.GET)
	public OrderDetailsModel orderDetails(String orderId){
		return secondaryOrderProcess.orderDetails(orderId);
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
	 * 取消订单
	 */
	@ResponseBody
	@RequestMapping(value="/orderCancel",method=RequestMethod.DELETE)
	public SimpleFlagModel cancelSecondaryOrder(Long id){
		return secondaryOrderProcess.cancelSecondaryOrder(id);
	}
	/**
	 * 确认收货
	 */
	@ResponseBody
	@RequestMapping(value="/orderAccept",method=RequestMethod.PUT)
	public SimpleFlagModel updateSecondaryOrderAccept( @RequestBody SecondaryOrderParam param) {
		return secondaryOrderProcess.updateSecondaryOrderAccept(param);	
	}
	
	/**
	 * 确认发货
	 */
	@ResponseBody
	@RequestMapping(value="/orderDelivery",method=RequestMethod.PUT)
	public SimpleFlagModel updateSecondaryOrderDelivery(@RequestBody SecondaryOrderParam param) {
		return secondaryOrderProcess.updateSecondaryOrderDelivery(param);	
	}
	/**
	 * 申请退款
	 */
	@ResponseBody
	@RequestMapping(value="/orderApply",method=RequestMethod.PUT)
	public SimpleFlagModel updateSecondaryOrderApplyRefund(@RequestBody RefundApplyParam param) {
		return secondaryOrderProcess.updateSecondaryOrderApplyRefund(param);
	}
	
	/**
	 * 获取资金信息
	 * **/
	@ResponseBody
	@RequestMapping(value = "/getSecondaryCapital",method = RequestMethod.GET)
	public SecondaryCapitalModel getSecondaryCapital() {
		return secondaryOrderProcess.getSecondaryCapital();
	}
	
	/**
	 * 提现
	 * **/
	@ResponseBody
	@RequestMapping(value = "/cash",method = RequestMethod.POST)
	public SecondaryCashModel setSecondaryCash(@RequestBody SecondaryCashParam param) {
		return secondaryOrderProcess.setSecondaryCash(param);
	}
	
	
	
	
}
