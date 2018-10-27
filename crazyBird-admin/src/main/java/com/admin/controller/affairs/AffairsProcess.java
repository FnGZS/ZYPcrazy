package com.admin.controller.affairs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.admin.controller.affairs.model.AffairsItem;
import com.admin.controller.affairs.model.AffairsPageModel;
import com.admin.controller.affairs.param.AffairsPageParam;
import com.admin.controller.base.BaseProcess;
import com.admin.dao.affairs.dataobject.AffairsDO;
import com.admin.dao.affairs.dataobject.AffairsPO;
import com.admin.model.enums.HttpCodeEnum;
import com.admin.service.affairs.AffairsService;
import com.admin.service.base.ResponsePageQueryDO;
import com.admin.utils.PageUtils;


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

	private List<AffairsItem> convertDemands(List<AffairsDO> dataResult) {
		// TODO Auto-generated method stub
		return null;
	}

}
