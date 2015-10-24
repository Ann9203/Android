package com.jczb.checkpoint.model;

import java.io.Serializable;

public class AppDownCong implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2730055093443994242L;
	private  int ID;
	private String Name;
	private String beginDate;
	private String EPCCode;
	private String TIDCode;
	private String PutDate;
	private String PutEnterprise;
	private String PutProduceName;
	private String PutProdeceModel;
	private String AnBiaoCode;
	private int State;
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
	public String getEPCCode() {
		return EPCCode;
	}
	public void setEPCCode(String ePCCode) {
		EPCCode = ePCCode;
	}
	public String getTIDCode() {
		return TIDCode;
	}
	public void setTIDCode(String tIDCode) {
		TIDCode = tIDCode;
	}
	public String getPutDate() {
		return PutDate;
	}
	public void setPutDate(String putDate) {
		PutDate = putDate;
	}
	public String getPutEnterprise() {
		return PutEnterprise;
	}
	public void setPutEnterprise(String putEnterprise) {
		PutEnterprise = putEnterprise;
	}
	public String getPutProduceName() {
		return PutProduceName;
	}
	public void setPutProduceName(String putProduceName) {
		PutProduceName = putProduceName;
	}
	public String getPutProdeceModel() {
		return PutProdeceModel;
	}
	public void setPutProdeceModel(String putProdeceModel) {
		PutProdeceModel = putProdeceModel;
	}
	public String getAnBiaoCode() {
		return AnBiaoCode;
	}
	public void setAnBiaoCode(String anBiaoCode) {
		AnBiaoCode = anBiaoCode;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	
	
	
	
}
