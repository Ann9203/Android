package com.database.day03_database;


import com.dao.day03_database.Utils;
import com.domain.UserBean.UserBean;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=this;
		findViewById(R.id.bt_add).setOnClickListener(this);
		findViewById(R.id.bt_delete).setOnClickListener(this);
		findViewById(R.id.bt_query).setOnClickListener(this);
		findViewById(R.id.bt_update).setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.bt_add:
				UserBean ub=new UserBean();
				ub.name="小李";
				ub.phone="110";
				Utils.add(context,ub);
				ub.name="小王";
				ub.phone="120";
				Utils.add(context,ub);
				break;
			case R.id.bt_delete:
				Utils.delete(context, "小李");
				break;
			case R.id.bt_query:
				
				Utils.query(context, "小王");
				break;
			case R.id.bt_update:
				UserBean ub1=new UserBean();
				ub1.name="小蓝";
				ub1.phone="120";
				Utils.update(context,ub1 );
				break;
			default:
				break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




	

}
