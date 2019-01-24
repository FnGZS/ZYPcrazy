package com.crazyBird.controller.secondary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.secondary.model.SecondaryCommetsMessageItem;
import com.crazyBird.controller.secondary.model.SecondaryCommetsMessageModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodsCommentItem;
import com.crazyBird.controller.secondary.model.SecondaryGoodsCommentsModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodsItem;
import com.crazyBird.controller.secondary.model.SecondaryGoodsModel;
import com.crazyBird.controller.secondary.model.SecondaryGoodsReplyItem;
import com.crazyBird.controller.secondary.model.SecondaryMessageDetailModel;
import com.crazyBird.controller.secondary.model.SecondaryMessageItem;
import com.crazyBird.controller.secondary.model.SecondaryMessageModel;
import com.crazyBird.controller.secondary.model.SecondaryMessageNumItem;
import com.crazyBird.controller.secondary.model.SecondaryMessageNumModel;
import com.crazyBird.controller.secondary.model.SecondarySlideItem;
import com.crazyBird.controller.secondary.model.SecondarySlideModel;
import com.crazyBird.controller.secondary.model.SecondaryTypeItem;
import com.crazyBird.controller.secondary.model.SecondaryTypeModel;
import com.crazyBird.controller.secondary.model.SecondaryUserAddressItem;
import com.crazyBird.controller.secondary.model.SecondaryUserAddressModel;
import com.crazyBird.controller.secondary.param.SearchSecondaryListParam;
import com.crazyBird.controller.secondary.param.SecondaryCommetsParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsByUserListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsCommentParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsGetCommetsParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsListParam;
import com.crazyBird.controller.secondary.param.SecondaryGoodsParam;
import com.crazyBird.controller.secondary.param.SecondaryUserAddressParam;
import com.crazyBird.dao.secondary.dataobject.SearchSecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCommentViewDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryCommetsMessageDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsByUserPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsCommentsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsDTO;
import com.crazyBird.dao.secondary.dataobject.SecondaryGoodsPO;
import com.crazyBird.dao.secondary.dataobject.SecondaryMessageDTO;
import com.crazyBird.dao.secondary.dataobject.SecondarySlideDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryTypeDO;
import com.crazyBird.dao.secondary.dataobject.SecondaryUserAddressDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.SecondaryService;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.PageUtils;
import com.crazyBird.utils.RegexUtils;
import com.ibm.icu.text.StringPrep;

@Component
public class SecondaryProcess {
	@Autowired
	private SecondaryService secondaryService;

