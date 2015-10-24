package com.jczb.car.bean;

import java.util.List;

/**
 * 内容集合，用于Json串的解析
 * 
 * @author 吴利昌 2015-8-26下午9:45:26
 */
@SuppressWarnings("serial")
public class ContentVo extends Entity {

	/** 服务器返回结果:true或false */
	private String result;

	/** 服务器返回的Json串解析为List结合 */
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

	// private int contentsCount;
	// private int pageSize;
	// private List<Content> contentList = new ArrayList<Content>();
	// public int getContentsCount() {
	// return contentsCount;
	// }
	// public void setContentsCount(int contentsCount) {
	// this.contentsCount = contentsCount;
	// }
	// public int getPageSize() {
	// return pageSize;
	// }
	// public void setPageSize(int pageSize) {
	// this.pageSize = pageSize;
	// }
	// public List<Content> getContentList() {
	// return contentList;
	// }
	// public void setContentList(List<Content> contentList) {
	// this.contentList = contentList;
	// }

}
