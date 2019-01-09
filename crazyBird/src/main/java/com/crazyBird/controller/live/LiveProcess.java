package com.crazyBird.controller.live;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.live.model.LiveGiftItem;
import com.crazyBird.controller.live.model.LiveGiftModel;
import com.crazyBird.controller.live.model.LiveOrderModel;
import com.crazyBird.controller.live.model.LivePlayUrlDetailModel;
import com.crazyBird.controller.live.model.LivePlayUrlItem;
import com.crazyBird.controller.live.model.LivePlayUrlModel;
import com.crazyBird.controller.live.param.LiveGiftOrderParam;
import com.crazyBird.controller.user.param.UserPayParam;
import com.crazyBird.dao.live.dataobject.LiveDO;
import com.crazyBird.dao.live.dataobject.LiveGiftDO;
import com.crazyBird.dao.live.dataobject.LiveOrderDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.live.LiveService;
import com.crazyBird.service.user.dataobject.OrderResponseInfo;
import com.crazyBird.service.weixin.WeixinAppService;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.OrderUtils;
import com.crazyBird.utils.TokenUtils;

@Component
public class LiveProcess extends BaseProcess {
	// 支付成功后的服务器回调url
	private static final String NOTIFY_URL_GIFT = "https://www.sxscott.com/crazyBird/pay/wxNotify/gift";
	@Autowired
	private LiveService liveService;

	public LivePlayUrlModel getPlayUrl() {
		LivePlayUrlModel model = new LivePlayUrlModel();
		List<LiveDO> tags = liveService.getPlayList();
		List<LivePlayUrlItem> items = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(tags)) {
			for (LiveDO tag : tags) {
				LivePlayUrlItem item = new LivePlayUrlItem();
				item.setId(tag.getId());
				item.setStatus(tag.getStatus());
				item.setPlayUrl(tag.getPlayUrl());
				item.setTitle(tag.getTitle());
				item.setImageUrl(tag.getImageUrl());
				item.setStreamId(tag.getStreamId());
				item.setEndTime(DateUtil.formatDate(tag.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
				items.add(item);
			}
		}

		model.setList(items);
		return model;
	}

	public LivePlayUrlDetailModel getPlayUrlDetail(Integer id) {
		LivePlayUrlDetailModel model = new LivePlayUrlDetailModel();
		LiveDO liveDO = liveService.getPlayUrl(id);
		model.setId(liveDO.getId());
		model.setStatus(liveDO.getStatus());
		model.setPlayUrl(liveDO.getPlayUrl());
		model.setTitle(liveDO.getTitle());
		model.setImageUrl(liveDO.getImageUrl());
		model.setStreamId(liveDO.getStreamId());
		model.setEndTime(DateUtil.formatDate(liveDO.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
		return model;

	}

	public SimpleFlagModel getPlayStatic() {
		SimpleFlagModel model = new SimpleFlagModel();
		liveService.liveStatistical();
		return model;
	}

	public LiveGiftModel getLiveGiftList() {
		LiveGiftModel model = new LiveGiftModel();
		List<LiveGiftDO> tags = liveService.getLiveGiftList();
		List<LiveGiftItem> items = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(tags)) {
			for (LiveGiftDO tag : tags) {
				LiveGiftItem item = new LiveGiftItem();
				item.setGiftImage(tag.getGiftImage());
				item.setGiftName(tag.getGiftName());
				item.setGiftPrice(String.valueOf(tag.getGiftPrice()));
				item.setId(tag.getId());
				items.add(item);
			}
			model.setList(items);
		}
		return model;
	}

	public LiveOrderModel createGiftOrder(LiveGiftOrderParam param) {
		LiveOrderModel model = new LiveOrderModel();
		LiveOrderDO orderDO = new LiveOrderDO();
		Long userId = (long) 0;
		try {
			userId = TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userId.longValue() == 0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("添加失败");
			return model;
		}
		orderDO.setUserId(userId);
		orderDO.setGiftId(param.getGiftId());
		orderDO.setGiftNum(param.getGiftNum());
		orderDO.setLiveId(param.getLiveId());
		orderDO.setOrderState(0);
		orderDO.setTotalPrice(BigDecimal.valueOf(param.getTotalPrice()));
		orderDO.setOrderId(OrderUtils.getOrderCode(userId));
		int flag = liveService.createGiftOrder(orderDO);
		if (flag <= 0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("添加失败");
		}
		UserPayParam payParam = new UserPayParam();
		payParam.setPlatCode(param.getPlatCode());
		payParam.setPlatUserInfoMap(param.getPlatUserInfoMap());
		payParam.setFee(param.getFee());
		String ip = getReqParam().getIp();
		String orederId = orderDO.getOrderId();
		ResponseDO<OrderResponseInfo> responsePay = WeixinAppService.wxPay(payParam, ip, orederId, NOTIFY_URL_GIFT);
		if (!responsePay.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("支付出错");
			return model;
		}
		model.setOrderInfo(responsePay.getDataResult());
		return model;

	}
}
