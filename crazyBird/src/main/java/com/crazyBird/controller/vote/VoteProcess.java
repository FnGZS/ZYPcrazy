package com.crazyBird.controller.vote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.vote.model.VoteActionCheckRecordModel;
import com.crazyBird.controller.vote.model.VoteActionDetailItem;
import com.crazyBird.controller.vote.model.VoteActionDetailListModel;
import com.crazyBird.controller.vote.model.VoteActionDetailRankModel;
import com.crazyBird.controller.vote.model.VoteActionHotListModel;
import com.crazyBird.controller.vote.model.VoteActionItem;
import com.crazyBird.controller.vote.model.VoteActionListModel;
import com.crazyBird.controller.vote.model.VoteActionRecordItem;
import com.crazyBird.controller.vote.model.VoteActionRecordModel;
import com.crazyBird.controller.vote.model.VoteActionSlideItem;
import com.crazyBird.controller.vote.model.VoteActionSlideModel;
import com.crazyBird.controller.vote.param.VoteActionDetailListParam;
import com.crazyBird.controller.vote.param.VoteActionParam;
import com.crazyBird.controller.vote.param.VoteActionRecordParam;
import com.crazyBird.controller.vote.param.VoteActionSearchDetailParam;
import com.crazyBird.controller.vote.param.VoteRecordParam;
import com.crazyBird.dao.vote.dataobject.VoteActionDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.crazyBird.dao.vote.dataobject.VoteActionPO;
import com.crazyBird.dao.vote.dataobject.VoteActionRecordDTO;
import com.crazyBird.dao.vote.dataobject.VoteActionRecordPO;
import com.crazyBird.dao.vote.dataobject.VoteActionSlideDO;
import com.crazyBird.dao.vote.dataobject.VoteRecordDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.vote.VoteService;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.PageUtils;
import com.crazyBird.utils.TokenUtils;

