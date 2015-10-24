package com.jczb.checkpoint.dao;

import java.util.List;


import com.jczb.checkpoint.model.AppUp;

public interface IAppUp {
	public void insert(AppUp appUp);	
	public List<AppUp> getAppDownCongByCondition(String condition);
	public List<AppUp> getUpRecord(String condition);
}