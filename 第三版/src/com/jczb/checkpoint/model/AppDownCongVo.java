package com.jczb.checkpoint.model;

import java.util.List;

/**
 * 映射RFID表类
 * @author wlc
 * @date 2015-3-31
 */
public class AppDownCongVo {
	private List<AppDownCong> appDownCong;
	private String result;
	public List<AppDownCong> getAppDownCong() {
		return appDownCong;
	}
	public void setAppDownCong(List<AppDownCong> appDownCong) {
		this.appDownCong = appDownCong;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
