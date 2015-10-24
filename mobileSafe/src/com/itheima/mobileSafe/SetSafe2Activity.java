package com.itheima.mobileSafe;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.itheima.Utils.NetPreMethod;
import com.itheima.ui.SetControl;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SetSafe2Activity extends NetPreMethod {
	private Editor edit;
	@ViewInject(R.id.sc_setsafe2_mycontrol)	
	private SetControl sc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setsafe2);
		ViewUtils.inject(this);
	 edit=sp.edit();
		//判断数据中是否存在这个上锁的状态
		if(TextUtils.isEmpty((sp.getString("sim", "")))){
			sc.setChecked(false);
		}else{
			sc.setChecked(true);
		}
		
		
		sc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//如果这个时候sc是点击的状态，那么在点击一次的话就是关闭的状态
				if(sc.isChecked()){
					sc.setChecked(false);
					edit.putString("sim", "");
				}else{
					//获取本地sim卡的唯一标示
					//先获取电话管理器
					TelephonyManager telephony=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
					//获取sim的唯一标识
					String simSerialNumber = telephony.getSimSerialNumber();
					sc.setChecked(true);
					edit.putString("sim", simSerialNumber);
				}
				edit.commit();
			}
		});
	}
	
	@Override
	public void preMethod() {
		// TODO Auto-generated method stub
		Intent intent =new Intent(this,SafeActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.set_enter_pretanim,R.anim.set_exit_pretanim);
		
	}

	@Override
	public void nextMethod() {
		// TODO Auto-generated method stub
		Intent intent =new Intent(this,SetSafe3Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.set_enter_nextanim, R.anim.set_exit_nextanim);
	}

}
