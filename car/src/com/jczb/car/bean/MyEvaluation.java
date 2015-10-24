package com.jczb.car.bean;

/**
 * 用于解析我的评论中的comment属性
 * @author 吴利昌
 * @date 2015-8-31下午4:32:24
 */
public class MyEvaluation {
	/**评论Id*/
	private String uid;
	/**评论内容*/
	private String content;
	
	/**评论时间*/
	private String time;

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


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
