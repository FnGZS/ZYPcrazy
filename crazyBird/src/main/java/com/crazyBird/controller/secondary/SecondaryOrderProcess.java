package com.crazyBird.controller.secondary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.secondary.model.OrderDetailsModel;
import com.crazyBird.controller.secondary.model.SecondaryCapitalItem;
import com.crazyBird.controller.secondary.model.SecondaryCapitalModel;
import com.crazyBird.controller.secondary.model.SecondaryCashModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderDeleteModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderItem;
import com.crazyBird.controller.secondary.model.SecondaryOrderListModel;
import com.crazyBird.controller.secondary.model.SecondaryOrderModel;
import com.crazyBird.controller.secondary.param.OrderListParam;
import com.crazyBird.controller.secondary.param.OrderParam;
import com.crazyBird.controller.secondary.param.SecondaryCashParam;
import com.crazyBird.controller.secondary.param.VendorListParam;
import com.crazyBird.controller.user.param.UserPayParam;
import com.crazyBird.dao.secondary.dataobject.DeleteSecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCapitalDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCashDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryOrderListPO;
import com.crazyBird.dao.secondary.dataobject.VendorListPO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.SecondaryOrderService;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;
import com.crazyBird.service.weixin.WeixinAppService;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.OrderUtils;
import com.crazyBird.utils.PageUtils;
import com.crazyBird.utils.TokenUtils;

@Component
public class SecondaryOrderProcess extends BaseProcess{

