package com.example.day03_news;

import java.util.ArrayList;

import com.itheima.dao.NewsDAO;
import com.itheima.domain.NewsBean;
import com.itheima.view.NewsView;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	/*
	 * 
	 * 
		1.写布局   ok
		2.找到控件   ok 
		3.获取新闻的数据(假数据)，用javaBean封装到list集合中,需要传递给adapter做适配 ;NewsBean:icon新闻图标 ,title标题 ,des描述,url链接    ok
		4.封装一个Adapter继承BaseAdapter，通过构造方法接受新闻数据，实现4个方法：
			getcount: 显示多少个新闻条目？list集合中有多少个Bean对象，就有多少个条目    ok  


			getView:  getview方法需要返回一个view作为条目显示的内容，这个内容是一个复杂的布局，如何写一个复杂的布局返回？？？？？？ 要获取list集合中postion位置的新闻Bean,将新闻数据设置给view布局上的控件；  不ok

				1.模板代码复用convertview
				2.将一个布局文件转换成一个view对象，作为getview的返回值。
				3.找到布局中的那些控件
				4.找到list集合中指定position位置的bean对象 
				5.将bean对象中的新闻数据设置给那些控件


		5.创建一个adapter对象设置给listview做展示。  ok
		6.设置listview条目的点击事件，点击方法中跳转到指定的新闻url查看新闻。？？？？？？？？？   不ok
	 * */
	private Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//1.找到listview控件
		ArrayList<NewsBean>array=NewsDAO.findAll(context);
		NewsView na=new NewsView(context,array);
		ListView lv_news=(ListView)findViewById(R.id.lv_news);
		lv_news.setAdapter(na);
		
		lv_news.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
		// TODO Auto-generated method stub
		NewsBean nb=(NewsBean)parent.getItemAtPosition(position);
		String url=nb.news_url;
		//创建意图
		Intent in=new Intent();
		in.setAction(Intent.ACTION_VIEW);
		in.setData(Uri.parse(url));
		startActivity(in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
