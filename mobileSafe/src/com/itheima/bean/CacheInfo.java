package com.itheima.bean;



public class CacheInfo {
	private String packageName;
	private String cacheSize;

	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(String cacheSize) {
		this.cacheSize = cacheSize;}
	public CacheInfo(String packageName, String cacheSize) {
		super();
		this.packageName = packageName;
		this.cacheSize = cacheSize;
	}
	
	
}
