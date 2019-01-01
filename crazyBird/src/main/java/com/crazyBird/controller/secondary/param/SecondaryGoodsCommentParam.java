package com.crazyBird.controller.secondary.param;

public class SecondaryGoodsCommentParam {
	private Long id;
	private Long goodsId;
	private Long userId;
	private String content;
	private Long replyedId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getReplyedId() {
		return replyedId;
	}
	public void setReplyedId(Long replyedId) {
		this.replyedId = replyedId;
	}

	
	
	
	
}
