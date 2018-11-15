package com.admin.service.vote;

import java.util.List;


import com.admin.dao.vote.dataobject.VoteActionDO;
import com.admin.dao.vote.dataobject.VoteActionDetailDO;
import com.admin.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.admin.dao.vote.dataobject.VoteActionHotDTO;
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
	List<VoteActionHotDTO> getVoteActionHotList();
	List<VoteActionDetailDO> selectActionDetailByName(VoteActionDetailSearchDO searchDO);
	List<VoteActionDetailDO> getActionDetailByRank(Long id);
	ResponsePageQueryDO<List<VoteActionRecordDTO>> getVoteActionRecord(VoteActionRecordPO po);
	//新增活动
	int insertVoteAction(VoteActionDO actionDO);

	//新增活动详情
	int insertVoteActionDetail(VoteActionDetailDO detailDO);
	

	int updateVoteAction(VoteActionDO actionDO);

	int updateVoteActionDetail(VoteActionDetailDO detailDO);
	
	int deleteVoteAction(Long id);
	
	int deleteVoteActionDetail(Long id);
	
}
