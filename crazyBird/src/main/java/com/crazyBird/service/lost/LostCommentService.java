package com.crazyBird.service.lost;

import java.util.List;

import com.crazyBird.dao.lost.dataobject.LostCommentDO;
import com.crazyBird.dao.lost.dataobject.LostDO;
import com.crazyBird.dao.lost.dataobject.LostReplyDO;
import com.crazyBird.dao.lost.dataobject.LostReplyPO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public abstract interface LostCommentService {

	ResponseDO<LostDO> commentInput(LostCommentDO dO);

	ResponsePageQueryDO<List<LostReplyDO>> getCommentList(LostReplyPO po);

	ResponsePageQueryDO<List<LostReplyDO>> getCommentItem(LostReplyPO po);



}
