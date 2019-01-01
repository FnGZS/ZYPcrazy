package com.crazyBird.dao.vote;

import java.util.List;

import com.crazyBird.dao.vote.dataobject.VoteActionSlideDO;

public interface VoteSlideDao {
	//	
	//得到投票幻灯片
	List<VoteActionSlideDO> getVoteActionSlide();
}
