package com.jczb.checkpoint.model;

import java.util.List;

/**
 * 下载的安标扫描件
 * @author wlc
 * @date 2015-4-8
 */
public class AnbiaoScanningImg {

	private String fileName;
	private String fileType;
	List<AnbiaoScanningImg> downScanning;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public List<AnbiaoScanningImg> getDownScanning() {
		return downScanning;
	}
	public void setDownScanning(List<AnbiaoScanningImg> downScanning) {
		this.downScanning = downScanning;
	}
	
}
