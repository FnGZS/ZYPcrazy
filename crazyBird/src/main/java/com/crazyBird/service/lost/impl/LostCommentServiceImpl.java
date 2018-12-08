package com.crazyBird.service.lost.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.dao.lost.LostArticleReplyDao;
import com.crazyBird.dao.lost.LostArticleTypeDao;
import com.crazyBird.dao.lost.dataobject.LostCommentDO;
import com.crazyBird.dao.lost.dataobject.LostDO;
import com.crazyBird.dao.lost.dataobject.LostReplyDO;
import com.crazyBird.dao.lost.dataobject.LostReplyPO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.lost.LostCommentService;
@Component("LostCommentService")
public class LostCommentServiceImpl implements LostCommentService{

	@Autowired
	private LostArticleReplyDao lostArticleReplyDao;
	
	@Override
	public ResponseDO<LostDO> commentInput(LostCommentDO DO) {
		ResponseDO<LostDO> response=new ResponseDO<>();
		int status=lostArticleReplyDao.replyInsert(DO);
		if(status==1) {
			response.setCode(ResponseCode.SUCCESS);
			response.setMessage("回复成功");
		}else {
			response.setMessage("回复失败");
			response.setCode(ResponseCode.ERROR);
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<LostReplyDO>> getCommentList(LostReplyPO po) {
		ResponsePageQueryDO<List<LostReplyDO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)lostArticleReplyDao.getCommentCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LostReplyDO> dataResult = lostArticleReplyDao.getComment(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}

	@Override
	public ResponsePageQueryDO<List<LostReplyDO>> getCommentItem(LostReplyPO po) {
		ResponsePageQueryDO<List<LostReplyDO>> response = new ResponsePageQueryDO<>();
		response.setPageSize(po.getPageSize());
		response.setTotal((Integer)lostArticleReplyDao.getCommentCount(po));
		if ((response.getTotal() > 0) && (response.getTotalPage() > po.getPageIndex())) {
			List<LostReplyDO> dataResult = lostArticleReplyDao.getComment(po);
			response.setDataResult(dataResult);
		}else {
			response.setMessage("到底了");
		}
		return response;
	}


}
