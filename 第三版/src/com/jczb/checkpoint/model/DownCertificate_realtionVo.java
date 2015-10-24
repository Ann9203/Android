package com.jczb.checkpoint.model;

import java.util.List;

/**
 * 证书关联表映射类
 * @author wlc
 * @date 2015-4-8
 */
public class DownCertificate_realtionVo {

	private String result;
	private List<DownCertificate_realtion> downCertificate_realtion;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<DownCertificate_realtion> getDownCertificate_realtion() {
		return downCertificate_realtion;
	}
	public void setDownCertificate_realtion(
			List<DownCertificate_realtion> downCertificate_realtion) {
		this.downCertificate_realtion = downCertificate_realtion;
	}
	
	
}
