package com.crazyBird.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTML.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.crazyBird.controller.base.AbstractPageParam;
import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.user.model.BillItem;
import com.crazyBird.controller.user.model.BillModel;
import com.crazyBird.controller.user.model.UserPayModel;
import com.crazyBird.controller.user.param.BillQueryParam;
import com.crazyBird.controller.user.param.UserAgainPayParam;
import com.crazyBird.controller.user.param.UserPayParam;
import com.crazyBird.controller.user.param.UserRefundParam;
import com.crazyBird.dao.user.dataobject.BillDTO;
import com.crazyBird.dao.user.dataobject.BillPO;
import com.crazyBird.dao.user.dataobject.UserRefundDO;
import com.crazyBird.dao.user.dataobject.UserWxPayOrderDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.SecondaryOrderService;
import com.crazyBird.service.secondary.SecondaryService;
import com.crazyBird.service.user.UserPayService;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;
import com.crazyBird.service.weixin.WeixinAppService;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.PageUtils;
import com.crazyBird.utils.TokenUtils;


@Component
public class UserPayProcess extends BaseProcess {
	// 二手支付成功后的服务器回调url
	private static final String NOTIFY_URL = "https://www.sxscott.com/crazyBird/pay/wxNotify";
	// 二手支付成功后的服务器回调url
	private static final String NOTIFY_URL_GIFT = "https://www.sxscott.com/crazyBird/pay/wxNotify/gitf";

	@Autowired
	private UserPayService payService;
	@Autowired
	private SecondaryOrderService secondaryOrderService;
	@Autowired
	private SecondaryService secondaryService;

	public BillModel getBillList(AbstractPageParam param) {
		BillModel model = new BillModel();
		Long userId = (long) 0;
		PageUtils.resetPageParam(param);
		try {
			userId = TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userId.longValue() == 0 || userId == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("查询失败,请先去绑定学号");
			return model;
		}
		BillPO po = new BillPO();
		po.setUserId(userId);
		po.setPageIndex(param.getPageNo()-1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<BillDTO>> response= payService.getBillList(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());;
			if(CollectionUtil.isNotEmpty(response.getDataResult())) {
			List<BillItem> list = new ArrayList<>();
			for (BillDTO tag : response.getDataResult()) {
				BillItem item = new BillItem();
				if(tag.getType()==2) {
					item.setType(-1);
				}
				if(tag.getType()==3) {
					item.setType(1);
				}
				item.setCash(String.valueOf(tag.getCash()));
				item.setId(tag.getId());
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setMessage(tag.getMessage());
				item.setTitle(tag.getTitle());
				item.setUserId(tag.getUserId());
				list.add(item);
			}
			model.setList(list);
			}
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("账单分页出错");
			
		}
		
		return model;
		
	}
	@Transactional
	public UserPayModel userPay(UserAgainPayParam param) throws IllegalAccessException {
		UserPayModel model = new UserPayModel();
		String ip = getIp();
		Map<String, String> platUserInfoMap = param.getPlatUserInfoMap();
		ResponseDO<OrderResponseInfo> result = null;
		int flag = secondaryOrderService.checkSecondaryGoodsPayStatus(param.getGoodsId());
		if (flag == 0) {
		
			model.setMessage("宝贝已经被人抢走了");
			return model;
		}
		String orderId = param.getOrderId();
		UserPayParam userPay = new UserPayParam();
		userPay.setFee(param.getFee());
		userPay.setPlatCode(param.getPlatCode());
		userPay.setPlatUserInfoMap(param.getPlatUserInfoMap());
		
		if (param.getType() == 1) {
			result = WeixinAppService.wxPay(userPay, ip, orderId, NOTIFY_URL);
		}
		if (param.getType() == 2) {
			result = WeixinAppService.wxPay(userPay, ip, orderId, NOTIFY_URL_GIFT);
		}
		if (!result.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(result.getMessage());
			return model;
		}
		model.setNonceStr(result.getDataResult().getNonceStr());

		model.setPkg(result.getDataResult().getPkg());
		model.setSignType(result.getDataResult().getSignType());
		model.setTimeStamp(result.getDataResult().getTimeStamp());
		model.setPaySign(result.getDataResult().getPaySign());

		/*
		 * else { model.setCode(HttpCodeEnum.ERROR.getCode()); model.setMessage("支付失败");
		 * }
		 */

		return model;
	}
	@Transactional
	public SimpleFlagModel secondaryRefund(UserRefundParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		ResponseDO<UserRefundDO> response = WeixinAppService.refund(param);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		int flag1 = payService.insertRefundOrder(response.getDataResult());
		if(param.getType()==1) {
		int flag2 = secondaryOrderService.updateSecondaryOrderRefund(response.getDataResult().getOut_trade_no());
		if (flag1 <= 0 || flag2 <= 0) {
			model.setMessage("退款成功但更新或插入本地数据时出错");
			}
		
		Long id = secondaryService.getSecondaryGoodsId(response.getDataResult().getOut_trade_no());
		secondaryService.updateSecondaryGoodsOnline(id);
		}
		return model;
	}

