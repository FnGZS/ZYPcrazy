package com.crazyBird.controller.affairs;

import com.crazyBird.controller.affairs.model.AffairsItem;
import com.crazyBird.controller.affairs.model.AffairsPageModel;
import com.crazyBird.controller.affairs.param.AffairsPageParam;
import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.dao.affairs.dataobject.AffairsDO;
import com.crazyBird.dao.affairs.dataobject.AffairsPO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.affairs.AffairsService;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.PageUtils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AffairsProcess extends BaseProcess {
	@Autowired
	private AffairsService affairsService;

	public AffairsPageModel getAffairsList(AffairsPageParam param) {
		AffairsPageModel model = new AffairsPageModel();
		PageUtils.resetPageParam(param);
		AffairsPO po = new AffairsPO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setTypeId(param.getTypeId());
		po.setKey(param.getKey());
		ResponsePageQueryDO<List<AffairsDO>> response = affairsService.getAffairsList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertDemands(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}

	private List<AffairsItem> convertDemands(List<AffairsDO> dataResults) {
		List<AffairsItem> items = new ArrayList<>();
		if(CollectionUtil.isEmpty(dataResults)) {
			for(AffairsDO dataResult : dataResults) {
				if(dataResult != null) {
					AffairsItem item = new AffairsItem();
					item.setId(dataResult.getId());
					item.setTitle(dataResult.getTitle());
					item.setAffairsPic(dataResult.getAffairsPic());
					item.setContent(dataResult.getContent() == null ? "" : new String(dataResult.getContent()));
					item.setTypeId(dataResult.getTypeId());
					item.setBrows(dataResult.getBrows());
					item.setGmtCreated(dataResult.getGmtCreated());
					items.add(item);
				}
			}
		}
		return items;
	}
}

