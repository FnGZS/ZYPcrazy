package com.crazyBird.dao.secondary.dataobject;

public class SecondaryGoodsCommentDO {
	private Long id;
	private Long goodsId;
	private Long replyId;
	private String content;
	private Long replyedId;
	private Long commentsId;
	
	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	public Long getReplyedId() {
		return replyedId;
	}
	public void setReplyedId(Long replyedId) {
		this.replyedId = replyedId;
	}

	public Long getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(Long commentsId) {
		this.commentsId = commentsId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
