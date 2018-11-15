package com.admin.controller.vote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.controller.base.SimpleFlagModel;
import com.admin.controller.vote.model.VoteActionCheckRecordModel;
import com.admin.controller.vote.model.VoteActionDetailItem;
import com.admin.controller.vote.model.VoteActionDetailListModel;
import com.admin.controller.vote.model.VoteActionDetailModel;
import com.admin.controller.vote.model.VoteActionDetailRankModel;
import com.admin.controller.vote.model.VoteActionHotItem;
import com.admin.controller.vote.model.VoteActionHotListModel;
import com.admin.controller.vote.model.VoteActionItem;
import com.admin.controller.vote.model.VoteActionListModel;
import com.admin.controller.vote.model.VoteActionRecordItem;
import com.admin.controller.vote.model.VoteActionRecordModel;
import com.admin.controller.vote.param.VoteActionDetailListParam;
import com.admin.controller.vote.param.VoteActionDetailParam;
import com.admin.controller.vote.param.VoteActionGetDetailParam;
import com.admin.controller.vote.param.VoteActionParam;
import com.admin.controller.vote.param.VoteActionStatusParam;
import com.admin.controller.vote.param.VoteDetailByIdParam;
import com.admin.controller.vote.param.VoteActionRecordParam;
import com.admin.controller.vote.param.VoteActionSearchDetailParam;
import com.admin.controller.vote.param.VoteRecordParam;
import com.admin.dao.vote.dataobject.VoteActionDO;
import com.admin.dao.vote.dataobject.VoteActionDetailDO;
import com.admin.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.admin.dao.vote.dataobject.VoteActionHotDTO;
import com.admin.dao.vote.dataobject.VoteActionPO;
import com.admin.dao.vote.dataobject.VoteActionRecordDTO;
import com.admin.dao.vote.dataobject.VoteActionRecordPO;
import com.admin.dao.vote.dataobject.VoteRecordDO;
import com.admin.model.enums.HttpCodeEnum;
import com.admin.service.base.ResponseDO;
import com.admin.service.base.ResponsePageQueryDO;
import com.admin.service.vote.VoteService;
import com.admin.utils.CollectionUtil;
import com.admin.utils.DateUtil;
import com.admin.utils.PageUtils;

@Component
public class VoteProcess {
	@Autowired
	private VoteService voteService;

