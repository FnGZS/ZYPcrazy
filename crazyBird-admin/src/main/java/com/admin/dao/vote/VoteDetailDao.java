package com.admin.dao.vote;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.admin.dao.vote.dataobject.VoteActionDetailDO;
import com.admin.dao.vote.dataobject.VoteActionDetailSearchDO;

public interface VoteDetailDao {
	//获取活动详情列表
	List<VoteActionDetailDO> getVoteActionDetail(@Param ("actionId")Long actionId);
	
	//获取活动详情列表按票数排序
	List<VoteActionDetailDO> getVoteActionDetailByRank(Long actionId);
	
	//增加个人票数
	int BatchupdateVoteActionDetail(@Param("list") List<Long> ids,@Param ("actionId") Long actionId);
	
	//搜索
	
	List<VoteActionDetailDO> selectActionDetailByName(VoteActionDetailSearchDO searchDO);

	//新增活动详情
	int insertVoteActionDetail(VoteActionDetailDO detailDO);

	int updateVoteActionDetail(VoteActionDetailDO detailDO);
	
	int deleteVoteActionDetail(Long id);
	
}
