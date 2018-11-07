package com.crazyBird.service.vote;

import java.util.List;

import com.crazyBird.dao.vote.dataobject.VoteActionCustomDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.crazyBird.dao.vote.dataobject.VoteActionPO;
import com.crazyBird.dao.vote.dataobject.VoteActionRecordDTO;
import com.crazyBird.dao.vote.dataobject.VoteActionRecordPO;
import com.crazyBird.dao.vote.dataobject.VoteActionSlideDO;
import com.crazyBird.dao.vote.dataobject.VoteRecordDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public interface VoteService {
	public ResponsePageQueryDO<List<VoteActionDO>> getVoteActionList(VoteActionPO po);
	public List<VoteActionDetailDO> getActionDetailList(Long id);
	public VoteActionDO getAction(Long id);
	public List<VoteActionDO> getVoteActionHotList();
	public List<VoteActionDO> getVoteActionHot();
	public ResponseDO<VoteRecordDO> checkVoteRecord(VoteRecordDO recordDO);
	public ResponseDO createVoteRecord(VoteRecordDO recordDO);
	public Integer checkActionStatus(Long id);
	public List<VoteActionDetailDO> selectActionDetailByName(VoteActionDetailSearchDO searchDO);
	public List<VoteActionDetailDO> getActionDetailByRank(Long id);
	public ResponsePageQueryDO<List<VoteActionRecordDTO>> getVoteActionRecord(VoteActionRecordPO po);
	public List<VoteActionSlideDO> getVoteActionSlide();
	VoteActionCustomDO selectActionDetailById(Long id);
}
