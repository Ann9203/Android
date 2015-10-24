package com.jczb.checkpoint.dao;

import java.util.List;

import com.jczb.checkpoint.model.DownCertificate;
import com.jczb.checkpoint.model.User;

public interface IDownCertificate {
	
	public void insert(DownCertificate downCertificate);
	
	public void delete(int downCertificate);
	
	public void delete(String condiction);
	
	public void update(DownCertificate downCertificate);
	

}
