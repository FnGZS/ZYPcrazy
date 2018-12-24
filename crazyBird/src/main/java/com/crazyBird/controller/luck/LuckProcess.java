package com.crazyBird.controller.luck;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.luck.model.AddLuckModel;
import com.crazyBird.controller.luck.model.AddPrizeModel;
import com.crazyBird.controller.luck.model.LuckAdvertisementItem;
import com.crazyBird.controller.luck.model.LuckAdvertisementModel;
import com.crazyBird.controller.luck.model.LuckDetailsModel;
import com.crazyBird.controller.luck.model.LuckIsPartModel;
import com.crazyBird.controller.luck.model.LuckListItem;
import com.crazyBird.controller.luck.model.LuckListModel;
import com.crazyBird.controller.luck.model.LuckPartakeItems;
import com.crazyBird.controller.luck.model.LuckPartakeModel;
import com.crazyBird.controller.luck.model.LuckPrizeItems;
import com.crazyBird.controller.luck.model.LuckPrizeModel;
import com.crazyBird.controller.luck.model.LuckWinnersItems;
import com.crazyBird.controller.luck.model.LuckWinnersModel;
import com.crazyBird.controller.luck.param.AddLuckParam;
import com.crazyBird.controller.luck.param.AwardParam;
import com.crazyBird.controller.luck.param.DeleaseParam;
import com.crazyBird.controller.luck.param.IsPartParam;
import com.crazyBird.controller.luck.param.JoinListParam;
import com.crazyBird.controller.luck.param.LuckListPageParam;
import com.crazyBird.controller.luck.param.LuckPartakePageParam;
import com.crazyBird.controller.luck.param.LuckPrizeParam;
import com.crazyBird.controller.luck.param.LuckWinnersPageParam;
import com.crazyBird.dao.luck.dataobject.AdvertisementDO;
import com.crazyBird.dao.luck.dataobject.DeleasePO;
import com.crazyBird.dao.luck.dataobject.IsPartDO;
import com.crazyBird.dao.luck.dataobject.JoinListPO;
import com.crazyBird.dao.luck.dataobject.LuckDetailsDTO;
import com.crazyBird.dao.luck.dataobject.LuckDrawDO;
import com.crazyBird.dao.luck.dataobject.LuckListPO;
import com.crazyBird.dao.luck.dataobject.LuckPartakeDTO;
import com.crazyBird.dao.luck.dataobject.LuckPartakePO;
import com.crazyBird.dao.luck.dataobject.LuckPrizeDO;
import com.crazyBird.dao.luck.dataobject.LuckPrizePO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersDTO;
import com.crazyBird.dao.luck.dataobject.LuckWinnersPO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
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
		ResponsePageQueryDO<List<LuckDetailsDTO>> response = luckService.getLuckList(po);
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
		ResponsePageQueryDO<List<LuckWinnersDTO>> response = luckService.getLuckWinners(po);
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
		ResponsePageQueryDO<List<LuckPartakeDTO>> response = luckService.getLuckPartake(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertLuckPartake(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}
	
	private List<LuckPartakeItems> convertLuckPartake (List<LuckPartakeDTO> dataResults){
		List<LuckPartakeItems> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(LuckPartakeDTO dataResult : dataResults) {
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
	
	private List<LuckWinnersItems> convertLuckWinners (List<LuckWinnersDTO> dataResults){
		List<LuckWinnersItems> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(LuckWinnersDTO dataResult : dataResults) {
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
	
	private List<LuckListItem> convertLuckList (List<LuckDetailsDTO> dataResults){
		List<LuckListItem> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(dataResults)) {
			for(LuckDetailsDTO dataResult : dataResults) {
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
					item.setUserName(dataResult.getUserName());
					item.setHeadImgUrl(dataResult.getHeadImgUrl());
					List<LuckPrizeDO> prizes = luckService.getLuckPrize(dataResult.getId());
					item.setItems(convertPrizeList(prizes));
					items.add(item);
				}
			}
		}
		return items;
		
	}

	public LuckDetailsModel getLuckDetails(Long luckId) {
		LuckDetailsModel model = new LuckDetailsModel();
		LuckDetailsDTO details = luckService.getLuckDetails(luckId);
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
			List<LuckPrizeDO> prizes = luckService.getLuckPrize(luckId);
			model.setItems(convertPrizeList(prizes));
		}
		return model;
	}

	public LuckIsPartModel getIsPart(IsPartParam param) {
		LuckIsPartModel model = new LuckIsPartModel();
		IsPartDO isPart = new IsPartDO();
		isPart.setLuckId(param.getLuckId());
		isPart.setUserId(param.getUserId());
		ResponseDO<String> response = luckService.isPart(isPart);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setMessage(response.getMessage());
		return model;
	}

	public AddPrizeModel AddPrize(LuckPrizeParam param) {
		AddPrizeModel model = new AddPrizeModel();
		LuckPrizeDO luckPrize = new LuckPrizeDO();
		luckPrize.setLuckDrawId(param.getLuckDrawId());
		luckPrize.setLuckPrize(param.getLuckPrize());
		luckPrize.setNum(param.getNum());
		luckPrize.setLuckPic(param.getLuckPic());
		luckPrize.setSponsor(param.getSponsor());
		ResponseDO<String> response = luckService.addPrize(luckPrize);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setMessage(response.getMessage());
		return model;
	}

	public AddLuckModel AddLuck(AddLuckParam param) {
		AddLuckModel model = new AddLuckModel();
		LuckDrawDO luckDraw = new LuckDrawDO();
		luckDraw.setUserId(param.getUserId());
		luckDraw.setLuckName(param.getLuckName());
		luckDraw.setLuckPic(param.getLuckPic());
		luckDraw.setLuckExplain(param.getLuckExplain());
		luckDraw.setLotteryTime(param.getLotteryTime());
		luckDraw.setLuckPrizeExplain(param.getLuckPrizeExplain());
		luckDraw.setLuckMode(param.getLuckMode());
		ResponseDO<String> response = luckService.AddLuck(luckDraw);
		if (!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			return model;
		}
		model.setMessage(response.getMessage());
		return model;
	}

	public LuckAdvertisementModel Advertisement() {
		LuckAdvertisementModel model = new LuckAdvertisementModel();
		List<AdvertisementDO> advertisements = luckService.getAdvertisement();
		List<LuckAdvertisementItem> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(advertisements)) {
			for(AdvertisementDO advertisement:advertisements) {
				if(advertisement!=null) {
					LuckAdvertisementItem item = new LuckAdvertisementItem();
					item.setId(advertisement.getId());
					item.setPic(advertisement.getPic());
					items.add(item);
				}
			}
		}
		model.setItems(items);
		return model;
	}

	public LuckListModel delease(DeleaseParam param) {
		LuckListModel model = new LuckListModel();
		PageUtils.resetPageParam(param);
		DeleasePO po = new DeleasePO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setUserId(param.getUserId());
		ResponsePageQueryDO<List<LuckDetailsDTO>> response = luckService.delease(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertLuckList(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	public LuckListModel getJoinList(JoinListParam param) {
		LuckListModel model = new LuckListModel();
		PageUtils.resetPageParam(param);
		JoinListPO po = new JoinListPO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setUserId(param.getUserId());
		ResponsePageQueryDO<List<LuckDetailsDTO>> response = luckService.getJoinList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertLuckList(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	public LuckPrizeModel awardList(AwardParam param) {
		LuckPrizeModel model = new LuckPrizeModel();
		PageUtils.resetPageParam(param);
		LuckPrizePO po = new LuckPrizePO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setUserId(param.getUserId());
		ResponsePageQueryDO<List<LuckPrizeDO>> response = luckService.awardList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertPrizeList(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}
	private List<LuckPrizeItems> convertPrizeList (List<LuckPrizeDO> prizes){
		List<LuckPrizeItems> items = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(prizes)) {
			for(LuckPrizeDO prize:prizes) {
				if(prize!=null) {
					LuckPrizeItems item = new LuckPrizeItems();
					item.setId(prize.getId());
					item.setLuckPrize(prize.getLuckPrize());
					item.setLuckPic(prize.getLuckPic());
					item.setSponsor(prize.getSponsor());
					item.setNum(prize.getNum());
					item.setLuckDrawId(prize.getLuckDrawId());
					item.setGmtCreated(prize.getGmtCreated());
					items.add(item);
				}
			}
		}
		return items;
		
	}



}
