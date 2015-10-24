package com.jczb.checkpoint.manager;

import java.util.List;

import android.content.Context;

import com.jczb.checkpoint.R.color;
import com.jczb.checkpoint.dao.IAppDownCong;
import com.jczb.checkpoint.dao.impl.AppDownCongImpl;
import com.jczb.checkpoint.db.AppDownCong.FIELDS;
import com.jczb.checkpoint.model.AppDownCong;

public class DownCongManager {

	private IAppDownCong iAppDownCongDao;
	
	
	//构造函数,实例化接口实现类
		public DownCongManager(Context context){
			iAppDownCongDao = new AppDownCongImpl(context);
		}
		
		
		/**
		 * 根据EPC返回查询结果
		 * @return 用户实体
		 */
		public List<AppDownCong> getAppDownCongByCondition(String epcString){
			String condition = FIELDS.EPCCODE + "='" + epcString +"'" ;
			List<AppDownCong> AppDownCongs = iAppDownCongDao.getAppDownCongByCondition(condition);
			return AppDownCongs;
		}
		
}
