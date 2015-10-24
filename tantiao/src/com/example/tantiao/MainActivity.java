package com.example.tantiao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	
	private TanTiao tt;
	private Button bt_color;
	//设置颜色
	private int[]color=new int[]{
			Color.parseColor("#ff0000"),Color.parseColor("#00ff00"),
	        Color.parseColor("#0000ff"),Color.parseColor("#ffff00")
	};
	private Canvas canvas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tt=(TanTiao) findViewById(R.id.tt);
		bt_color=(Button) findViewById(R.id.bt);
		canvas=new Canvas();
		bt_color.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//数组循环
				int in=(int)( Math.random()*4);
				tt.setColor(in);
			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
