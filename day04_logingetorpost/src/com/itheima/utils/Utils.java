package com.itheima.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Utils {
	
	//��Ҫ���Ǳ����û���
	//��ȡ�û���
	
	public static boolean saveUserInfo(Context context,String username,String pwd)
	{
		
		SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = spf.edit();
		edit.putString("username", username);
		edit.putString("pwd", pwd);
		return edit.commit();
	}
	
	//��ȡ�û���Ϣ
	public static Map<String,String> getUserInfo(Context context)
	{
		Map<String,String> map=new HashMap<String,String>();
		SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(context);
		String username=spf.getString("username", "");
		String pwd=spf.getString("pwd", "");
		map.put("username", username);
		map.put("pwd",pwd);
		return map;
	}

}
