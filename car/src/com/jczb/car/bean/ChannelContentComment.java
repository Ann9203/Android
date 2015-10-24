package com.jczb.car.bean;

import java.util.List;

/**
 * 频道内容评论， 用于解析频道内容评论接口
 * 
 * @author 吴利昌
 * @date 2015-8-31下午5:01:58
 */
@SuppressWarnings("serial")
public class ChannelContentComment extends Entity {

	/**
	 * 频道内容Id
	 */
	private int uid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 视频地址或图片地址
	 */
	private String path;
	/**
	 * 频道类型
	 */
	private String channelName;
	/**
	 * 播放或阅读数量
	 */
	private int count;
	/**
	 * 评论数
	 */
	private int commentCount;
	/**
	 * 是否收藏
	 */
	private int isCollect;
	/**
	 * 频道类型
	 */
	private int channelType;
	/**
	 * 评论
	 */
	private List<ChannelContentComments> comment;

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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public List<ChannelContentComments> getComment() {
		return comment;
	}

	public void setComment(List<ChannelContentComments> comment) {
		this.comment = comment;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
