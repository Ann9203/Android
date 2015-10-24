package com.jczb.car.bean;

import java.util.List;

/**
 * 我的收藏映射类，
 * 用于解析我的收藏，作为我的收藏实体类的集合
 * @author 吴利昌
 * @date 2015-8-31下午4:12:39
 */
@SuppressWarnings("serial")
public class MyCollectionVo extends Entity {
	
	/**返回的收藏记录结果：true或者false*/
	private String result;
	
	/**我的收藏集合*/
	private List<MyCollection> Content;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<MyCollection> getContent() {
		return Content;
	}

	public void setContent(List<MyCollection> content) {
		Content = content;
	}
	
}
