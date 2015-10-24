package com.jczb.checkpoint.manager;

import android.content.Context;

import com.jczb.checkpoint.dao.IInitDb;
import com.jczb.checkpoint.dao.impl.InitDbImpl;

/**
 * 初始化数据库管理类
 * @author wlc
 * @date 2015-3-24
 */
public class InitDbManager {

	private IInitDb dao;
	//private Context context;
	
	public void Init(Context context){
		dao = new InitDbImpl(context);
	}
	
}
