package com.jczb.checkpoint.model;

import java.io.Serializable;

/**
 * 安标关联表实体
 * @author wlc
 * @date 2015-4-1
 */
public class AppAnBiaoRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1584790483102855452L;
	/**
	 * ID
	 */
	private int iD;
	/**
	 * Name
	 */
	private String Name;
	/**
	 * 发放日期
	 */
	private String beginDate;
	/**
	 * 主安标证书ID
	 */
	private int MainAnBiaoID;
	/**
	 * 关联安标Code，下载下来的时候没有
	 */
	private String ViceAnBiaoID;
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
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
	public String getViceAnBiaoID() {
		return ViceAnBiaoID;
	}
	public void setViceAnBiaoID(String viceAnBiaoID) {
		ViceAnBiaoID = viceAnBiaoID;
	}
	
	
	
	
}
