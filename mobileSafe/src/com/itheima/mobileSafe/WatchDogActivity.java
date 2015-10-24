package com.itheima.mobileSafe;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class WatchDogActivity extends Activity {

	@ViewInject(R.id.iv_watchdog_icon)
	private ImageView iv_watchdog_icon;
	@ViewInject(R.id.tv_watchdog_name)
	private TextView tv_watchdog_name;
	@ViewInject(R.id.et_watchdog_password)
	private EditText et_watchdog_password;
	private String packagename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watchdog);
		ViewUtils.inject(this);
		//获取数据
		Intent intent = getIntent();
		packagename = intent.getStringExtra("packagename");
		//设置枷锁功能的应用程序的名称和图片
		PackageManager pm=getPackageManager();
		//获取应用程序
		try {
			ApplicationInfo applicationInfo = pm.getApplicationInfo(packagename, 0);
			Drawable loadIcon = applicationInfo.loadIcon(pm);
			String applicationname = applicationInfo.loadLabel(pm).toString();
			tv_watchdog_name.setText(applicationname);
			iv_watchdog_icon.setImageDrawable(loadIcon);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 点击解锁界面的时候
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			//调回主界面
			Intent intent=new Intent();
			intent.setAction("android.intent.action.MAIN");
			intent.addCategory("android.intent.category.HOME");
			startActivity(intent);
			finish();
			
		}
		return super.onKeyDown(keyCode, event);
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
	/**
	 * 判断密码是否正确，解锁
	 */
	public void unlock(View v){
		String password=et_watchdog_password.getText().toString().trim();
		if(TextUtils.isEmpty(password)){
			Toast.makeText(getApplicationContext(), "请输入解锁密码！", 0).show();
			return;
		}
		if("123".equals(password)){
			//一般以广播的形式进行发送
			Intent intent=new Intent();
			intent.setAction("com.itheima.mobileSafe.Unlock");
			intent.putExtra("packagename", packagename);
			sendBroadcast(intent);
			finish();
			
			
		}else{
			Toast.makeText(getApplicationContext(), "请输入正确的密码！", 0).show();
		}
	}
}
