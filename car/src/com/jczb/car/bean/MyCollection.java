package com.jczb.car.bean;


/**
 * 我的收藏，
 * 用于解析我的收藏接口
 * @author 吴利昌
 * @date 2015-8-31下午4:07:21
 */
@SuppressWarnings("serial")
public class MyCollection extends Entity{
	
	/**我的收藏id*/
	private int uid;
	
	/**标题*/
	private String title;
	
	/**视频地址或图片地址*/
	private String path;
	
	/**频道名称*/
	private String channelName;
	
	/**播放或阅读数*/
	private int browseNumber;
	
	/**是否收藏*/
	private int isCollect;
	
	/**频道类ixng*/
	private int type;


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

	public int getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

	public int getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
}
