package com.crazyBird.dao.lost;

import java.util.List;

import com.crazyBird.dao.lost.dataobject.LostCommentDO;
import com.crazyBird.dao.lost.dataobject.LostReplyDO;
import com.crazyBird.dao.lost.dataobject.LostReplyPO;

public abstract interface LostArticleReplyDao {

	int replyInsert(LostCommentDO dO);

	Integer getCommentCount(LostReplyPO po);

	List<LostReplyDO> getComment(LostReplyPO po);

}
