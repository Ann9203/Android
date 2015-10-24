package com.jczb.car.bean;

import java.util.List;

@SuppressWarnings("serial")
public class MyCommentVo extends Entity{
	
	/**返回我的评论的结果：true或者false*/
	private String result;
	
	/**我的评论集合*/
	private List<MyComment> Content;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<MyComment> getContent() {
		return Content;
	}

	public void setContent(List<MyComment> content) {
		Content = content;
	}
	
}
