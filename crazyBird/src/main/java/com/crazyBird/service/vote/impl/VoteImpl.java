package com.crazyBird.service.vote.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crazyBird.dao.vote.VoteDao;
import com.crazyBird.dao.vote.VoteDetailDao;
import com.crazyBird.dao.vote.VoteRecordDao;
import com.crazyBird.dao.vote.VoteSlideDao;
import com.crazyBird.dao.vote.dataobject.VoteActionCustomDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.crazyBird.dao.vote.dataobject.VoteActionPO;
import com.crazyBird.dao.vote.dataobject.VoteActionRecordDTO;
import com.crazyBird.dao.vote.dataobject.VoteActionRecordPO;
import com.crazyBird.dao.vote.dataobject.VoteActionSlideDO;
import com.crazyBird.dao.vote.dataobject.VoteRecordDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.vote.VoteService;
import com.crazyBird.utils.CollectionUtil;

@Component("VoteService")
public class VoteImpl implements VoteService {
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private VoteDetailDao voteDetailDao;
	@Autowired
	private VoteRecordDao voteRecordDao;
	@Autowired
	private VoteSlideDao voteSlideDao;

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
	public ResponseDO<VoteRecordDO> checkVoteRecord(VoteRecordDO recordDO) {
		ResponseDO<VoteRecordDO> response= new ResponseDO<>();
		response.setDataResult(voteRecordDao.checkVoteRecord(recordDO));
		return response;
	}

	@Override
	public ResponseDO createVoteRecord(VoteRecordDO recordDO) {
		ResponseDO response = new ResponseDO<>();
		List<Long> ids = new ArrayList<>();
		if (recordDO.getDetail().indexOf(",") != -1) {
			String[] tags = recordDO.getDetail().split(",");
			for (String tag : tags) {
				if (StringUtils.isNotBlank(tag)) {
					ids.add(Long.parseLong(tag));
				}
			}
		}
		if (recordDO.getDetail().indexOf(",") == -1) {
			ids.add(Long.parseLong(recordDO.getDetail()));
		}
		if (ids.size() != recordDO.getSum() || CollectionUtil.isEmpty(ids)) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("投票异常");
		} else {
			int count = voteDetailDao.BatchupdateVoteActionDetail(ids,recordDO.getActionId());
			if(count<=0) {
				response.setCode(ResponseCode.ERROR);
				response.setMessage("网络繁忙请重新投票");
				return response;
			}
			int flag=voteRecordDao.insertVoteRecord(recordDO);
			if(flag<=0) {
				response.setCode(ResponseCode.ERROR);
				response.setMessage("投票异常");
				return response;
			}
			voteDao.updateVoteActionSum(recordDO);
			response.setMessage("投票成功");
		}
		return response;
	}

	@Override
	public List<VoteActionDetailDO>selectActionDetailByName(VoteActionDetailSearchDO searchDO) {
		List<VoteActionDetailDO> detailList = voteDetailDao.selectActionDetailByName(searchDO);
		return detailList;
	}

	@Override
	public ResponsePageQueryDO<List<VoteActionRecordDTO>> getVoteActionRecord(VoteActionRecordPO po) {
		ResponsePageQueryDO<List<VoteActionRecordDTO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(voteRecordDao.getRecordCount(po.getStudentId()));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<VoteActionRecordDTO> recordList = voteRecordDao.getVoteActionRecord(po);
			response.setDataResult(recordList);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("无投票记录");
		}
		return response;
	}
	@Override
	public Integer checkActionStatus(Long id) {
		return voteDao.checkVoteAction(id);
	}
	@Override
	public List<VoteActionDO> getVoteActionHot() {
		return voteDao.getVoteActionHot();
	}
	@Override
	public List<VoteActionSlideDO> getVoteActionSlide() {
		// TODO Auto-generated method stub
		return voteSlideDao.getVoteActionSlide();
	}
	@Override
	public VoteActionCustomDO selectActionDetailById(Long id) {
		// TODO Auto-generated method stub
		return voteDetailDao.selectActionDetailById(id);
	}
}
