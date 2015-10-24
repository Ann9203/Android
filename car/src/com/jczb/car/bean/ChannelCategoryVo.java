package com.jczb.car.bean;

import java.util.List;

/**
 * 获取频道列表映射类，
 * 用于频道和频道列表的解析
 * @author 吴利昌
 * @date 2015-9-1上午8:43:56
 */
@SuppressWarnings("serial")
public class ChannelCategoryVo extends Entity {
	/**
	 * 映射返回结果
	 */
	private String result;
	/**
	 * 频道类型集合
	 */
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
