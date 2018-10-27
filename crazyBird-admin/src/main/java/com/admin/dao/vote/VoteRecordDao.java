package com.admin.dao.vote;

import java.util.List;

import com.admin.dao.vote.dataobject.VoteActionRecordDTO;
import com.admin.dao.vote.dataobject.VoteActionRecordPO;
import com.admin.dao.vote.dataobject.VoteRecordDO;

public interface VoteRecordDao {
		//获取用户的投票记录
		Integer getUserRecordCount(Long studentId);
		//获取活动的投票记录
		Integer getActionRecordCount(Long actionId);
		
		List<VoteActionRecordDTO> getVoteActionUserRecord(VoteActionRecordPO po);
		
		List<VoteActionRecordDTO> getVoteActionRecord(VoteActionRecordPO po);
}
