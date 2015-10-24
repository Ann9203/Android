package com.jczb.car.bean;

import java.util.List;

/**
 * 取得频道列表信息
 * 用于Json串的解析
 * 
 * @author 吴利昌 2015-8-26下午9:20:42
 */
@SuppressWarnings("serial")
public class ChannelCategoryList extends Entity {

	/** 服务器返回结果:true或false */
	private String result;

	/** 服务器返回的Json串解析为List结合 */
	private List<ChannelCategory> ChannelCategory;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<ChannelCategory> getChannelCategory() {
		return ChannelCategory;
	}

	public void setChannelCategory(List<ChannelCategory> channelCategory) {
		ChannelCategory = channelCategory;
	}

}