@Component
public class VoteProcess extends BaseProcess {
	@Autowired
	private VoteService voteService;
	public VoteActionListModel getActionList(VoteActionParam param) {
		VoteActionListModel model = new VoteActionListModel();
		if (param.getStatus() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("没有此状态活动");
			return model;
		}
		PageUtils.resetPageParam(param);
		// List<VoteActionItem> actionItems = new ArrayList<>();
		VoteActionPO po = new VoteActionPO();
		po.setStatus(param.getStatus());
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<VoteActionDO>> response = voteService.getVoteActionList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setVoteList(convertVoteAction(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}
	
	public VoteActionRecordModel getVoteActionRecord(VoteActionRecordParam param) {
		VoteActionRecordModel model = new VoteActionRecordModel();
		if(param.getId()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数不能为空");
		}
		PageUtils.resetPageParam(param);
		VoteActionRecordPO po = new VoteActionRecordPO();
		po.setStudentId(param.getId());
		po.setPageIndex(param.getPageNo()-1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<VoteActionRecordDTO>> response = voteService.getVoteActionRecord(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setVoteRecord(convertVoteRecord(response.getDataResult()));
		}else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}
	public VoteActionDetailRankModel getActionDetailByRank(Long id) {
		VoteActionDetailRankModel model = new VoteActionDetailRankModel();
		if (id == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数为空");
		}
		List<VoteActionDetailDO> tags = voteService.getActionDetailByRank(id);
		if (CollectionUtil.isEmpty(tags)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("活动出错");
			return model;
		}
		model.setVoteDetailList(convertVoteActionDetail(tags));
		return model;
		
	}
	public VoteActionDetailListModel getActionDetailList(Long id) {
		VoteActionDetailListModel model = new VoteActionDetailListModel();
		if (id == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数为空");
		}
		VoteActionDO voteActionDO = voteService.getAction(id);
		List<VoteActionDetailDO> tags = voteService.getActionDetailList(id);
		if (CollectionUtil.isEmpty(tags)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("活动出错");
			return model;
		}
		Long ms = null;
		List<VoteActionDetailItem> detailItems = convertVoteActionDetail(tags);
		model.setActionIntro(voteActionDO.getActionIntro());
		model.setActionName(voteActionDO.getActionName());
		model.setStartTime(DateUtil.formatDate(voteActionDO.getStartTime(), DateUtil.DATE_FORMAT_YMDHMS));
		model.setEndTime(DateUtil.formatDate(voteActionDO.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
		model.setHost(voteActionDO.getHost());
		model.setActionImage(voteActionDO.getActionImage());
		model.setId(voteActionDO.getId());
		model.setStatus(voteActionDO.getStatus());
		model.setVoteMax(voteActionDO.getVoteMax());
		model.setVoteMin(voteActionDO.getVoteMin());
		model.setTelephone(voteActionDO.getTelephone());
		model.setVisitNum(voteActionDO.getVisitNum());
		model.setVoteRuler(voteActionDO.getVoteRuler());
		model.setVoteSum(voteActionDO.getVoteSum());
		model.setVoteDetailList(detailItems);
		if(voteActionDO.getStatus()==0) {
			 ms = voteActionDO.getStartTime().getTime()-new Date().getTime();
		}
		if(voteActionDO.getStatus()==1) {
			 ms = voteActionDO.getEndTime().getTime()-new Date().getTime();
		}
		if(voteActionDO.getStatus()==2){
			 ms = (long) 0;
		}
		model.setTimeDiff(ms);
		return model;
	}

	public VoteActionHotListModel getVoteActionHotList() {
		VoteActionHotListModel model = new VoteActionHotListModel();
		List<VoteActionDO> actionList = voteService.getVoteActionHotList();
		if (CollectionUtil.isEmpty(actionList)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("暂无进行中的热门活动");
			return model;
		}
		List<VoteActionItem> actionItems = convertVoteAction(actionList);
		model.setVoteList(actionItems);
		return model;
	}
	
	public VoteActionHotListModel getVoteActionHot() {
		VoteActionHotListModel model = new VoteActionHotListModel();
		List<VoteActionDO> actionList = voteService.getVoteActionHot();
		if (CollectionUtil.isEmpty(actionList)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("暂无进行中的热门活动");
			return model;
		}
		List<VoteActionItem> actionItems = convertVoteAction(actionList);
		model.setVoteList(actionItems);
		return model;
	}
	public VoteActionSlideModel getVoteActionSlide() {
		VoteActionSlideModel model = new VoteActionSlideModel();
		List<VoteActionSlideDO> tags = voteService.getVoteActionSlide();
		List<VoteActionSlideItem> items = new ArrayList<>();
		for (VoteActionSlideDO tag: tags) {
			VoteActionSlideItem item = new VoteActionSlideItem();
			item.setActionId(tag.getActionId());
			item.setPicUrl(tag.getPicUrl());
			items.add(item);
		}
		model.setItems(items);
		return model;
		
	}
	public SimpleFlagModel createVoteDetailNum(VoteActionDetailListParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		VoteRecordDO recordDO = new VoteRecordDO();
		if(param.getStudentId()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("学号不能为空");
			return model;
		}
		Long shoolNum = (long) 0;
		try {
			shoolNum = TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(param.getStudentId() != shoolNum) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("学号非法");
			return model;
		}
		recordDO.setActionId(param.getActionId());
		recordDO.setStudentId(param.getStudentId());
		recordDO.setDetail(param.getDetail());
		recordDO.setSum(param.getSum());	
		if(voteService.checkActionStatus(param.getActionId())!=1) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("活动未开始或已结束");
			return model;
		}
		if(voteService.checkVoteRecord(recordDO).getDataResult()!=null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("你已经投过票");
			return model;
		}
		ResponseDO response = voteService.createVoteRecord(recordDO);
		if(!response.isSuccess()){
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}
	public VoteActionDetailRankModel selectActionDetailByName(VoteActionSearchDetailParam param) {
		VoteActionDetailRankModel model= new VoteActionDetailRankModel();
		if(StringUtils.isBlank(param.getPeopleName())||param.getActionId()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数错误");
			return model;
		}
		VoteActionDetailSearchDO searchDO = new VoteActionDetailSearchDO();
		searchDO.setActionId(param.getActionId());
		searchDO.setPeopleName(param.getPeopleName());
		List<VoteActionDetailDO> tags = voteService.selectActionDetailByName(searchDO);
		if (CollectionUtil.isEmpty(tags)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("活动出错");
			return model;
		}
		model.setVoteDetailList(convertVoteActionDetail(tags));
		return model;
	}
	public VoteActionCheckRecordModel checkVoteRecord(VoteRecordParam param) {
		VoteActionCheckRecordModel model = new VoteActionCheckRecordModel();  
		if(param.getActionId()==null||param.getStudentId()==null){
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数错误");
			return model;	
		}
		VoteRecordDO recordDO = new VoteRecordDO();
		recordDO.setActionId(param.getActionId());
		recordDO.setStudentId(param.getStudentId());
		ResponseDO<VoteRecordDO> response = voteService.checkVoteRecord(recordDO);
		if(response.getDataResult()!=null) {		
			model.setStatus(1);
			model.setDetail(response.getDataResult().getDetail());
			model.setMessage("你已经投过票");
		}
		return model;	
	}

	private List<VoteActionItem> convertVoteAction(List<VoteActionDO> tags) {
		List<VoteActionItem> actionItems = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(tags)) {
			for (VoteActionDO tag : tags) {
				if(tag!=null) {
				VoteActionItem item = new VoteActionItem();
				item.setId(tag.getId());
				item.setActionIntro(tag.getActionIntro());
				item.setActionName(tag.getActionName());
				item.setStartTime(DateUtil.formatDate(tag.getStartTime(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setEndTime(DateUtil.formatDate(tag.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setActionImage(tag.getActionImage());
				item.setHost(tag.getHost());
				item.setStatus(tag.getStatus());
				item.setTelephone(tag.getTelephone());
				item.setVisitNum(tag.getVisitNum());
				item.setVoteMin(tag.getVoteMin());
				item.setVoteMax(tag.getVoteMax());
				item.setVoteRuler(tag.getVoteRuler());
				item.setVoteSum(tag.getVoteSum());
				actionItems.add(item);
				}
			}
		}
		return actionItems;
	}
	
	private List<VoteActionDetailItem> convertVoteActionDetail(List<VoteActionDetailDO> tags){
		List<VoteActionDetailItem> detailItems = new ArrayList<>();
		for (VoteActionDetailDO tag : tags) {
			if (tag != null) {
				VoteActionDetailItem item = new VoteActionDetailItem();
				item.setActionId(tag.getActionId());
				item.setSerialId(tag.getSerialId());
				item.setBranch(tag.getBranch());
				item.setClassName(tag.getClassName());
				item.setCompete(tag.getCompete());
				item.setContent(tag.getContent());
				item.setHonor(tag.getHonor());
				item.setScientific(tag.getScientific());
				item.setId(tag.getId());
				item.setImageUrl(tag.getImageUrl());
				item.setNum(tag.getNum());
				item.setPeopleName(tag.getPeopleName());
				item.setPolitical(tag.getPolitical());
				item.setPost(tag.getPost());
				item.setRecommend(tag.getRecommend());
				item.setStory(tag.getStory());			
				detailItems.add(item);
			}
		}
		return detailItems;
	}
	
	private List<VoteActionRecordItem> convertVoteRecord(List<VoteActionRecordDTO> tags) {
		List<VoteActionRecordItem> recordItems = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(tags)) {
			for (VoteActionRecordDTO tag : tags) {
				if(tag!=null) {
				VoteActionRecordItem item = new VoteActionRecordItem();
				item.setId(tag.getId());
				item.setActionIntro(tag.getActionIntro());
				item.setActionName(tag.getActionName());
				item.setStartTime(DateUtil.formatDate(tag.getStartTime(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setEndTime(DateUtil.formatDate(tag.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setActionImage(tag.getActionImage());
				item.setHost(tag.getHost());
				item.setStatus(tag.getStatus());
				item.setTelephone(tag.getTelephone());
				item.setVisitNum(tag.getVisitNum());
				item.setVoteRuler(tag.getVoteRuler());
				item.setVoteSum(tag.getVoteSum());
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				recordItems.add(item);
				}
			}
		}
		return recordItems;
	}
}
