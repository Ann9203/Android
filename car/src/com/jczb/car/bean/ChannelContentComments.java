package com.jczb.car.bean;

/**
 * 用于解析ChannelContentComment中的comment属性
 * 
 * @author 吴利昌
 * @date 2015-8-31下午5:07:27
 */
public class ChannelContentComments {

	/**
	 * 头像
	 */
	private String headimage;
	/**
	 * 用户
	 */
	private String username;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论时间
	 */
	private String time;
	/**
	 * 回复的用户
	 */
	private String commentPerson;
	/**
	 * 是否推荐
	 */
	private int isRecommendComment;
	/**
	 * 是否是回复
	 */
	private int isReplay;
	/**
	 * 赞数
	 */
	private int praiseNumber;

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCommentPerson() {
		return commentPerson;
	}

	public void setCommentPerson(String commentPerson) {
		this.commentPerson = commentPerson;
	}

	public int getIsRecommendComment() {
		return isRecommendComment;
	}

	public void setIsRecommendComment(int isRecommendComment) {
		this.isRecommendComment = isRecommendComment;
	}

	public int getIsReplay() {
		return isReplay;
	}

	public void setIsReplay(int isReplay) {
		this.isReplay = isReplay;
	}

	public int getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(int praiseNumber) {
		this.praiseNumber = praiseNumber;
	}

}