	public boolean wxNotify(Map<String, Object> resultMap) {
		int secondaryFlag = payService.checkSecondaryOrder((String) resultMap.get("out_trade_no"));
		if (secondaryFlag == 0) {
			payService.updateSecondaryOrder((String) resultMap.get("out_trade_no"));
		}
		int count = payService.checkWxPayOrder((String) resultMap.get("transaction_id"));
		if (count > 0) {
			return true;
		}
		UserWxPayOrderDO orderDO = new UserWxPayOrderDO();
		orderDO.setAppid((String) resultMap.get("appid"));
		orderDO.setMch_id((String) resultMap.get("mch_id"));
		orderDO.setNonce_str((String) resultMap.get("nonce_str"));
		orderDO.setSign((String) resultMap.get("sign"));
		orderDO.setResult_code((String) resultMap.get("result_code"));
		orderDO.setOpenid((String) resultMap.get("openid"));
		orderDO.setTrade_type((String) resultMap.get("trade_type"));
		orderDO.setBank_type((String) resultMap.get("bank_type"));
		orderDO.setTotal_fee(Integer.valueOf((String) resultMap.get("total_fee")));
		orderDO.setCash_fee(Integer.valueOf((String) resultMap.get("cash_fee")));
		orderDO.setTransaction_id((String) resultMap.get("transaction_id"));
		orderDO.setOut_trade_no((String) resultMap.get("out_trade_no"));
		orderDO.setGmt_created(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		orderDO.setGmt_modified(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		int flag = payService.insertOrder(orderDO);
	
		if (flag <= 0) {
			return false;
		}
		Long id = secondaryService.getSecondaryGoodsId((String) resultMap.get("out_trade_no"));
		secondaryService.updateSecondaryGoodsPay(id);
		return true;

	}

	public boolean wxNotifyGift(Map<String, Object> resultMap) {
		int liveFlag = payService.checkLiveOrder((String) resultMap.get("out_trade_no"));
		if (liveFlag == 0) {
			payService.updateLiveOrder((String) resultMap.get("out_trade_no"));
		}
		int count = payService.checkWxPayOrder((String) resultMap.get("transaction_id"));
		if (count > 0) {
			return true;
		}
		UserWxPayOrderDO orderDO = new UserWxPayOrderDO();
		orderDO.setAppid((String) resultMap.get("appid"));
		orderDO.setMch_id((String) resultMap.get("mch_id"));
		orderDO.setNonce_str((String) resultMap.get("nonce_str"));
		orderDO.setSign((String) resultMap.get("sign"));
		orderDO.setResult_code((String) resultMap.get("result_code"));
		orderDO.setOpenid((String) resultMap.get("openid"));
		orderDO.setTrade_type((String) resultMap.get("trade_type"));
		orderDO.setBank_type((String) resultMap.get("bank_type"));
		orderDO.setTotal_fee(Integer.valueOf((String) resultMap.get("total_fee")));
		orderDO.setCash_fee(Integer.valueOf((String) resultMap.get("cash_fee")));
		orderDO.setTransaction_id((String) resultMap.get("transaction_id"));
		orderDO.setOut_trade_no((String) resultMap.get("out_trade_no"));
		orderDO.setGmt_created(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		orderDO.setGmt_modified(DateUtil.getStringToDate((String) resultMap.get("time_end"), DateUtil.dtLong));
		int flag = payService.insertOrder(orderDO);
		if (flag <= 0) {
			return false;
		}
		return true;

	}
	
	
}
