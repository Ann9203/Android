package com.example.day03_listview;

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
public class MainActivity extends Activity  {

	private   Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv_show= (ListView) findViewById(R.id.lv_show);
		lv_show.setAdapter(new listview());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	class listview extends BaseAdapter
	{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 15;
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
				TextView tv=null;
				
				if(convertView!=null)
				{
					tv=(TextView) convertView;
				}else{
					tv=new TextView(context);
				}
				tv.setText("hao");
				tv.setTextColor(Color.RED);
				tv.setTextSize(30);
				
				return tv;
			}

	}
}



