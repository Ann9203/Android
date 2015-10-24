package com.jczb.car.ui;

import java.util.HashMap;
import java.util.Map;



import com.jczb.car.AppContext;
import com.jczb.car.R;
import com.jczb.car.bean.SiteUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VersionInfoActivity extends Activity implements OnClickListener {
	
	LinearLayout versionDetails1,versionDetails2;
	/*版本信息第一条的名称*/
	private TextView tvVersionName1;
	/*版本信息第一条的时间*/
	private TextView tvVsionTime1;
	/*版本信息第二条的名称*/
	private TextView tvVersionName2;
	/*版本信息第二条的时间*/
	private TextView tvVrsionTime2;
	/*版本信息的返回按钮 */
	private ImageButton ibVersionInfo;
	/*版本信息访问服务器端时传参用的集合*/
	private Map<String, Object> params = new HashMap<String, Object>();
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_info);
        
        //初始化控件
        initView();
        
        
        findViewById();
        
        /*跳转到版本信息的详细页面*/
        versionDetails1.setOnClickListener(this);
        versionDetails2.setOnClickListener(this);
        
	}
    private void initView() {
		// TODO Auto-generated method stub
    	//初始化控件
    	tvVersionName1=(TextView)findViewById(R.id.version_details_name_one);
    	tvVsionTime1=(TextView) findViewById(R.id.version_details_time_one);
    	tvVersionName2=(TextView) findViewById(R.id.version_details_name_two);
    	tvVrsionTime2=(TextView) findViewById(R.id.version_details_time_two);
    	ibVersionInfo=(ImageButton) findViewById(R.id.version_info_back);
    	
    	//初始化点击的监听事件
    	ibVersionInfo.setOnClickListener(this);
    	
    	
    	
		
	}
	/*根据ID找到控件*/
	private void findViewById() {
		// TODO Auto-generated method stub
		versionDetails1=(LinearLayout) findViewById(R.id.version_details_one);
		versionDetails2=(LinearLayout) findViewById(R.id.version_details_two);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.version_details_one:
			Intent intentversioninfo = new Intent(this,VersionDetailsInfo.class);
		    startActivity(intentversioninfo);
			break;
		case R.id.version_details_two:
			Intent intentversioninfo1 = new Intent(this,VersionDetailsInfo.class);
		    startActivity(intentversioninfo1);
			break;
		case R.id.version_info_back:
			this.finish();
			break;
		
		default:
			break;
		
	}

	}
	
	
}
