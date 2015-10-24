package com.example.day03_tiger;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡidֵ
		ListView lv_tager1=(ListView)findViewById(R.id.lv_tager1);
		ListView lv_tager2=(ListView)findViewById(R.id.lv_tager2);
		ListView lv_tager3=(ListView)findViewById(R.id.lv_tager3);
		Teger teger=new Teger();
		lv_tager1.setAdapter(teger);
		lv_tager2.setAdapter(teger);
		lv_tager3.setAdapter(teger);
		
		
	}
	//����һ���ڲ��࣬�̳�BaseAdaper
	class Teger extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 500;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			//���ȷ���convertview
			TextView tv=null;
			if(convertView!=null)
			{
				tv=(TextView) convertView;
			}else{
				tv=new TextView(context);
			}
			//���������
			Random ran=new Random();
			int i=ran.nextInt(100);
			if(i<20)
			{
				tv.setText("��");
				tv.setTextColor(Color.GRAY);
				//tv.setTextSize(15);
			}else if(i<40)
			{
				tv.setText("��");
				tv.setTextColor(Color.RED);
				//tv.setTextSize(15);
			}else if(i<60)
			{
				tv.setText("��");
				tv.setTextColor(Color.GREEN);
				//tv.setTextSize(15);
			}else if(i<80)
			{
				tv.setTextColor(Color.YELLOW);
				tv.setText("��");
				//tv.setTextSize(15);
			}else{
				tv.setText("��");
				tv.setTextColor(Color.BLACK);
				//tv.setTextSize(15)
			}
			
			
			return tv;
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
