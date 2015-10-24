package com.example.day03;

import com.dbday03.MySQLite;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private Context context=this;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void transfer(View v)
	{
		MySQLite mysql=new MySQLite(context);
		SQLiteDatabase sq = mysql.getReadableDatabase();
		//用事物
		sq.beginTransaction(); //开启事务
		try{
			
			sq.execSQL("update account set money=money-200 where name='小王' ");
			int i=100/0;
			sq.execSQL("update account set money=money+200 where name='小李'");
			//事物成功
			sq.setTransactionSuccessful();
		}finally{
			//事物不成功就要回滚事务
			sq.endTransaction();
		}
		
	
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
