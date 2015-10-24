package com.jczb.car.bean;

/**
 * 搜索内容实体
 * @author 丁国华
 * 2015年9月9日 13:32:30
 */
@SuppressWarnings("serial")
public class SearchContent extends Entity {
	
	/**主键标识*/
	private int uid;
	
	/**阅读量和播放量*/
	private int browseNumber;
	
	/**频道名称*/
	private String channelName;
	
	/**频道类型是图文还是视频*/
	private int channelType;
	
	
	/**是否收藏*/
	private int isCollect;
	
	/**存放路径*/
	private String path;
	
	/**标题*/
	private String title;
	
	/**评论量*/
	private  String commentCount;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public int getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	
	
	
	

	

}
