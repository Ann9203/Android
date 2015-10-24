package com.jczb.car.bean;

import java.util.List;

/**
 * SiteUser集合映射
 * 
 * @author 吴利昌
 * @date 2015-8-28下午5:31:34
 */
public class SiteUserVo {
	
	/**查询的结果：true和false*/
	private String result;
	
	/**siteuser集合*/
	private List<SiteUser> SiteUser;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<SiteUser> getSiteUser() {
		return SiteUser;
	}

	public void setSiteUser(List<SiteUser> siteUser) {
		SiteUser = siteUser;
	}
}
