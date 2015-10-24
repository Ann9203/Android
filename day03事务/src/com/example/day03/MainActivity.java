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
		//������
		sq.beginTransaction(); //��������
		try{
			
			sq.execSQL("update account set money=money-200 where name='С��' ");
			int i=100/0;
			sq.execSQL("update account set money=money+200 where name='С��'");
			//����ɹ�
			sq.setTransactionSuccessful();
		}finally{
			//���ﲻ�ɹ���Ҫ�ع�����
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
