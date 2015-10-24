package com.jczb.checkpoint.model;

import java.io.Serializable;
import java.util.List;

public class DownCertificate_realtion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<DownCertificate_realtion> downCertificate_realtion;
	
	
	private String ID;
	private String Name;
	private String beginDate;
	/**
	 * 主安标证书
	 */
	private int MainAnBiaoID;
	/**
	 * 关联安标证书编码
	 */
	private String ViceAnBiaoCode;
	public List<DownCertificate_realtion> getDownCertificate_realtion() {
		return downCertificate_realtion;
	}
	public void setDownCertificate_realtion(
			List<DownCertificate_realtion> downCertificate_realtion) {
		this.downCertificate_realtion = downCertificate_realtion;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
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
	public int getMainAnBiaoID() {
		return MainAnBiaoID;
	}
	public void setMainAnBiaoID(int mainAnBiaoID) {
		MainAnBiaoID = mainAnBiaoID;
	}
	public String getViceAnBiaoCode() {
		return ViceAnBiaoCode;
	}
	public void setViceAnBiaoCode(String viceAnBiaoCode) {
		ViceAnBiaoCode = viceAnBiaoCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