	public VoteActionListModel getActionList(VoteActionStatusParam param) {
		VoteActionListModel model = new VoteActionListModel();
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
		if(param.getId()==null||param.getType()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数不能为空");
		}
		PageUtils.resetPageParam(param);
		VoteActionRecordPO po = new VoteActionRecordPO();
		po.setType(param.getType());
		po.setId(param.getId());
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
	public VoteActionDetailListModel getActionDetailList(VoteDetailByIdParam param) {
		VoteActionDetailListModel model = new VoteActionDetailListModel();
		
		List<VoteActionDetailDO> tags = voteService.getActionDetailList(param.getId());
		if (CollectionUtil.isEmpty(tags)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("活动出错");
			return model;
		}
		List<VoteActionDetailItem> detailItems = convertVoteActionDetail(tags);
		model.setVoteDetailList(detailItems);
		return model;
	}

	public VoteActionHotListModel getVoteActionHotList() {
		VoteActionHotListModel model = new VoteActionHotListModel();
		List<VoteActionHotDTO> tags = voteService.getVoteActionHotList();
		if (CollectionUtil.isEmpty(tags)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("暂无进行中的热门活动");
			return model;
		}
		List<VoteActionHotItem> actionItems = new ArrayList<>();
		for (VoteActionHotDTO tag : tags) {
			VoteActionHotItem item = new VoteActionHotItem();
			item.setId(tag.getReId());
			item.setActionId(tag.getId());
			item.setActionIntro(tag.getActionIntro());
			item.setActionName(tag.getActionName());
			item.setStartTime(DateUtil.formatDate(tag.getStartTime(), DateUtil.DATE_FORMAT_YMDHMS));
			item.setEndTime(DateUtil.formatDate(tag.getEndTime(), DateUtil.DATE_FORMAT_YMDHMS));
			item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
			item.setGmtModified(DateUtil.formatDate(tag.getGmtModified(), DateUtil.DATE_FORMAT_YMDHMS));
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
		model.setVoteList(actionItems);
		return model;
	}
	public VoteActionDetailRankModel selectActionDetailByName(VoteActionSearchDetailParam param) {
		VoteActionDetailRankModel model= new VoteActionDetailRankModel();
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
	public SimpleFlagModel addVoteAction(VoteActionParam param){
		SimpleFlagModel model = new SimpleFlagModel();
		VoteActionDO actionDO = new VoteActionDO();
		actionDO.setActionImage(param.getActionImage());
		actionDO.setActionIntro(param.getActionIntro());
		actionDO.setActionName(param.getActionName());
		actionDO.setEndTime(param.getEndTime());
		actionDO.setHost(param.getHost());
		actionDO.setStartTime(param.getStartTime());
		
		actionDO.setStatus(param.getStatus());
		actionDO.setTelephone(param.getTelephone());
		actionDO.setVisitNum(param.getVisitNum());
		actionDO.setVoteMax(param.getVoteMax());
		actionDO.setVoteMin(param.getVoteMin());
		actionDO.setVoteRuler(param.getVoteRuler());
		actionDO.setVoteSum(param.getVoteSum());
		
		int count = voteService.insertVoteAction(actionDO);
		if(count<=0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("添加失败");
		}
		return model;
	}
	public SimpleFlagModel addVoteActionDetail(VoteActionDetailParam param){
		SimpleFlagModel model = new SimpleFlagModel();
		VoteActionDetailDO detailDO = new VoteActionDetailDO();
		detailDO.setActionId(param.getActionId());
		detailDO.setClassName(param.getClassName());
		detailDO.setImageUrl(param.getImageUrl());
		detailDO.setNum(param.getNum());
		detailDO.setDetail(param.getDetail());
		detailDO.setPeopleName(param.getPeopleName());
		int count = voteService.insertVoteActionDetail(detailDO);
		if(count<=0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("添加失败");
		}
		return model;
	}
	public SimpleFlagModel updateVoteAction(VoteActionParam param){
		SimpleFlagModel model = new SimpleFlagModel();
		VoteActionDO actionDO = new VoteActionDO();
		actionDO.setActionImage(param.getActionImage());
		actionDO.setActionIntro(param.getActionIntro());
		actionDO.setActionName(param.getActionName());
		actionDO.setEndTime(param.getEndTime());
		actionDO.setHost(param.getHost());
		actionDO.setStartTime(param.getStartTime());
		actionDO.setId(param.getId());
		actionDO.setStatus(param.getStatus());
		actionDO.setTelephone(param.getTelephone());
		actionDO.setVisitNum(param.getVisitNum());
		actionDO.setVoteMax(param.getVoteMax());
		actionDO.setVoteMin(param.getVoteMin());
		actionDO.setVoteRuler(param.getVoteRuler());
		actionDO.setVoteSum(param.getVoteSum());
		
		int count = voteService.updateVoteAction(actionDO);
		if(count<=0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("更新失败");
		}
		return model;
	}
	public SimpleFlagModel updateVoteActionDetail(VoteActionDetailParam param){
		SimpleFlagModel model = new SimpleFlagModel();
		VoteActionDetailDO detailDO = new VoteActionDetailDO();
		detailDO.setActionId(param.getActionId());
		detailDO.setId(param.getId());
		detailDO.setClassName(param.getClassName());
		detailDO.setImageUrl(param.getImageUrl());
		detailDO.setNum(param.getNum());
		detailDO.setPeopleName(param.getPeopleName());
		detailDO.setSerialId(param.getSerialId());

		
		int count = voteService.updateVoteActionDetail(detailDO);
		if(count<=0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("更新失败");
		}
		return model;
	}
	
	public SimpleFlagModel deleteVoteAction(Long id){
		SimpleFlagModel model = new SimpleFlagModel();
		int count = voteService.deleteVoteAction(id);
		if(count<=0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("删除失败");
		}
		return model;
	}
	public SimpleFlagModel deleteVoteActionDetail(Long id){
		SimpleFlagModel model = new SimpleFlagModel();
		int count = voteService.deleteVoteActionDetail(id);
		if(count<=0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("删除失败");
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
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setGmtModified(DateUtil.formatDate(tag.getGmtModified(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setActionImage(tag.getActionImage());
				item.setHost(tag.getHost());
				item.setStatus(tag.getStatus());
				item.setTelephone(tag.getTelephone());
				item.setVoteMax(tag.getVoteMax());
				item.setVoteMin(tag.getVoteMin());
				item.setVisitNum(tag.getVisitNum());
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
				item.setDetail(tag.getDetail());
				item.setId(tag.getId());
				item.setActionId(tag.getActionId());
				item.setSerialId(tag.getSerialId());
				item.setPeopleName(tag.getPeopleName());
				item.setClassName(tag.getClassName());
				item.setImageUrl(tag.getImageUrl());
				item.setNum(tag.getNum());
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
				item.setDetail(tag.getDetail());
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				recordItems.add(item);
				}
			}
		}
		return recordItems;
	}
}
