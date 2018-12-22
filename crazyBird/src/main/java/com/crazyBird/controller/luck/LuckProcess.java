package com.crazyBird.controller.luck;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.luck.model.LuckDetailsModel;
import com.crazyBird.controller.luck.model.LuckListItem;
import com.crazyBird.controller.luck.model.LuckListModel;
import com.crazyBird.controller.luck.model.LuckPartakeItems;
import com.crazyBird.controller.luck.model.LuckPartakeModel;
import com.crazyBird.controller.luck.model.LuckWinnersItems;
import com.crazyBird.controller.luck.model.LuckWinnersModel;
import com.crazyBird.controller.luck.param.LuckListPageParam;
import com.crazyBird.controller.luck.param.LuckPartakePageParam;
import com.crazyBird.controller.luck.param.LuckWinnersPageParam;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.dao.luck.dataobject.LuckLuckPartakeDO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckPrizeDO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;
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
		ResponsePageQueryDO<List<LuckDetailsDO>> response = luckService.getLuckList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertLuckList(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	public LuckWinnersModel getLuckWinners(LuckWinnersPageParam param) {
		LuckWinnersModel model = new LuckWinnersModel();
		PageUtils.resetPageParam(param);
		LuckWinnersPO po = new LuckWinnersPO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setStatus(param.getStatus());
		po.setLuckId(param.getLuckId());
		ResponsePageQueryDO<List<LuckWinnersDO>> response = luckService.getLuckWinners(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertLuckWinners(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}
	
	
	public LuckPartakeModel getLuckPartake(LuckPartakePageParam param) {
		LuckPartakeModel model = new LuckPartakeModel();
		PageUtils.resetPageParam(param);
		LuckPartakePO po = new LuckPartakePO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setLuckId(param.getLuckId());
		ResponsePageQueryDO<List<LuckLuckPartakeDO>> response = luckService.getLuckPartake(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertLuckPartake(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}
	
	private List<LuckPartakeItems> convertLuckPartake (List<LuckLuckPartakeDO> dataResults){
		List<LuckPartakeItems> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(LuckLuckPartakeDO dataResult : dataResults) {
				if(dataResult != null) {
					LuckPartakeItems item = new LuckPartakeItems();
					item.setId(dataResult.getId());
					item.setUserId(dataResult.getUserId());
					item.setLuckId(dataResult.getLuckId());
					item.setIsWinning(dataResult.getIsWinning());
					item.setUserName(dataResult.getUserName());
					item.setHeadImgUrl(dataResult.getHeadImgUrl());
					items.add(item);
				}
			}
		}
		return items;
	}
	
	private List<LuckWinnersItems> convertLuckWinners (List<LuckWinnersDO> dataResults){
		List<LuckWinnersItems> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(LuckWinnersDO dataResult : dataResults) {
				if(dataResult != null) {
					LuckWinnersItems item = new LuckWinnersItems();
					item.setId(dataResult.getId());
					item.setUserId(dataResult.getUserId());
					item.setLuckId(dataResult.getLuckId());
					item.setIswinning(dataResult.getIsWinning());
					item.setPriceId(dataResult.getPriceId());
					item.setLuckPrize(dataResult.getLuckPrize());
					item.setNum(dataResult.getNum());
					item.setLuckPic(dataResult.getLuckPic());
					item.setUserName(dataResult.getUserName());
					item.setHeadImgUrl(dataResult.getHeadImgUrl());
					items.add(item);
				}
			}
		}
		return items;
		
	}
	
	private List<LuckListItem> convertLuckList (List<LuckDetailsDO> dataResults){
		List<LuckListItem> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(LuckDetailsDO dataResult : dataResults) {
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

	public LuckDetailsModel getLuckDetails(Long luckId) {
		LuckDetailsModel model = new LuckDetailsModel();
		LuckDetailsDO details = luckService.getLuckDetails(luckId);
		if(details != null) {
			model.setId(details.getId());
			model.setUserId(details.getUserId());
			model.setLuckName(details.getLuckName());
			model.setLuckPic(details.getLuckPic());
			model.setExplain(details.getExplain());
			model.setLotteryTime(details.getLotteryTime());
			model.setStatus(details.getStatus());
			model.setMode(details.getMode());
			model.setGmtCreated(DateUtil.formatDate(details.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
			List<LuckPrizeDO> prize = luckService.getLuckPrize(luckId);
			
		}
		return model;
	}




}
