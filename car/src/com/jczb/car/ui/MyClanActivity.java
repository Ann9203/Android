package com.jczb.car.ui;

import com.jczb.car.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;



import android.widget.RelativeLayout;



/**
 * 我的部落功能页面
 * @author 丁国华
 * @date 2015-9-6下午6:05:56
 */
public class MyClanActivity extends Activity implements OnClickListener {

	private RelativeLayout rlMyEvaluation;
	private Intent intent;

	/*我的部落的返回按钮 */
	private ImageButton ibMyClan;
	   
	protected void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   //设置加载页面
		   setContentView(R.layout.my_clan);
		   //初始化页面控件
		   initView();
		        
	}

	private void initView() {
		// TODO Auto-generated method stub
		//初始化控件
		ibMyClan=(ImageButton)findViewById(R.id.my_clan_back);
		//初始化点击的监听事件
		ibMyClan.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.my_clan_back:
			this.finish();
			break;
		

		default:
			break;
		}
	}


		 
	
}
