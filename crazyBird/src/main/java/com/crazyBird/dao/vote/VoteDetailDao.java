package com.crazyBird.dao.vote;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailSearchDO;

public interface VoteDetailDao {
	//获取活动详情列表
	List<VoteActionDetailDO> getVoteActionDetail(Long actionId);
	
	//获取活动详情列表按票数排序
	List<VoteActionDetailDO> getVoteActionDetailByRank(Long actionId);
	
	//增加个人票数
	int BatchupdateVoteActionDetail(@Param("list") List<Long> ids,@Param ("actionId") Long actionId);
	
	//搜索
	
	List<VoteActionDetailDO> selectActionDetailByName(VoteActionDetailSearchDO searchDO);
	
	
}
