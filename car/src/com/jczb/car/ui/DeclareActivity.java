package com.jczb.car.ui;

import com.jczb.car.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class DeclareActivity extends Activity implements OnClickListener {
	/*声明需要用到的空间*/
	private ImageButton ivDeclare;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置加载页面
        setContentView(R.layout.declare);
        //初始化页面控件
        initView();
	}
       
	

	private void initView() {
		// TODO Auto-generated method stub
		//初始化控件
		ivDeclare=(ImageButton)findViewById(R.id.declare_back);
		//初始化点击的监听事件
		ivDeclare.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.declare_back:
			this.finish();
			break;
		

		default:
			break;
		}
		
	}

}
