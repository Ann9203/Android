package com.itheima.rebackdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click1(View v)
	{
		Intent intent=new Intent(this,ContentActivity.class);
		startActivityForResult(intent, 100);
		
		//startActivity(intent);
	}
	
	public void click2(View v)
	{
		Intent intent =new Intent(this,Msg.class);
		startActivityForResult(intent, 200);
		//startActivity(intent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100)
		{
			String contant=data.getStringExtra("contant");	
			EditText et_contant=(EditText)findViewById(R.id.tv_contant);
			System.out.println(contant);
			et_contant.setText(et_contant.getText().toString().trim()+contant);
			
		}else if(requestCode==200)
		{
			String msg=data.getStringExtra("msg");
			EditText et_msg=(EditText)findViewById(R.id.msg);
			et_msg.setText(msg);
			
		}
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
