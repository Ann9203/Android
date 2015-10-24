package com.jczb.car.bean;

import java.util.Date;
import java.util.List;

import android.R.integer;

import android.R.integer;

/**
 * 段子内容实体
 * @author 吴利昌
 * @date 2015-8-27下午2:32:57
 */
@SuppressWarnings("serial")
public class Content extends Entity {
	
	/** 内容表标识 */
	private int uid;
	
	/** 显示标题 */
	private String title;
	
	/** 视频或者图片路径 */
	private String path;
	
	/**详情*/
	private String details;
	
	/**频道类型*/
	private String channelType;
	
	/**频道名称*/
	private String channelName;
	
	/**作者*/
	private String author;
	
	/**是否推荐*/
	private String isRecommend;
	
	/**阅读或者播放数量*/
	private int  browseNumber;
	
	/**评论数*/
	private int commentbrowseNumber;
		

	/**赞数*/
	private int praiseNumber;
	
	/**踩数*/
	private int treadNumber;
	
	/**发布时间*/
	private String issueTime;
	
	/**是否收藏*/
	private int isCollect;
	
	/**关联视频*/
	private List<VideoRelation> Content;
	
	//------------------------以下字段在数据库文档中不存在----吴利昌注-----------
//	/** 频道名称，如果是视频节目则在界面显示频道，如果是图文格式则在页面不显示。 */
//	private String channelName;
//	
//	/** 如果是视频则显示观看次数，如果是图文则表示阅读次数 */
//	private int count;
//	
//	/** 评论数量 */
//	private int commentCount;
//	
//	/** 是否收藏 1，已收藏。0，未收藏 */
//	private int isCollet; 
//	
//	/**type:1视频，0图文*/
//	private int type;
	
	//-----------------------以上字段在数据库文档中不存在---吴利昌注---------------------


	

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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public int getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

	public int getCommentbrowseNumber() {
		return commentbrowseNumber;
	}

	public void setCommentbrowseNumber(int commentbrowseNumber) {
		this.commentbrowseNumber = commentbrowseNumber;
	}

	public int getPraiseNumber() {
		return praiseNumber;
	}

	public void setPraiseNumber(int praiseNumber) {
		this.praiseNumber = praiseNumber;
	}

	public int getTreadNumber() {
		return treadNumber;
	}

	public void setTreadNumber(int treadNumber) {
		this.treadNumber = treadNumber;
	}

	public String getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}


	public List<VideoRelation> getContent() {
		return Content;
	}

	public void setContent(List<VideoRelation> content) {
		Content = content;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
	
}
