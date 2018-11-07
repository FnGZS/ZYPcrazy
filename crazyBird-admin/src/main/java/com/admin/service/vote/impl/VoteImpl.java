package com.admin.service.vote.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.admin.dao.vote.VoteDao;
import com.admin.dao.vote.VoteDetailDao;
import com.admin.dao.vote.VoteRecordDao;
import com.admin.dao.vote.dataobject.VoteActionDO;
import com.admin.dao.vote.dataobject.VoteActionDetailDO;
import com.admin.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.admin.dao.vote.dataobject.VoteActionPO;
import com.admin.dao.vote.dataobject.VoteActionRecordDTO;
import com.admin.dao.vote.dataobject.VoteActionRecordPO;
import com.admin.dao.vote.dataobject.VoteRecordDO;
import com.admin.service.base.ResponseCode;
import com.admin.service.base.ResponseDO;
import com.admin.service.base.ResponsePageQueryDO;
import com.admin.service.vote.VoteService;
import com.admin.utils.CollectionUtil;

@Component("VoteService")
public class VoteImpl implements VoteService {
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private VoteDetailDao voteDetailDao;
	@Autowired
	private VoteRecordDao voteRecordDao;

	@Override
	public ResponsePageQueryDO<List<VoteActionDO>> getVoteActionList(VoteActionPO po) {
		ResponsePageQueryDO<List<VoteActionDO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(voteDao.getVoteActionCount(po.getStatus()));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<VoteActionDO> actionList = voteDao.getVoteActionlist(po);
			response.setDataResult(actionList);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("暂无该类活动");
		}
		return response;
	}
	@Override
	public List<VoteActionDetailDO> getActionDetailList(Long id) {
		List<VoteActionDetailDO> detailList = voteDetailDao.getVoteActionDetail(id);
		voteDao.updateVoteActionNum(id);
		return detailList;
	}
	
	@Override
	public List<VoteActionDetailDO> getActionDetailByRank(Long id) {
		List<VoteActionDetailDO> detailList = voteDetailDao.getVoteActionDetailByRank(id);
		return detailList;
	}
	
	@Override
	public VoteActionDO getAction(Long id) {
		return voteDao.getVoteAction(id);
	}

	@Override
	public List<VoteActionDO> getVoteActionHotList() {
		return voteDao.getVoteActionHotList();
	}


	@Override
	public List<VoteActionDetailDO>selectActionDetailByName(VoteActionDetailSearchDO searchDO) {
		List<VoteActionDetailDO> detailList = voteDetailDao.selectActionDetailByName(searchDO);
		return detailList;
	}

	@Override
	public ResponsePageQueryDO<List<VoteActionRecordDTO>> getVoteActionRecord(VoteActionRecordPO po) {
		ResponsePageQueryDO<List<VoteActionRecordDTO>> response = new ResponsePageQueryDO<>();
		List<VoteActionRecordDTO> recordList = new ArrayList<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		if(po.getType()==1) {
			response.setTotal(voteRecordDao.getUserRecordCount(po.getId()));
		}
		if(po.getType()==2) {
			response.setTotal(voteRecordDao.getActionRecordCount(po.getId()));
		}
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			if(po.getType()==1) {
				recordList = voteRecordDao.getVoteActionRecord(po);
			}
			if(po.getType()==2) {
				recordList = voteRecordDao.getVoteActionRecord(po);
			}
			response.setDataResult(recordList);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("无投票记录");
		}
		return response;
	}
	@Override
	public int insertVoteAction(VoteActionDO actionDO) {
		
		return voteDao.insertVoteAction(actionDO);
	}
	@Override
	public int insertVoteActionDetail(VoteActionDetailDO detailDO) {
		// TODO Auto-generated method stub
		return voteDetailDao.insertVoteActionDetail(detailDO);
	}
	@Override
	public int updateVoteAction(VoteActionDO actionDO) {
		// TODO Auto-generated method stub
		return voteDao.updateVoteAction(actionDO);
	}
	@Override
	public int updateVoteActionDetail(VoteActionDetailDO detailDO) {
		// TODO Auto-generated method stub
		return voteDetailDao.updateVoteActionDetail(detailDO);
	}
	@Override
	public int deleteVoteAction(Long id) {	
		return voteDao.daleteVoteAction(id);
	}
	@Override
	public int deleteVoteActionDetail(Long id) {
		// TODO Auto-generated method stub
		return voteDetailDao.daleteVoteActionDetail(id);
	}
}
