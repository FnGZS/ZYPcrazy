package com.crazyBird.dao.lost.dataobject;

import com.crazyBird.service.base.PageQueryDO;

public class LostReplyPO extends PageQueryDO{
	private Integer articleId;
	private Integer commentId;
	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

}