	// 支付成功后的服务器回调url
	private static final String NOTIFY_URL = "https://www.sxscott.com/crazyBird/pay/wxNotify";
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
		int flag = secondaryOrderService.checkSecondaryGoodsPayStatus(param.getGoodsId());
		if(flag!=0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("宝贝已经被人抢走了");
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
		ResponseDO<OrderResponseInfo> responsePay = WeixinAppService.wxPay(payParam, ip, orederId,NOTIFY_URL);
		if (!responsePay.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setOrderInfo(responsePay.getDataResult());
		model.setMessage(response.getMessage());
		return model;
	}

	public SecondaryOrderListModel getOrderList(OrderListParam param) {
		SecondaryOrderListModel model = new SecondaryOrderListModel();
		PageUtils.resetPageParam(param);
		SecondaryOrderListPO po = new SecondaryOrderListPO();
		try {
			po.setUserId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		po.setOrderStatus(param.getOrderStatus());
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<SecondaryOrderDTO>> response = secondaryOrderService.getOrderList(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setTags(convertSecondaryOrder(response.getDataResult()));
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}

	private List<SecondaryOrderItem> convertSecondaryOrder (List<SecondaryOrderDTO> tags){
		List<SecondaryOrderItem> lists = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(tags)) {
			for(SecondaryOrderDTO tag:tags) {
				if(tag != null) {
					SecondaryOrderItem item = new SecondaryOrderItem();
					item.setId(tag.getId());
					item.setUserId(tag.getUserId());
					item.setViews(tag.getViews());
					item.setGoodsNum(tag.getGoodsNum());
					item.setGoodsTitle(tag.getGoodsTitle());
					item.setGoodsContent(tag.getGoodsContent());
					item.setGoodsImg(tag.getGoodsImg());
					item.setGoodsType(tag.getGoodsType());
					item.setPostion(tag.getPostion());
					item.setGoodsWay(tag.getGoodsWay());
					item.setTradingWay(tag.getTradingWay());
					item.setPrice(String.valueOf(tag.getPrice()));
					item.setOldPrice(String.valueOf(tag.getOldPrice()));
					item.setUserName(tag.getUserName());
					item.setHeadImgUrl(tag.getHeadImgUrl());
					item.setOrderId(tag.getOrderId());
					item.setGoodsId(tag.getGoodsId());
					item.setSellerId(tag.getSellerId());
					item.setSeller(tag.getSeller());
					item.setConsignee(tag.getConsignee());
					item.setLogistics(tag.getLogistics());
					item.setOrderState(tag.getOrderState());
					item.setReceivePhone(tag.getReceivePhone());
					item.setReceiveAddress(tag.getReceiveAddress());
					item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
					lists.add(item);
				}
			}
		}
		return lists;
	}

	public SecondaryOrderDeleteModel deleteSecondaryOrder(Long id) {
		SecondaryOrderDeleteModel model = new SecondaryOrderDeleteModel();
		DeleteSecondaryOrderDO deleteOrder = new DeleteSecondaryOrderDO();
		deleteOrder.setId(id);
		try {
			deleteOrder.setUserId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseDO<String> response = secondaryOrderService.deleteSecondaryOrder(deleteOrder);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setMessage(response.getMessage());
		return model;
	}
	
	public SimpleFlagModel 	updateSecondaryOrderAccept(String orderId) {
		 SimpleFlagModel model = new SimpleFlagModel();
		 if(orderId==null) {
				model.setCode(HttpCodeEnum.ERROR.getCode());
				model.setMessage("参数为空");
				return model;
		 }
		 int count = secondaryOrderService.updateSecondaryOrderAccept(orderId);
		 if(count>0) {
				model.setMessage("更新成功");
		 }
		return model;
	}
	
	public SecondaryCapitalModel getSecondaryCapital(Long id) {
		SecondaryCapitalModel model = new SecondaryCapitalModel();
		SecondaryCapitalDO DO = secondaryOrderService.getSecondaryCapital(id);
		if(DO != null) {
			SecondaryCapitalItem item = new SecondaryCapitalItem();
			item.setRemainder(DO.getRemainder());
			item.setId(DO.getId());
			item.setUserId(DO.getUserId());
			model.setList(item);
			return model;
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		return model;
	}

	public SecondaryCashModel setSecondaryCash(SecondaryCashParam param) {
		SecondaryCashModel model = new SecondaryCashModel();
		SecondaryCashDO input = new SecondaryCashDO();
		input.setUserId(param.getUserId());
		input.setAccount(param.getAccount());
		input.setCash(param.getCash());
		ResponseDO<SecondaryCashDO> response = secondaryOrderService.setSecondaryCash(input);
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		else {
			model.setCode(HttpCodeEnum.SUCCESS.getCode());
			model.setMessage(response.getMessage());
		}	
		return model;
	}

	public SecondaryOrderListModel vendorList(VendorListParam param) {
		SecondaryOrderListModel model = new SecondaryOrderListModel();
		PageUtils.resetPageParam(param);
		VendorListPO po = new VendorListPO();
		try {
			po.setSellerId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		po.setLogistics(param.getLogistics());
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<SecondaryOrderDTO>> response = secondaryOrderService.getVendorOrderList(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setTags(convertSecondaryOrder(response.getDataResult()));
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}

	public OrderDetailsModel orderDetails(String orderId) {
		OrderDetailsModel model = new OrderDetailsModel();
		SecondaryOrderDTO orderDetails = secondaryOrderService.getOrderDetails(orderId);
		if(orderDetails != null) {
			model.setId(orderDetails.getId());
			model.setUserId(orderDetails.getUserId());
			model.setViews(orderDetails.getViews());
			model.setGoodsNum(orderDetails.getGoodsNum());
			model.setGoodsTitle(orderDetails.getGoodsTitle());
			model.setGoodsContent(orderDetails.getGoodsContent());
			model.setGoodsImg(orderDetails.getGoodsImg());
			model.setGoodsType(orderDetails.getGoodsType());
			model.setPostion(orderDetails.getPostion());
			model.setGoodsWay(orderDetails.getGoodsWay());
			model.setTradingWay(orderDetails.getTradingWay());
			model.setPrice(String.valueOf(orderDetails.getPrice()));
			model.setOldPrice(String.valueOf(orderDetails.getOldPrice()));
			model.setUserName(orderDetails.getUserName());
			model.setHeadImgUrl(orderDetails.getHeadImgUrl());
			model.setOrderId(orderDetails.getOrderId());
			model.setGoodsId(orderDetails.getGoodsId());
			model.setSellerId(orderDetails.getSellerId());
			model.setSeller(orderDetails.getSeller());
			model.setConsignee(orderDetails.getConsignee());
			model.setLogistics(orderDetails.getLogistics());
			model.setOrderState(orderDetails.getOrderState());
			model.setReceivePhone(orderDetails.getReceivePhone());
			model.setReceiveAddress(orderDetails.getReceiveAddress());
			model.setGmtCreated(DateUtil.formatDate(orderDetails.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
			return model;
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		return model;
	}
	
}
