package com.jczb.checkpoint.manager;

import android.content.Context;

import com.jczb.checkpoint.dao.impl.DownCertificateImpl;
import com.jczb.checkpoint.model.DownCertificate;


/**
 * 下载主表 业务逻辑类
 * @author wlc
 * @date 2015-3-30
 */
public class ScanManager {

	//声明接口
	private DownCertificateImpl downCertificateDao;
	
	
	//构造函数,实例化接口实现类
	public ScanManager(Context context){
		downCertificateDao = new DownCertificateImpl(context);
	}
	
	
	/**
	 * 向Down表中插入一条记录
	 */
	public void Insert(DownCertificate downInfo){
		downCertificateDao.insert(downInfo);
	}
	
}
