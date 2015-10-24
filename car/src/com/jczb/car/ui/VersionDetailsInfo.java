package com.jczb.car.ui;

import com.jczb.car.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class VersionDetailsInfo extends Activity implements OnClickListener {
	/*版本信息的详细页面*/
	private ImageButton ibVersionDetailInfo;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置加载页面
        setContentView(R.layout.version_detailsinfo);
        //初始化页面控件
        initView();
        
	}

	private void initView() {
		// TODO Auto-generated method stub
		//初始化控件
		ibVersionDetailInfo=(ImageButton)findViewById(R.id.version_details_info_back);
		//初始化点击的监听事件
		ibVersionDetailInfo.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.version_details_info_back:
			this.finish();
			break;
		

		default:
			break;
		}
		
	}

}
