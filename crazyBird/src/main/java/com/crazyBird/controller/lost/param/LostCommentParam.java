package com.crazyBird.controller.lost.param;

public class LostCommentParam {
	private Integer commentId;
	private String comment;
	private Integer articleId;
	private Integer replyId;
	private Integer replyedId;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public Integer getReplyedId() {
		return replyedId;
	}
	public void setReplyedId(Integer replyedId) {
		this.replyedId = replyedId;
	}
	
}
