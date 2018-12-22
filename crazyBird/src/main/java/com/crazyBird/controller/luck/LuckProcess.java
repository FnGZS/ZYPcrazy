package com.crazyBird.controller.luck;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.luck.model.LuckListItem;
import com.crazyBird.controller.luck.model.LuckListModel;
import com.crazyBird.controller.luck.param.LuckListPageParam;
import com.crazyBird.dao.luck.dataobject.LuckListDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.luck.LuckService;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.PageUtils;

@Component
public class LuckProcess {
	
	@Autowired
	private LuckService luckService;

	public LuckListModel getLuckList(LuckListPageParam param) {
		LuckListModel model = new LuckListModel();
		PageUtils.resetPageParam(param);
		LuckListPO po = new LuckListPO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setStatus(param.getStatus());
		ResponsePageQueryDO<List<LuckListDO>> response = luckService.getLuckList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertLuckList(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	private List<LuckListItem> convertLuckList (List<LuckListDO> dataResults){
		List<LuckListItem> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(LuckListDO dataResult : dataResults) {
				if(dataResult != null) {
					LuckListItem item = new LuckListItem();
					item.setId(dataResult.getId());
					item.setUserId(dataResult.getUserId());
					item.setLuckName(dataResult.getLuckName());
					item.setLuckPic(dataResult.getLuckPic());
					item.setExplain(dataResult.getExplain());
					item.setLotteryTime(dataResult.getLotteryTime());
					item.setStatus(dataResult.getStatus());
					item.setMode(dataResult.getMode());
					item.setGmtCreated(DateUtil.formatDate(dataResult.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
					items.add(item);
				}
			}
		}
		return items;
		
	}
}
