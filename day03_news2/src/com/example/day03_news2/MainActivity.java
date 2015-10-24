package com.example.day03_news2;

import java.util.ArrayList;

import com.itheima.adapter.NewsAdapter;
import com.itheima.dao.NewsDAO;
import com.itheima.domain.NewsBean;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity  implements OnItemClickListener{

	private Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<NewsBean>array=NewsDAO.findAll(context);
		NewsAdapter na=new NewsAdapter(context,array);
		//获取ListView的Id值
		ListView lv=(ListView)findViewById(R.id.lv_news);
		lv.setAdapter(na);
		//设置listView的点击事件
		lv.setOnItemClickListener(this);
	
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
		// TODO Auto-generated method stub
		//根据所指向的position的位置，得到newsBean对象
		NewsBean nb=(NewsBean)parent.getItemAtPosition(position);
		//设置意图
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(nb.url));
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
