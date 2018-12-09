package com.crazyBird.controller.lost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.affairs.model.AffairsDetailsModel;
import com.crazyBird.controller.affairs.model.AffairsItem;
import com.crazyBird.controller.affairs.model.AffairsPageModel;
import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.lost.model.LostDetailsModel;
import com.crazyBird.controller.lost.model.LostInputModel;
import com.crazyBird.controller.lost.model.LostItem;
import com.crazyBird.controller.lost.model.LostMessageItem;
import com.crazyBird.controller.lost.model.LostMessageModel;
import com.crazyBird.controller.lost.model.LostPageModel;
import com.crazyBird.controller.lost.model.LostTypeItem;
import com.crazyBird.controller.lost.model.LostTypeModel;
import com.crazyBird.controller.lost.param.LostInputParam;
import com.crazyBird.controller.lost.param.LostPageParam;
import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import com.crazyBird.dao.lost.dataobject.LostArticleDO;
import com.crazyBird.dao.lost.dataobject.LostDO;
import com.crazyBird.dao.lost.dataobject.LostPO;
import com.crazyBird.dao.lost.dataobject.LostTypeDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.lost.LostService;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.PageUtils;
import com.crazyBird.utils.TokenUtils;
import com.google.zxing.common.StringUtils;
@Component
public class LostProcess extends BaseProcess {

	@Autowired
	private LostService lostService;

	public LostMessageModel getLostMessage() {
		LostMessageModel model = new LostMessageModel();
		List<LostTypeDO> tags = lostService.getLostMessage();
		if(CollectionUtil.isNotEmpty(tags)) {
			List<LostMessageItem> items = new ArrayList<LostMessageItem>();
			for(LostTypeDO tag : tags) {
				LostMessageItem item = new LostMessageItem();
				item.setTypeId(tag.getTypeId());
				item.setMessage(tag.getMessage());
				items.add(item);
			}
			model.setLostTypeList(items);
		}
		return model;
	}

	public LostTypeModel getLostType() {
		LostTypeModel model=new LostTypeModel();
		List<LostTypeDO> tags = lostService.getLostType();
		if(CollectionUtil.isNotEmpty(tags)) {
			List<LostTypeItem> items = new ArrayList<LostTypeItem>();
			for(LostTypeDO tag : tags) {
				LostTypeItem item = new LostTypeItem();
				item.setTypeId(tag.getTypeId());
				item.setTypeName(tag.getTypeName());
				items.add(item);
			}
			model.setLostTypeList(items);
		}
		return model;
	}


	public LostPageModel getLostList(LostPageParam param) {
		LostPageModel model = new LostPageModel();
		PageUtils.resetPageParam(param);
		LostPO po = new LostPO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setTypeId(param.getTypeId());
		po.setKey(param.getKey());
		po.setMessageId(param.getMessageId());
		if(param.getPublisher()!=null) {
			Long shoolNum = (long) 0;
			try {
				shoolNum = TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			po.setPublisher(shoolNum);
			if(po.getPublisher()==(long)0) {
				model.setCode(HttpCodeEnum.ERROR.getCode());
				model.setMessage("登录状态无效");
				return model;
			}
		}
		ResponsePageQueryDO<List<LostDO>> response = lostService.getLostList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertDemands(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	private List<LostItem> convertDemands(List<LostDO> dataResult) {
		List<LostItem> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResult)) {
			for(LostDO dataResults : dataResult) {
				if(dataResults != null) {
					LostItem item = new LostItem();
					item.setId(dataResults.getId());
					item.setBrow(dataResults.getBrow());
					item.setContent(dataResults.getContent());
					item.setFoundPic(dataResults.getFoundPic());
					item.setTypeId(dataResults.getTypeId());
					item.setGmtCreated(dataResults.getGmtCreated());
					item.setIsExamine(dataResults.getIsExamine());
					item.setIsSolve(dataResults.getIsSolve());
					item.setPublisher(dataResults.getPublisher());
					item.setAddress(dataResults.getAddress());
					item.setTitle(dataResults.getTitle());
					item.setMessageId(dataResults.getMessageId());
					item.setContact(dataResults.getContact());
					items.add(item);
				}
			}
		}
		return items;
	}

	public LostInputModel lostInput(LostInputParam param) {
		LostInputModel model=new LostInputModel();
		LostArticleDO DO=new LostArticleDO();

		Long shoolNum = (long) 0;
		try {
			shoolNum = TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DO.setAddress(param.getAddress());
		DO.setTitle(param.getTitle());
		DO.setTypeId(param.getTypeId());
		DO.setMessageId(param.getMessageId());
		DO.setContact(param.getContact());
		DO.setContent(param.getContent());
		DO.setFoundPic(param.getFoundPic());
		DO.setPublisher(shoolNum);
		ResponseDO<LostDO> response=lostService.lostInput(DO);
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}else{
			model.setCode(HttpCodeEnum.SUCCESS.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}

	public LostDetailsModel getLostDetails(Long id) {
		LostDetailsModel model = new LostDetailsModel();
		LostDO detail = lostService.getLostDetails(id);
		if(detail!=null) {
			LostItem item=new LostItem();
			item.setBrow(detail.getBrow());
			item.setContent(detail.getContent());
			item.setFoundPic(detail.getFoundPic());
			item.setGmtCreated(detail.getGmtCreated());
			item.setId(detail.getId());
			item.setIsExamine(detail.getIsExamine());
			item.setIsSolve(detail.getIsSolve());
			item.setAddress(detail.getAddress());
			item.setMessageId(detail.getMessageId());
			item.setPublisher(detail.getPublisher());
			item.setTitle(detail.getTitle());
			item.setTypeId(detail.getTypeId());
			item.setContact(detail.getContact());
			model.setDetails(item);
			return model;
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		model.setMessage("无此项");
		return model;
	}

	public LostInputModel getLostDelete(Long id) {
		LostInputModel model=new LostInputModel();
		ResponseDO<LostDO> response=lostService.lostDelete(id);
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}else{
			model.setCode(HttpCodeEnum.SUCCESS.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}




}
