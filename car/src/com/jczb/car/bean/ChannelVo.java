package com.jczb.car.bean;

import java.util.List;

/**
 * 频道集合，用于Json串的解析
 * @author 吴利昌
 * 2015-8-26下午9:51:37
 */

public class ChannelVo extends Entity{
	public final static int CATALOG_ALL = 1;
	public final static int CATALOG_INTEGRATION = 2;
	public final static int CATALOG_SOFTWARE = 3;
	
	
	/** 服务器返回结果:true或false */
	private String result;
	
	/** 服务器返回的Json串解析为List结合 */
	//TODO:这个Channel名字还不确定，得跟Json保持一致
	private List<Content> Content;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<Content> getContent() {
		return Content;
	}

	public void setContent(List<Content> content) {
		Content = content;
	}

	

	
	
	
//	private int channelsCount;
//	private int pageSize;
//	private List<Channel> channelList = new ArrayList<Channel>();
//	
//	public int getChannelsCount() {
//		return channelsCount;
//	}
//	public void setChannelsCount(int channelsCount) {
//		this.channelsCount = channelsCount;
//	}
//	public int getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//	public List<Channel> getChannelList() {
//		return channelList;
//	}
//	public void setChannelList(List<Channel> channelList) {
//		this.channelList = channelList;
//	}

}
