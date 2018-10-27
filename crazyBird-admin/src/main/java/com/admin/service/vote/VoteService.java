package com.admin.service.vote;

import java.util.List;


import com.admin.dao.vote.dataobject.VoteActionDO;
import com.admin.dao.vote.dataobject.VoteActionDetailDO;
import com.admin.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.admin.dao.vote.dataobject.VoteActionPO;
import com.admin.dao.vote.dataobject.VoteActionRecordDTO;
import com.admin.dao.vote.dataobject.VoteActionRecordPO;
import com.admin.dao.vote.dataobject.VoteRecordDO;
import com.admin.service.base.ResponseDO;
import com.admin.service.base.ResponsePageQueryDO;

public interface VoteService {
	public ResponsePageQueryDO<List<VoteActionDO>> getVoteActionList(VoteActionPO po);
	public List<VoteActionDetailDO> getActionDetailList(Long id);
	public VoteActionDO getAction(Long id);
	public List<VoteActionDO> getVoteActionHotList();
	public List<VoteActionDetailDO> selectActionDetailByName(VoteActionDetailSearchDO searchDO);
	public List<VoteActionDetailDO> getActionDetailByRank(Long id);
	public ResponsePageQueryDO<List<VoteActionRecordDTO>> getVoteActionRecord(VoteActionRecordPO po);
	
}
