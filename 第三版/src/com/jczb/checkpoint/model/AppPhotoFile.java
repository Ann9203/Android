package com.jczb.checkpoint.model;

import java.io.Serializable;

public class AppPhotoFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 25305622382939878L;
	/**
	 * ID
	 */
	private int ID;
	/**
	 * Name
	 */
	private String Name;
	/**
	 * 发放日期
	 */
	private String beginDate;
	/**
	 * 关联表ID
	 */
	private int MainID;
	/**
	 * 类型
	 */
	private String MainStype;
	/**
	 * 二进制图片
	 */
	private byte[] Image;
	
	/**
	 * 图片名称
	 */
	private String FileName;
	
	
	/**
	 * 图片后缀类型
	 */
	private String FileType;
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public int getMainID() {
		return MainID;
	}
	public void setMainID(int mainID) {
		MainID = mainID;
	}
	public String getMainStype() {
		return MainStype;
	}
	public void setMainStype(String mainStype) {
		MainStype = mainStype;
	}
	public byte[] getImage() {
		return Image;
	}
	public void setImage(byte[] image) {
		Image = image;
	}
	
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	
	
	
	
}
