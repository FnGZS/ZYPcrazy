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
	ResponsePageQueryDO<List<VoteActionDO>> getVoteActionList(VoteActionPO po);
	List<VoteActionDetailDO> getActionDetailList(Long id);
	VoteActionDO getAction(Long id);
	List<VoteActionDO> getVoteActionHotList();
	List<VoteActionDetailDO> selectActionDetailByName(VoteActionDetailSearchDO searchDO);
	List<VoteActionDetailDO> getActionDetailByRank(Long id);
	ResponsePageQueryDO<List<VoteActionRecordDTO>> getVoteActionRecord(VoteActionRecordPO po);
 
	
}
