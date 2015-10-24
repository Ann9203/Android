package com.jczb.car.bean;

import java.util.List;

/**
 * 我的评论实体，
 * 用于对我的评论进行解析
 * 
 * @author 吴利昌
 * @date 2015-8-31下午3:39:11
 */
@SuppressWarnings("serial")
public class MyComment extends Entity {

	/**频道内容标识*/
	private int uid;
	
	/**标题*/
	private String title;
	
	/**频道名称*/
	private String path;

	/**播放或阅读数*/
	private int browseNumber;
	
	/**评论数*/
	private int commentCount;
	
	/**是否收藏*/
	private int isCollect;
	
	/**频道类型*/
	private int channelType;
	
	/**评论内容实体集合*/
	private List<MyEvaluation> comment;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public List<MyEvaluation> getComment() {
		return comment;
	}

	public void setComment(List<MyEvaluation> comment) {
		this.comment = comment;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
}