	public SecondarySlideModel getSecondarySlide() {
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

	public SecondaryTypeModel getSecondaryType() {
		SecondaryTypeModel model = new SecondaryTypeModel();
		List<SecondaryTypeDO> tags = secondaryService.getSecondaryType();
		model.setList(convertSecondaryType(tags));
		return model;
	}

	public SecondaryTypeModel getSecondaryTradingWay() {
		SecondaryTypeModel model = new SecondaryTypeModel();
		List<SecondaryTypeDO> tags = secondaryService.getSecondaryTradingWay();
		model.setList(convertSecondaryType(tags));
		return model;
	}

	public SecondaryTypeModel getSecondaryWay() {
		SecondaryTypeModel model = new SecondaryTypeModel();
		List<SecondaryTypeDO> tags = secondaryService.getSecondaryWay();
		model.setList(convertSecondaryType(tags));
		return model;
	}

	public SecondaryGoodsModel searchSecondaryGoods(SearchSecondaryListParam param) {
		SecondaryGoodsModel model = new SecondaryGoodsModel();
		if (StringUtils.isBlank(param.getKeyWord())) {
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
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertSecondaryGoods(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}

		return model;

	}

	public SecondaryGoodsModel getSecondaryGoodsList(SecondaryGoodsListParam param) {
		SecondaryGoodsModel model = new SecondaryGoodsModel();
		/*
		 * if(param.getGoodsType()==null) { model.setCode(HttpCodeEnum.ERROR.getCode());
		 * model.setMessage("类型不能为空"); return model; }
		 */
		PageUtils.resetPageParam(param);
		SecondaryGoodsPO po = new SecondaryGoodsPO();
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setGoodsType(param.getGoodsType());
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = secondaryService.getSecondaryGoodsList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertSecondaryGoods(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}

		return model;

	}

	public SecondaryGoodModel getSecondaryGoods(Long id) {
		SecondaryGoodModel model = new SecondaryGoodModel();
		if (id == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("id不能为空");
		}
		secondaryService.createSecondaryViews(id);
		List<SecondaryGoodsDTO> list = secondaryService.getSecondaryGoods(id);
		model.setList(convertSecondaryGoods(list));
		return model;
	}
	
	public SimpleFlagModel updatetUserAddress(SecondaryUserAddressParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		SecondaryUserAddressDO addressDO = new SecondaryUserAddressDO();
		addressDO.setAddress(param.getAddress());
		addressDO.setId(param.getId());
		addressDO.setUserId(param.getUserId());
		addressDO.setTelephone(param.getTelephone());
		addressDO.setName(param.getName());
		addressDO.setAddress(param.getAddress());
		addressDO.setIsDefault(param.getIsDefault());
		if(param.getIsDefault()==1) {
			secondaryService.setUserAddress(param.getUserId());
		}
		secondaryService.updateUserAddress(addressDO);
		return model;
	}

	public SimpleFlagModel addUserAddress(SecondaryUserAddressParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		SecondaryUserAddressDO addressDO = new SecondaryUserAddressDO();
		addressDO.setAddress(param.getAddress());
		addressDO.setUserId(param.getUserId());
		addressDO.setTelephone(param.getTelephone());
		addressDO.setName(param.getName());
		addressDO.setAddress(param.getAddress());
		addressDO.setIsDefault(param.getIsDefault());
		if(param.getIsDefault()==1) {
			secondaryService.setUserAddress(param.getUserId());
		}
		secondaryService.addUserAddress(addressDO);
		return model;
	}

	public SecondaryGoodsModel getSecondaryGoodsByUser(SecondaryGoodsByUserListParam param) {
		SecondaryGoodsModel model = new SecondaryGoodsModel();
		if (param.getId() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("id不能为空");
			return model;
		}
		PageUtils.resetPageParam(param);
		SecondaryGoodsByUserPO po = new SecondaryGoodsByUserPO();
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setStatus(param.getStatus());
		po.setId(param.getId());
		ResponsePageQueryDO<List<SecondaryGoodsDTO>> response = secondaryService.getSecondaryGoodsByUser(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertSecondaryGoods(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;

	}

	public SecondaryMessageNumModel getSecondaryGoodsCommentsCount(Long id) {
		SecondaryMessageNumModel model = new SecondaryMessageNumModel();
		int count1 = secondaryService.getNewCommentCount(id);
		
		int count2 = secondaryService.getNewSecondaryViolationCount(id);
		System.out.println( count1+"-"+count2);
		List<SecondaryMessageNumItem> items = new ArrayList<>();
		List<Integer> num = new ArrayList<>();
		num.add(count1);
		num.add(count2);
		for (Integer integer : num) {
			SecondaryMessageNumItem item = new SecondaryMessageNumItem();
			item.setNum(integer);
			items.add(item);
		}

		model.setList(items);
		model.setSum(count1 + count2);

		return model;

	}

	public SimpleFlagModel deleteSecondaryGoods(Long id) {
		SimpleFlagModel model = new SimpleFlagModel();
		if (id == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("id不能为空");
			return model;
		}
		int flag = secondaryService.deleteSecondaryGoods(id);
		if (flag <= 0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("删除失败");
			return model;
		}
		return model;

	}
	public SimpleFlagModel createSecondaryGoods(SecondaryGoodsParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		if(!RegexUtils.isLegalMobile(param.getTelephone())) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("请输入正确的联系方式");
			return model;
		}
		SecondaryGoodsDO goodsDO = new SecondaryGoodsDO();
		goodsDO.setGoodsContent(param.getGoodsContent());
		goodsDO.setGoodsImag(param.getGoodsImag());
		goodsDO.setGoodsTitle(param.getGoodsTitle());
		goodsDO.setGoodsType(param.getGoodsType());
		goodsDO.setGoodsWay(param.getGoodsWay());
		goodsDO.setPostion(param.getPostion());
		goodsDO.setPrice(param.getPrice());
		goodsDO.setOldPrice(param.getOldPrice());
		goodsDO.setTradingWay(param.getTradingWay());
		goodsDO.setUserId(param.getUserId());
		goodsDO.setTelephone(param.getTelephone());
		ResponseDO responseDO = secondaryService.createSecondaryGoods(goodsDO);
		if (responseDO.isSuccess()) {
			model.setMessage("发布成功");
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("发布失败");
		}
		return model;

	}
	public SecondaryCommetsMessageModel getCommentMessage(SecondaryCommetsParam param){
		SecondaryCommetsMessageModel model = new SecondaryCommetsMessageModel();
		SecondaryGoodsCommentsPO po = new SecondaryGoodsCommentsPO();
		PageUtils.resetPageParam(param);
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setId(param.getUserId());
		 ResponsePageQueryDO<List<SecondaryCommetsMessageDTO>> response = secondaryService.getCommentMessage(po);
		 if (response.isSuccess()) {
			 PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertCommentMessage(response.getDataResult()));
		 }
		 else {
				model.setCode(HttpCodeEnum.ERROR.getCode());
				model.setMessage(response.getMessage());
		}
		return model;	
	}
	public SecondaryGoodsCommentsModel getSecondaryGoodsComments(SecondaryGoodsGetCommetsParam param) {
		SecondaryCommentViewDO viewDO = new SecondaryCommentViewDO();
		SecondaryGoodsCommentsModel model = new SecondaryGoodsCommentsModel();
		if (param.getId() == null || param.getUserId() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数不能为空");
			return model;
		}
		viewDO.setGoodsId(param.getId());
		viewDO.setUserId(param.getUserId());
		PageUtils.resetPageParam(param);
		SecondaryGoodsCommentsPO po = new SecondaryGoodsCommentsPO();
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setId(param.getId());
		int count = secondaryService.getSecondaryGoodsCommentsNum(param.getId());
		model.setCommentsNum(count);
		ResponsePageQueryDO<List<SecondaryGoodsCommentsDTO>> response = secondaryService.getSecondaryGoodsComment(po);
		secondaryService.updateSecondaryComments(viewDO);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setList(convertSecondaryComments(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}
	
	public SimpleFlagModel createSecondaryGoodsComment(SecondaryGoodsCommentParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		SecondaryGoodsCommentDO commentDO = new SecondaryGoodsCommentDO();
		commentDO.setGoodsId(param.getGoodsId());
		commentDO.setContent(param.getContent());
		commentDO.setReplyId(param.getUserId());
		commentDO.setReplyedId(param.getReplyedId());
		int i = secondaryService.createSecondaryGoodsComment(commentDO);
		if (i <= 0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("评论失败");
			return model;
		}
		return model;
	}

	public SimpleFlagModel createSecondaryGoodsReply(SecondaryGoodsCommentParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		SecondaryGoodsCommentDO commentDO = new SecondaryGoodsCommentDO();
		commentDO.setGoodsId(param.getGoodsId());
		commentDO.setContent(param.getContent());
		commentDO.setReplyId(param.getUserId());
		commentDO.setCommentsId(param.getId());
		commentDO.setReplyedId(param.getReplyedId());
		int i = secondaryService.createSecondaryGoodsReply(commentDO);
		if (i <= 0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("回复失败");
			return model;
		}
		return model;

	}
	public SimpleFlagModel updateSecondaryCommentsNoSee(SecondaryGoodsGetCommetsParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		if (param.getId() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数不能为空");
			return model;
		}
		
		secondaryService.updateSecondaryCommentsNoSee(param.getId());
		return model;
		
	}
	public SimpleFlagModel updateSecondaryMessageNoSee(Long id) {
		SimpleFlagModel model = new SimpleFlagModel();
		secondaryService.updateSecondaryMessageNoSee(id);
		return model;
		
	}
	public SecondaryMessageDetailModel getSecondaryMessageDetail(Long id) {
		SecondaryMessageDetailModel model = new SecondaryMessageDetailModel();
		secondaryService.updateSecondaryMessage(id);
	
		SecondaryMessageDTO dto=secondaryService.getSecondaryMessageDetail(id);
		if(dto==null) {
			return model;
		}
		model.setGmtCreated(DateUtil.formatDate(dto.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
		model.setGoodsTitle(dto.getGoodsTitle());
		model.setIsView(dto.getIsView());
		model.setMessage(dto.getIsView());
		model.setTitle(dto.getTitle());
		model.setId(dto.getId());
		
		return model;
		
	}
	public SecondaryMessageModel getSecondaryMessage(Long id) {
		SecondaryMessageModel model = new SecondaryMessageModel();
		if(id==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setCode("参数为空");
			return model;
		}
		List<SecondaryMessageItem> items = new ArrayList<>();
		List<SecondaryMessageDTO> tags = secondaryService.getSecondaryMessage(id);
		for (SecondaryMessageDTO tag : tags) {
			SecondaryMessageItem item = new SecondaryMessageItem();
			item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
			item.setGoodsTitle(tag.getGoodsTitle());
			item.setIsView(tag.getIsView());
			item.setMessage(tag.getMessage());
			item.setTitle(tag.getTitle());
			item.setId(tag.getId());
			items.add(item);
		}
		model.setList(items);
		return model;
	}
	public SimpleFlagModel deleteUserAddress (Long id) {
		SimpleFlagModel model = new SimpleFlagModel();
		if (id == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("id不能为空");
			return model;
		}
		secondaryService.deleteUserAddress(id);
		return model;
		
	}
	public SecondaryUserAddressModel getUserAddress(Long id) {
		SecondaryUserAddressModel model = new SecondaryUserAddressModel();
		if (id == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("用户id不能为空");
			return model;
		}

		List<SecondaryUserAddressDO> tags = secondaryService.getUserAddress(id);
		List<SecondaryUserAddressItem> items = new ArrayList<>();
		int count =0;
		int flag=0;
		if (CollectionUtils.isNotEmpty(tags)) {
			for (SecondaryUserAddressDO tag : tags) {
				count=count+1;
				SecondaryUserAddressItem item = new SecondaryUserAddressItem();
				item.setAddress(tag.getAddress());
				item.setId(tag.getId());
				item.setName(tag.getName());
				item.setTelephone(tag.getTelephone());
				item.setIsDefault(tag.getIsDefault());
				if(tag.getIsDefault()==1) {
					flag = count-1;
				}
				items.add(item);
			}
			SecondaryUserAddressItem itemTop = items.get(flag);
			items.remove(flag);
			items.add(0,itemTop);
			model.setList(items);
		}

	

		return model;
	}

	private List<SecondaryGoodsCommentItem> convertSecondaryComments(List<SecondaryGoodsCommentsDTO> tags) {

		List<SecondaryGoodsCommentItem> list = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(tags)) {
			for (SecondaryGoodsCommentsDTO tag : tags) {
				List<SecondaryGoodsReplyItem> items = new ArrayList<>();
				SecondaryGoodsCommentItem item = new SecondaryGoodsCommentItem();
				item.setCommentName(tag.getReplyName());
				item.setContent(tag.getContent());
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setHeadImgUrl(tag.getHeadImgUrl());
				item.setId(tag.getId());
				item.setSchoolNum(tag.getSchoolNum());
				List<SecondaryGoodsCommentsDTO> dtos = secondaryService.getSecondaryGoodsReply(tag.getId());
				for (SecondaryGoodsCommentsDTO dto : dtos) {
					SecondaryGoodsReplyItem replyItem = new SecondaryGoodsReplyItem();
					replyItem.setContent(dto.getContent());
					replyItem.setGmtCreated(DateUtil.formatDate(dto.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
					replyItem.setHeadImgUrl(dto.getHeadImgUrl());
					replyItem.setReplyName(dto.getReplyName());
					replyItem.setReplyedName(dto.getReplyedName());
					replyItem.setSchoolNum(dto.getSchoolNum());
					items.add(replyItem);
				}
				item.setItems(items);
				list.add(item);
			}
		}
		return list;

	}

	private List<SecondaryTypeItem> convertSecondaryType(List<SecondaryTypeDO> tags) {
		List<SecondaryTypeItem> items = new ArrayList<>();
		for (SecondaryTypeDO tag : tags) {
			SecondaryTypeItem item = new SecondaryTypeItem();
			item.setId(tag.getId());
			item.setField(tag.getField());
			items.add(item);
		}
		return items;
	}
	private List<SecondaryCommetsMessageItem> convertCommentMessage(List<SecondaryCommetsMessageDTO> tags){
		List<SecondaryCommetsMessageItem> items = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(tags)) {
			for (SecondaryCommetsMessageDTO tag : tags) {
				SecondaryCommetsMessageItem item = new SecondaryCommetsMessageItem();
				item.setContent(tag.getContent());
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setGoodsId(tag.getGoodsId());
				item.setHeadImgUrl(tag.getHeadImgUrl());
				item.setId(tag.getId());
				item.setIsView(tag.getIsView());
				item.setReplyName(tag.getReplyName());
				items.add(item);
			}
		}
		return items;
		
	}
	private List<SecondaryGoodsItem> convertSecondaryGoods(List<SecondaryGoodsDTO> tags) {
		List<SecondaryGoodsItem> list = new ArrayList<>();
		if (!CollectionUtils.isEmpty(tags)) {
			for (SecondaryGoodsDTO tag : tags) {

				SecondaryGoodsItem item = new SecondaryGoodsItem();
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setGoodsContent(tag.getGoodsContent());
				item.setGoodsImg(tag.getGoodsImag());
				// item.setGoodsNum(tag.getGoodsNum());
				item.setGoodsTitle(tag.getGoodsTitle());
				item.setGoodsType(tag.getGoodsType());
				item.setGoodsWay(tag.getGoodsWay());
				item.setHeadImgUrl(tag.getHeadImgUrl());
				item.setId(tag.getId());
				item.setPostion(tag.getPostion());
				item.setPrice(String.valueOf(tag.getPrice()));
				item.setOldPrice(String.valueOf(tag.getOldPrice()));
				item.setTradingWay(tag.getTradingWay());
				item.setUserId(tag.getUserId());
				item.setStatus(tag.getStatus());
				item.setUserName(tag.getUserName());
				item.setViews(tag.getViews());
				list.add(item);

			}
		}
		return list;
	}

}
