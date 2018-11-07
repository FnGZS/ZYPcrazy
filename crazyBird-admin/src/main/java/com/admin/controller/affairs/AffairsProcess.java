package com.admin.controller.affairs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admin.controller.affairs.model.AddAffairsModel;
import com.admin.controller.affairs.model.AffairsItem;
import com.admin.controller.affairs.model.AffairsPageModel;
import com.admin.controller.affairs.model.DeleteAffairsModel;
import com.admin.controller.affairs.param.AddAffairsParam;
import com.admin.controller.affairs.param.AffairsPageParam;
import com.admin.controller.base.BaseProcess;
import com.admin.dao.affairs.dataobject.AffairsDO;
import com.admin.dao.affairs.dataobject.AffairsPO;
import com.admin.model.enums.HttpCodeEnum;
import com.admin.service.affairs.AffairsService;
import com.admin.service.base.ResponsePageQueryDO;
import com.admin.utils.PageUtils;
import com.admin.controller.affairs.model.AffairsDetailsModel;
import com.admin.dao.affairs.dataobject.AddAffairDO;
import com.admin.service.base.ResponseDO;
import com.admin.utils.CollectionUtil;


@Component
public class AffairsProcess extends BaseProcess{


	@Autowired
	private AffairsService affairsService;
	
	public AffairsPageModel getAffairsList(AffairsPageParam param) {
		AffairsPageModel model = new AffairsPageModel();
		PageUtils.resetPageParam(param);
		AffairsPO po = new AffairsPO();
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		po.setTypeId(param.getTypeId());
		po.setKey(param.getKey());
		ResponsePageQueryDO<List<AffairsDO>> response = affairsService.getAffairsList(po);
		if(response.isSuccess()){
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertDemands(response.getDataResult()));
		}else{
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}

	private List<AffairsItem> convertDemands(List<AffairsDO> dataResults) {
		List<AffairsItem> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(AffairsDO dataResult : dataResults) {
				if(dataResult != null) {
					AffairsItem item = new AffairsItem();
					item.setId(dataResult.getId());
					item.setTitle(dataResult.getTitle());
					item.setAffairsPic(dataResult.getAffairsPic());
					item.setContent(dataResult.getContent() == null ? "" : new String(dataResult.getContent()));
					item.setTypeId(dataResult.getTypeId());
					item.setBrows(dataResult.getBrows());
					item.setSubordinate(dataResult.getSubordinate());
					item.setYear(dataResult.getGmtCreated().substring(0, 4));
					item.setDay(dataResult.getGmtCreated().substring(5, 11));
					item.setMinute(dataResult.getGmtCreated().substring(11, 19));
					items.add(item);
				}
			}
		}
		return items;
	}

	public AddAffairsModel addAffair(AddAffairsParam param) {
		AddAffairsModel model = new AddAffairsModel();
		AddAffairDO affair = new AddAffairDO();
		affair.setTitle(param.getTitle());
		affair.setAffairsPic(param.getAffairsPic());
		affair.setTypeId(param.getTypeId());
		affair.setContent(StringUtils.isNotBlank(param.getContent()) ? param.getContent().getBytes() : null);
		affair.setSubordinate(param.getSubordinate());
		ResponseDO<Long> response = affairsService.addAffair(affair);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			model.setResult(response.getDataResult());
			return model;
		}
		model.setMessage(response.getMessage());
		model.setResult(response.getDataResult());
		return model;
	}

	public DeleteAffairsModel deleteAffair(Long id) {
		DeleteAffairsModel model = new DeleteAffairsModel();
		ResponseDO<Long> response = affairsService.deleteAffair(id);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			model.setResult(response.getDataResult());
			return model;
		}
		model.setMessage(response.getMessage());
		model.setResult(response.getDataResult());
		return model;
	}

	public AffairsDetailsModel getAffairsDetails(Long id) {
		AffairsDetailsModel model = new AffairsDetailsModel();
		AffairsDO detail = affairsService.getAffairsDetails(id);
		if(detail!=null) {
			AffairsItem affairs = new AffairsItem();
			affairs.setId(detail.getId());
			affairs.setTitle(detail.getTitle());
			affairs.setAffairsPic(detail.getAffairsPic());
			affairs.setContent(detail.getContent() == null ? "" : new String(detail.getContent()));
			affairs.setTypeId(detail.getTypeId());
			affairs.setBrows(detail.getBrows());
			affairs.setSubordinate(detail.getSubordinate());
			affairs.setYear(detail.getGmtCreated().substring(0, 4));
			affairs.setDay(detail.getGmtCreated().substring(5, 11));
			affairs.setMinute(detail.getGmtCreated().substring(11, 19));
			model.setDetails(affairs);
			return model;
		}
		model.setCode(HttpCodeEnum.ERROR.getCode());
		model.setMessage("无此项");
		return model;
	}

}
