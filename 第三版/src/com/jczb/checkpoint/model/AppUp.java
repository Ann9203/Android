package com.jczb.checkpoint.model;

import java.io.Serializable;

import android.R.integer;

public class AppUp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 431464628856895478L;
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
	 * EPC编码
	 */
	private String EPRCode;
	/**
	 * TID编码
	 */
	private String TIDCode;
	/**
	 * 煤矿名称
	 */
	private String MeKuangiName;
	/**
	 * 安检编号
	 */
	private String AnJianCode;
	/**
	 * 工作人员
	 */
	private String JobName;
	/**
	 * 抽查日期
	 */
	private String CheckDate;
	/**
	 * 备注
	 */
	private String ExceRemark;
	/**
	 * 安标编号
	 */
	private String AnBiaoCode;
	
	/**
	 * 抽查结果
	 */
	private String CheckResult;
	private int UserID;
	
	public String getCheckResult() {
		return CheckResult;
	}
	public void setCheckResult(String checkResult) {
		CheckResult = checkResult;
	}
	/*
	 * 上传状态
	 * */
	private String state;
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getEPRCode() {
		return EPRCode;
	}
	public void setEPRCode(String ePRCode) {
		EPRCode = ePRCode;
	}
	public String getTIDCode() {
		return TIDCode;
	}
	public void setTIDCode(String tIDCode) {
		TIDCode = tIDCode;
	}
	public String getMeKuangiName() {
		return MeKuangiName;
	}
	public void setMeKuangiName(String meKuangiName) {
		MeKuangiName = meKuangiName;
	}
	public String getAnJianCode() {
		return AnJianCode;
	}
	public void setAnJianCode(String anJianCode) {
		AnJianCode = anJianCode;
	}
	public String getJobName() {
		return JobName;
	}
	public void setJobName(String jobName) {
		JobName = jobName;
	}
	public String getCheckDate() {
		return CheckDate;
	}
	public void setCheckDate(String checkDate) {
		CheckDate = checkDate;
	}
	public String getExceRemark() {
		return ExceRemark;
	}
	public void setExceRemark(String exceRemark) {
		ExceRemark = exceRemark;
	}
	public String getAnBiaoCode() {
		return AnBiaoCode;
	}
	public void setAnBiaoCode(String anBiaoCode) {
		AnBiaoCode = anBiaoCode;
	}	
}
