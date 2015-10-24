package com.itheima.mobileSafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.itheima.Utils.CallLocationUtils;
import com.itheima.server.BlackNumServer;
import com.itheima.server.CallLocationServer;
import com.itheima.server.WatchDogServer;
import com.itheima.ui.CallLocationStyle;
import com.itheima.ui.SetControl;

public class SettingActivity extends Activity {
	private SetControl sc;
	private SharedPreferences sp ;
	private SetControl sc_callLocation;
	private SetControl sc_blacknum;
	private CallLocationStyle cls_style;
	private CallLocationStyle cls_attribute;
	private SetControl sc_lock;
	final String[] items={"半透明","活力橙","卫士蓝","金属灰","苹果绿"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		 sp = getSharedPreferences("config", MODE_PRIVATE);
		 sc_callLocation=(SetControl) findViewById(R.id.sc_setting_calllocation);
		 sc_blacknum=(SetControl)findViewById(R.id.sc_setting_blacknum);
		 sc_lock=(SetControl) findViewById(R.id.sc_setting_lock);
		update();		
		showSytle();
		setAttribute();
		showBlackNum();
		LockApp();
	}
	/**
	 * 设置是否开启拦截模式
	 */
	private void showBlackNum(){
		
		if(CallLocationUtils.isRunning("com.itheima.server.BlackNumServer", getApplicationContext())){
			sc_blacknum.setChecked(true);
			
		}else{
			sc_blacknum.setChecked(false);
		}
	
		//设置服务提示开启
		sc_blacknum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//定义Intent，获取服务
				Intent intent=new Intent(SettingActivity.this,BlackNumServer.class);
				//点击开启或者是关闭服务
				if(sc_blacknum.isChecked()){
					//关闭服务
					stopService(intent);
					//重置选中的状态
					sc_blacknum.setChecked(false);
				}else{
					//开启服务
					startService(intent);
					sc_blacknum.setChecked(true);
				}
				
			}
		});
	}
	/**
	 * 是否开启枷锁的服务
	 */
	/**
	 * 设置是否开启拦截模式
	 */
	private void LockApp(){
		
		if(CallLocationUtils.isRunning("com.itheima.server.WatchDogServer", getApplicationContext())){
			sc_lock.setChecked(true);
			
		}else{
			sc_lock.setChecked(false);
		}
	
		//设置服务提示开启
		sc_lock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//定义Intent，获取服务
				Intent intent=new Intent(SettingActivity.this,WatchDogServer.class);
				//点击开启或者是关闭服务
				if(sc_lock.isChecked()){
					//关闭服务
					stopService(intent);
					//重置选中的状态
					sc_lock.setChecked(false);
				}else{
					//开启服务
					startService(intent);
					sc_lock.setChecked(true);
				}
				
			}
		});
	}
	
	
	/**
	 * 设置归属框的地理位置
	 */
	private void setAttribute(){
		cls_attribute=(CallLocationStyle) findViewById(R.id.cls_setting_attribute);
		cls_attribute.setDes("设置归属地提示框的显示位置");
		//点击此按钮的时候，跳转到另一个界面
		cls_attribute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SettingActivity.this,AttributeLocalActivity.class);
				startActivity(intent);
			}
		});
		
	}
	/**
	 * 设置提示是否需要更新这个功能
	 * 
	 */
	private void update(){
		//设置提示更新的那一栏
				sc=(SetControl) findViewById(R.id.sc_setting_mycontrol);
				//sc.setTitle("提示更新");
				
				 final Editor edit = sp.edit();
				 if(sp.getBoolean("update", true))
					{
						//sc.setDes("开启更新");
						sc.setChecked(true);
						
						
					}else{
						//sc.setDes("关闭更新");
						sc.setChecked(false);
						
					}
				 //点击控件事件
				
				sc.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(sc.isChecked()){
							//sc.setDes("关闭更新");		
							sc.setChecked(false);
							edit.putBoolean("update", false);					
						}else{
							//sc.setDes("开启更新");
							sc.setChecked(true);					
							edit.putBoolean("update", true);
						}
						edit.commit();
						
					}
				});
	}
	
	/**
	 * 设置是否显示归属地的这个功能
	 */
	private void addressLocation(){
		//获取提示服务是否开启的id
				
				//根据系统中是否存在这个服务来判断是否是需要 开启关闭
				//类名不是路径不要些错误了
		
				if(CallLocationUtils.isRunning("com.itheima.server.CallLocationServer", getApplicationContext())){
					sc_callLocation.setChecked(true);
					
				}else{
					sc_callLocation.setChecked(false);
				}
			
				//设置服务提示开启
				sc_callLocation.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//定义Intent，获取服务
						Intent intent=new Intent(SettingActivity.this,CallLocationServer.class);
						//点击开启或者是关闭服务
						if(sc_callLocation.isChecked()){
							//关闭服务
							stopService(intent);
							//重置选中的状态
							sc_callLocation.setChecked(false);
						}else{
							//开启服务
							startService(intent);
							sc_callLocation.setChecked(true);
						}
						
					}
				});
	}
	
	
	/**
	 * 这个开启服务，和关闭服务，在界面开始的时候就应该调用
	 * onCreate只是被调用一次
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		addressLocation();
		System.out.println(".....");
	}
	
	/**
	 * 更换来电显示的样式
	 * 	
	 */
	public void showSytle(){
		cls_style=(CallLocationStyle) findViewById(R.id.cls_setting_callstyle);
		//设置desTextView的值回显
		String style=items[sp.getInt("whitch", 0)];
		cls_style.setDes(style);
		//设置点击事件
		//点击出现dialog对话框
		cls_style.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();
			}
		});
		
	}
	/**
	 * 定义对话框
	 */
	public void showDialog(){
		AlertDialog.Builder builder=new Builder(this);
		//设置icon
		builder.setIcon(R.drawable.ic_launcher);
		//设置title
		builder.setTitle("请选择样式：");
		
		//设置单选
		builder.setSingleChoiceItems(items, sp.getInt("whitch", 0), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//展示条目
				Editor edit=sp.edit();
				edit.putInt("whitch", which);
				edit.commit();
				cls_style.setDes(items[sp.getInt("whitch", 0)]);
				dialog.dismiss();
				
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
		
	}
	
	
}
