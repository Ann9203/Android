/**
 * 说明：首页频道种类
 * 作者：丁国华
 * 时间：2015年8月20日 14:21:37
 * 
 */

package com.jczb.car.bean;

import java.util.Date;

/**
 * 频道类型
 * 
 * @author 吴利昌 
 * 2015-8-27上午9:16:25
 */
@SuppressWarnings("serial")
public class ChannelCategory extends Entity {

	/** 频道类型表标识 */
	private int uid;
	
	/** 频道种类的名称 */
	private String channelName;
	
	/** 显示顺序 */
	private int showOrder;
	
	/** 添加时间 */
	private Date channelTime;


	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public Date getChannelTime() {
		return channelTime;
	}

	public void setChannelTime(Date channelTime) {
		this.channelTime = channelTime;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
