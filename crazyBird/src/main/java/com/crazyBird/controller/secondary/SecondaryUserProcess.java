package com.crazyBird.controller.secondary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.secondary.model.CollectionQuantityModel;
import com.crazyBird.controller.secondary.model.CollectionSecondaryListModel;
import com.crazyBird.controller.secondary.model.CollectionSecondaryModel;
import com.crazyBird.controller.secondary.model.IsCollectionModel;
import com.crazyBird.controller.secondary.model.PurchaseSecondaryListModel;
import com.crazyBird.controller.secondary.model.UserSecondaryItem;
import com.crazyBird.controller.secondary.model.SellSecondaryListModel;
import com.crazyBird.controller.secondary.param.CollectionListParam;
import com.crazyBird.controller.secondary.param.CollectionParam;
import com.crazyBird.controller.secondary.param.CollectionSecondaryParam;
import com.crazyBird.controller.secondary.param.PurchaseSecondaryParam;
import com.crazyBird.controller.secondary.param.SellSecondaryParam;
import com.crazyBird.dao.secondary.dataobject.CollectionDO;
import com.crazyBird.dao.secondary.dataobject.CollectionSecondaryListPO;
import com.crazyBird.dao.secondary.dataobject.PurchasePO;
import com.crazyBird.dao.secondary.dataobject.UserSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.UserSecondaryService;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.PageUtils;
import com.crazyBird.utils.TokenUtils;

@Component
public class SecondaryUserProcess extends BaseProcess{

	@Autowired
	private UserSecondaryService userSecondaryService;
	
	
	public SellSecondaryListModel getSellList(SellSecondaryParam param) {
		SellSecondaryListModel model = new SellSecondaryListModel();
		PageUtils.resetPageParam(param);
		SellSecondaryPO po = new SellSecondaryPO();
		po.setStatus(param.getStatus());
		try {
			po.setSellId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<UserSecondaryDTO>> response = userSecondaryService.getSellList(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setTags(convertUserSecondary(response.getDataResult()));
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}

	public PurchaseSecondaryListModel getPurchase(PurchaseSecondaryParam param) {
		PurchaseSecondaryListModel model = new PurchaseSecondaryListModel();
		PurchasePO po = new PurchasePO();
		try {
			po.setUserId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<UserSecondaryDTO>> response = userSecondaryService.getPurchaseSecondary(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertUserSecondary(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	private List<UserSecondaryItem> convertUserSecondary (List<UserSecondaryDTO> tags){
		List<UserSecondaryItem> lists = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(tags)) {
			for(UserSecondaryDTO tag:tags) {
				if(tag != null) {
					UserSecondaryItem item = new UserSecondaryItem();
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
					item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
					lists.add(item);
				}
			}
		}
		return lists;
	}

	public CollectionSecondaryModel collection(CollectionSecondaryParam param) {
		CollectionSecondaryModel model = new CollectionSecondaryModel();
		CollectionDO collection= new CollectionDO();
		try {
			collection.setUserId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collection.setGoodsId(param.getGoodsId());
		ResponseDO<String> responseDO = userSecondaryService.collection(collection);
		if (responseDO.isSuccess()) {
			model.setMessage(responseDO.getMessage());
			return model;
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		model.setMessage("想要失败");
		return model;
	}

	public CollectionSecondaryListModel collectionList(CollectionListParam param) {
		CollectionSecondaryListModel model = new CollectionSecondaryListModel();
		CollectionSecondaryListPO po = new CollectionSecondaryListPO();
		try {
			po.setUserId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		po.setStatus(param.getStatus());
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<UserSecondaryDTO>> response = userSecondaryService.getCollectionSecondaryList(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertUserSecondary(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	public IsCollectionModel isCollection(CollectionParam param) {
		IsCollectionModel model = new IsCollectionModel();
		CollectionDO collection= new CollectionDO();
		try {
			collection.setUserId(TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		collection.setGoodsId(param.getGoodsId());
		ResponseDO<String> responseDO = userSecondaryService.isCollection(collection);
		if (responseDO.isSuccess()) {
			model.setMessage(responseDO.getMessage());
			return model;
		}
		model.setMessage("未想要");
		return model;
	}

	public CollectionQuantityModel collectionNum(Long goodsId) {
		CollectionQuantityModel model = new CollectionQuantityModel();
		Integer quantity = userSecondaryService.getCollectionNum(goodsId);
		model.setQuantity(quantity);
		return model;
	}
	

}
