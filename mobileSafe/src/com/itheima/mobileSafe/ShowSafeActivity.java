package com.itheima.mobileSafe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ShowSafeActivity extends Activity {
	private SharedPreferences sp;
	@ViewInject(R.id.tv_showsafe_phonecode)
	private TextView tv_showsafe_num;
	
	@ViewInject(R.id.iv_showsafe_unlock)
	private ImageView iv_showsafe_lock;
	
	@ViewInject(R.id.tv_showsafe_lostfind)
	private TextView tv_showsafe_lostfind;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		sp=getSharedPreferences("config", MODE_PRIVATE);
		if(sp.getBoolean("first", true)){
			Intent intent=new Intent(this,SafeActivity.class);
			startActivity(intent);
			finish();
		}else{
			
			setContentView(R.layout.activity_showsafe);
			ViewUtils.inject(this);
			String num=sp.getString("safenum", "");
			
			tv_showsafe_num.setText(num);
			if(sp.getBoolean("protect", false)){
				iv_showsafe_lock.setImageResource(R.drawable.lock);
				tv_showsafe_lostfind.setText("您开启了防盗模式");
			}else{
				iv_showsafe_lock.setImageResource(R.drawable.unlock);
				tv_showsafe_lostfind.setText("您还没有开启防盗模式");
			}
		}
		
		
	}
	public void reset(View v)
	{
		Intent intent =new Intent(this,SafeActivity.class);
		startActivity(intent);
	}

}
