package com.jczb.checkpoint.model;

import java.util.List;

/**
 * 解析Json
 * @author 星尘
 *
 */
public class DownCertificateVo {
	String result;
	List<DownCertificate> downCertificate;
	
	public List<DownCertificate> getDownCertificate() {
		return downCertificate;
	}
	public void setDownCertificate(List<DownCertificate> downCertificate) {
		this.downCertificate = downCertificate;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}


	
	
}
