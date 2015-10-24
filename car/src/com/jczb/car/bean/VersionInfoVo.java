package com.jczb.car.bean;

import java.util.List;

/**
 * 版本信息映射类，
 * 用于解析服务器返回的版本信息Json串
 * @author 吴利昌
 * @date 2015-8-31下午5:50:30
 */
@SuppressWarnings("serial")
public class VersionInfoVo extends Entity{
	
	/**
	 * 解析时返回的版本信息结果：true或者false
	 */
	private String result;
	
	/**
	 * 解析时返回的版本信息集合
	 */
	private List<VersionInfo> version;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<VersionInfo> getVersion() {
		return version;
	}

	public void setVersion(List<VersionInfo> version) {
		this.version = version;
	}
}
