package com.itheima.rebackdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContentActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview);
		ListView lv_data = (ListView) findViewById(R.id.lv_data);
		final String[] objects =new String[] { "÷£≤©", "¿Ó¿ˆ", "–°∫Ï", "¿Ó—©" };
		System.out.println(objects.toString());
 		lv_data.setAdapter(new ArrayAdapter<String>(this,R.layout.item_data,
				R.id.tv_item, objects));

		lv_data.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("contant", objects[position]);
				setResult(100, intent);
				finish();
			}
		});

	}
}
