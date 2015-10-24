package com.itheima.mobileSafe;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.itheima.Utils.NetPreMethod;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SetSafe4Activity extends NetPreMethod{
	@ViewInject(R.id.ch_setsafe4_start)
	private CheckBox ch_setSafe4;
	@ViewInject(R.id.tv_setsafe4_protect)
	private TextView tv_setSafe4;
	private Editor edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setsafe3);
		ViewUtils.inject(this);
		 edit=sp.edit();
		if(sp.getBoolean("protect", false)){
			tv_setSafe4.setText("开启防盗模式");
			ch_setSafe4.setChecked(true);
		}else{
			tv_setSafe4.setText("不开启防盗模式");
			ch_setSafe4.setChecked(false);
		}
		ch_setSafe4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			//isChecked是点击完之后的状态
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					tv_setSafe4.setText("开启防盗模式");
					ch_setSafe4.setChecked(true);
					edit.putBoolean("protect", true);
				}else{
					tv_setSafe4.setText("关闭防盗模式");
					ch_setSafe4.setChecked(false);
					edit.putBoolean("protect", false);
				}
				edit.commit();
			}
		});
	}

	@Override
	public void preMethod() {
		// TODO Auto-generated method stub
		Intent intent =new Intent(this,SetSafe3Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.set_enter_pretanim,R.anim.set_exit_pretanim);
	}

	@Override
	public void nextMethod() {
		// TODO Auto-generated method stub
		Editor edit=sp.edit();
		edit.putBoolean("first", false);
		edit.commit();
		Intent intent =new Intent(this,ShowSafeActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.set_enter_nextanim, R.anim.set_exit_nextanim);
	}

}
