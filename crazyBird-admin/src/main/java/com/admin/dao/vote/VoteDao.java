package com.admin.dao.vote;

import java.util.List;

import com.admin.dao.vote.dataobject.VoteActionDO;
import com.admin.dao.vote.dataobject.VoteActionDetailDO;
import com.admin.dao.vote.dataobject.VoteActionHotDTO;
import com.admin.dao.vote.dataobject.VoteActionPO;
import com.admin.dao.vote.dataobject.VoteRecordDO;

public interface VoteDao {
	//得到投票活动列表
	List<VoteActionDO> getVoteActionlist(VoteActionPO po);
	
	//得到当前投票活动
	VoteActionDO getVoteAction(Long id);
	
	//得到热门投票活动列表
	List<VoteActionHotDTO> getVoteActionHotList();
	
	//得到当前总票数
	Long getVoteActionSum(Long id);
	
	//增加访问次数
	void updateVoteActionNum(Long id);
	
	//增加当前活动总票数
	int updateVoteActionSum(VoteRecordDO recordDO);	
	
	//得到活动数
	Integer getVoteActionCount(Integer status);
	
	//新增活动
	int insertVoteAction(VoteActionDO actionDO);
	//新增活动
	int updateVoteAction(VoteActionDO actionDO);
	
	int daleteVoteAction(Long id);

	
}
