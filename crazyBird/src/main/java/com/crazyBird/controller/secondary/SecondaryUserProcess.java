package com.crazyBird.controller.secondary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.secondary.model.SellSecondaryItem;
import com.crazyBird.controller.secondary.model.SellSecondaryListModel;
import com.crazyBird.controller.secondary.param.SellSecondaryParam;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryDTO;
import com.crazyBird.dao.secondary.dataobject.SellSecondaryPO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.secondary.UserSecondaryService;
import com.crazyBird.utils.PageUtils;

@Component
public class SecondaryUserProcess {

	@Autowired
	private UserSecondaryService userSecondaryService;
	
	
	public SellSecondaryListModel getSellList(SellSecondaryParam param) {
		SellSecondaryListModel model = new SellSecondaryListModel();
		if(param.getUserId() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("");
			return model;
		}
		PageUtils.resetPageParam(param);
		SellSecondaryPO po = new SellSecondaryPO();
		po.setUserId(param.getUserId());
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<SellSecondaryDTO>> response = userSecondaryService.getSellList(po);
		if(response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setTags(convertSellList(response.getDataResult()));
		}
		else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}


	private List<SellSecondaryItem> convertSellList (List<SellSecondaryDTO> tags){
		List<SellSecondaryItem> list = new ArrayList<>();
		return list;
	}
	

}
