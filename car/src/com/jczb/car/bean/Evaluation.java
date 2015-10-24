package com.jczb.car.bean;

import java.util.Date;

/**
 * 评论实体
 * @author 吴利昌
 * 2015-8-27下午3:18:54
 */
@SuppressWarnings("serial")
public class Evaluation extends Entity{

	/**主键*/
	private int uid; 
	
	/**文章内容Id*/
	private int essayContentId;
	
	/**评论人Id*/
	private int commentPersonId;
	
	/**评论内容*/
	private int commentContent;
	
	/**是否是回复：0代表是，1代表否*/
	private int isReplay;
	
	/**被回复的评论所属人Id*/
	private int beenCommentPersonId;
	
	/**评论时间*/
	private Date commentTime;

	/**赞数*/
	private int praiseNumber;
	
	/**是否为推荐评论：0代表是，1代表否*/
	private int isRecommendComment;
	

	public int getEssayContentId() {
		return essayContentId;
	}

	public void setEssayContentId(int essayContentId) {
		this.essayContentId = essayContentId;
	}

	public int getCommentPersonId() {
		return commentPersonId;
	}

	public void setCommentPersonId(int commentPersonId) {
		this.commentPersonId = commentPersonId;
	}

	public int getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(int commentContent) {
		this.commentContent = commentContent;
	}

	public int getIsReplay() {
		return isReplay;
	}

	public void setIsReplay(int isReplay) {
		this.isReplay = isReplay;
	}

	public int getBeenCommentPersonId() {
		return beenCommentPersonId;
	}

	public void setBeenCommentPersonId(int beenCommentPersonId) {
		this.beenCommentPersonId = beenCommentPersonId;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public int getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(int praiseNumber) {
		this.praiseNumber = praiseNumber;
	}

	public int getIsRecommendComment() {
		return isRecommendComment;
	}

	public void setIsRecommendComment(int isRecommendComment) {
		this.isRecommendComment = isRecommendComment;
	}



	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}


	
	
}
