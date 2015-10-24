package com.itheima.register;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//ע�����ݹ۲���
		ContentResolver resolver = getContentResolver();
		resolver.registerContentObserver(Uri.parse("content://sms"),//�۲��Ǹ�uri��������
				true, new MyOberser(new Handler()));
	}
	class MyOberser extends ContentObserver{

		public MyOberser(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}
		//���ݿⷢ���˸ı���Ҫ����֪ͨ���˷�������
	
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
			System.out.println("���ŷ�����֪ͨ");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
