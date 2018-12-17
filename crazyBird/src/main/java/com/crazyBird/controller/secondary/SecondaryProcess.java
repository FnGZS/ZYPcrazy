package com.crazyBird.controller.secondary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodsItem;
import com.crazyBird.controller.secondary.model.SecondaryGoodsModel;
import com.crazyBird.controller.secondary.model.SecondarySlideItem;
import com.crazyBird.controller.secondary.model.SecondarySlideModel;
import com.crazyBird.controller.secondary.model.SecondaryTypeItem;
import com.crazyBird.controller.secondary.model.SecondaryTypeModel;
import com.crazyBird.controller.secondary.param.SearchSecondaryListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsByUserListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsParam;
import com.crazyBird.dao.secondary.dataobject.SearchSecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsByUserPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondarySlideDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryTypeDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.SecondaryService;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.PageUtils;

@Component
public class SecondaryProcess {
	@Autowired
	private SecondaryService secondaryService;
	public SecondarySlideModel getSecondarySlide(){
		SecondarySlideModel model = new SecondarySlideModel();
		List<SecondarySlideItem> items = new ArrayList<>();
		List<SecondarySlideDO> tags = secondaryService.getSecondarySlide();
		for (SecondarySlideDO tag : tags) {
			SecondarySlideItem item = new SecondarySlideItem();
			item.setId(tag.getId());
			item.setSlideImg(tag.getSlideImg());
			items.add(item);
		}
		model.setList(items);
		return model;
	}
	public SecondaryTypeModel getSecondaryType(){
		SecondaryTypeModel model = new SecondaryTypeModel();
		List<SecondaryTypeDO> tags =secondaryService.getSecondaryType();
		model.setList(convertSecondaryType(tags));
		return model;
	}
	public SecondaryTypeModel getSecondaryTradingWay(){
		SecondaryTypeModel model = new SecondaryTypeModel();
		List<SecondaryTypeDO> tags =secondaryService.getSecondaryTradingWay();
		model.setList(convertSecondaryType(tags));
		return model;
	}
	public SecondaryTypeModel getSecondaryWay(){
		SecondaryTypeModel model = new SecondaryTypeModel();
		List<SecondaryTypeDO> tags =secondaryService.getSecondaryWay();
		model.setList(convertSecondaryType(tags));
		return model;
	}

	public SecondaryGoodsModel searchSecondaryGoods(SearchSecondaryListParam param) {
		SecondaryGoodsModel model = new SecondaryGoodsModel();
		if(StringUtils.isBlank(param.getKeyWord())) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("关键字不能为空");
			return model;
		}
		PageUtils.resetPageParam(param);
		SearchSecondaryGoodsPO po = new SearchSecondaryGoodsPO();
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setKeyWord(param.getKeyWord());
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = secondaryService.searchSecondaryGoods(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertSecondaryGoods(response.getDataResult()));
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		
		return model;
		
	}

	public SecondaryGoodsModel getSecondaryGoodsList(SecondaryGoodsListParam param) {
		SecondaryGoodsModel model = new SecondaryGoodsModel();
		/*if(param.getGoodsType()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("类型不能为空");
			return model;
		}*/
		PageUtils.resetPageParam(param);
		SecondaryGoodsPO po = new SecondaryGoodsPO();
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setGoodsType(param.getGoodsType());
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = secondaryService.getSecondaryGoodsList(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertSecondaryGoods(response.getDataResult()));
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		
		return model;
		
	}

	public SecondaryGoodModel getSecondaryGoods( Long id) {
		SecondaryGoodModel model = new SecondaryGoodModel();
		if(id==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("id不能为空");
		}

		List<SecondaryGoodsDTO> list = secondaryService.getSecondaryGoods(id);
		model.setList(convertSecondaryGoods(list));
		return model;	
	}
	

	public SecondaryGoodsModel getSecondaryGoodsByUser(SecondaryGoodsByUserListParam param) {
		SecondaryGoodsModel model = new SecondaryGoodsModel();
		if(param.getId()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("id不能为空");
			return model;
		}
		PageUtils.resetPageParam(param);
		SecondaryGoodsByUserPO po = new SecondaryGoodsByUserPO();
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setId(param.getId());
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = secondaryService.getSecondaryGoodsByUser(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertSecondaryGoods(response.getDataResult()));
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
		
	}
	

	public SimpleFlagModel createSecondaryGoods(SecondaryGoodsParam param) {
		SimpleFlagModel model  = new SimpleFlagModel();
		SecondaryGoodsDO goodsDO = new SecondaryGoodsDO();
		goodsDO.setGoodsContent(param.getGoodsContent());
		goodsDO.setGoodsImag(param.getGoodsImag());
		goodsDO.setGoodsTitle(param.getGoodsTitle());
		goodsDO.setGoodsType(param.getGoodsType());
		goodsDO.setGoodsWay(param.getGoodsWay());
		goodsDO.setPostion(param.getPostion());
		goodsDO.setPrice(param.getPrice());
		goodsDO.setTradingWay(param.getTradingWay());
		goodsDO.setUserId(param.getUserId());
		ResponseDO responseDO = secondaryService.createSecondaryGoods(goodsDO);
		if(responseDO.isSuccess()) {
			model.setMessage("发布成功");
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("发布失败");
		}
		return model;
		
	}
	private  List<SecondaryTypeItem> convertSecondaryType(List<SecondaryTypeDO> tags){
		List<SecondaryTypeItem> items = new ArrayList<>();
		for (SecondaryTypeDO tag : tags) {
			SecondaryTypeItem item = new SecondaryTypeItem();
			item.setId(tag.getId());
			item.setField(tag.getField());
			items.add(item);
		}
		return items;		
	}
	private List<SecondaryGoodsItem> convertSecondaryGoods(List<SecondaryGoodsDTO> tags){
		List<SecondaryGoodsItem> list = new ArrayList<>();
		if(!CollectionUtils.isEmpty(tags)){
		for (SecondaryGoodsDTO tag : tags) {
			
			SecondaryGoodsItem item = new SecondaryGoodsItem();
			item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
			item.setGoodsContent(tag.getGoodsContent());
			item.setGoodsImag(tag.getGoodsImag());
			//item.setGoodsNum(tag.getGoodsNum());
			item.setGoodsTitle(tag.getGoodsTitle());
			item.setGoodsType(tag.getGoodsType());
			item.setGoodsWay(tag.getGoodsWay());
			item.setHeadImgUrl(tag.getHeadImgUrl());
			item.setId(tag.getId());
			item.setPostion(tag.getPostion());
			item.setPrice(String.valueOf(tag.getPrice()));
			item.setTradingWay(tag.getTradingWay());
			item.setUserId(tag.getUserId());
			item.setUserName(tag.getUserName());
			item.setViews(tag.getViews());
			list.add(item);
			
		}
		}
		return list;
	}

	
}
