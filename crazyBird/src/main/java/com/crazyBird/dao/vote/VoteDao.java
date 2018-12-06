package com.crazyBird.dao.vote;

import java.util.List;

import com.crazyBird.dao.vote.dataobject.VoteActionDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionPO;
import com.crazyBird.dao.vote.dataobject.VoteActionSlideDO;
import com.crazyBird.dao.vote.dataobject.VoteRecordDO;

public interface VoteDao {
	//得到投票活动列表
	List<VoteActionDO> getVoteActionlist(VoteActionPO po);
	
	//得到当前投票活动
	VoteActionDO getVoteAction(Long id);
	
	//得到热门投票活动列表
	List<VoteActionDO> getVoteActionHotList();
	
	//得到当前总票数
	Long getVoteActionSum(Long id);
	
	//增加访问次数
	void updateVoteActionNum(Long id);
	
	//增加当前活动总票数
	int updateVoteActionSum(VoteRecordDO recordDO);	
	
	//得到活动数
	Integer getVoteActionCount(Integer status);
	//检测活动状态
	Integer checkVoteAction(Long id);
	
	//得到特别推荐活动
	List<VoteActionDO> getVoteActionHot();
	
	Integer checkTestType();
	
	

	
}
