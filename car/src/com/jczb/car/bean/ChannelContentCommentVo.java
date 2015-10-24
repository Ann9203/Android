package com.jczb.car.bean;

import java.util.List;

/**
 * 频道内容评论映射类，
 * 作为频道内容评论实体的集合
 * @author 吴利昌
 * @date 2015-8-31下午5:03:52
 */
@SuppressWarnings("serial")
public class ChannelContentCommentVo extends Entity{
	
	/**返回频道内容评论结果：true或者false*/
	private String result;
	
	/**频道评论内容集合*/
	private List<ChannelContentComment> Content;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<ChannelContentComment> getContent() {
		return Content;
	}

	public void setContent(List<ChannelContentComment> content) {
		Content = content;
	}
	
	
}
