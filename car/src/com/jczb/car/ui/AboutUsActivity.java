package com.jczb.car.ui;

import com.jczb.car.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutUsActivity extends Activity implements OnClickListener {
	
	/*关于我们的返回按钮 */
	private ImageButton ibAboutUs;
	private TextView tvDeclare;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置加载页面
        setContentView(R.layout.about_us);
        //初始化页面控件
        initView();
        
	}

	private void initView() {
		// TODO Auto-generated method stub
		//初始化控件
		ibAboutUs=(ImageButton)findViewById(R.id.about_us_back);
		tvDeclare=(TextView)findViewById(R.id.about_us_declare);
		//初始化点击的监听事件
		ibAboutUs.setOnClickListener(this);
		tvDeclare.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.about_us_back:
			this.finish();
			break;
		case R.id.about_us_declare:
			Intent intentDeclare = new Intent(this,DeclareActivity.class);
		    startActivity(intentDeclare);
			break;
		

		default:
			break;
		}
		
	}

}
