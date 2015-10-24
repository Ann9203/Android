package com.example.day04_news;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ithei.adapter.newsAdapter;
import com.ithei.domain.newsbean.NewsBean;
import com.ithei.utils.Utils;

public class MainActivity extends Activity implements OnItemClickListener {
	Handler handler = new Handler() {
		
		public void handleMessage(android.os.Message msg){
			switch (msg.what) {
			case LocalNewsList:
				ArrayList<NewsBean> array_local = (ArrayList<NewsBean>) msg.obj;
				if (array_local != null && array_local.size() > 0) {
					newsAdapter newslocal_adapter = new newsAdapter(mContext,
							array_local);
					lv_news.setAdapter(newslocal_adapter);
				}
				break;
			case ServerNewsList:
				ArrayList<NewsBean> array_servlet = (ArrayList<NewsBean>) msg.obj;
				if (array_servlet != null && array_servlet.size() > 0) {
					newsAdapter newslocal_adapter = new newsAdapter(mContext,
							array_servlet);
					lv_news.setAdapter(newslocal_adapter);
				}
			}

		};
	};

	private ListView lv_news;
	private Context mContext = this;
	private final static int LocalNewsList = 1;
	private final static int ServerNewsList = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取ListView控件

		lv_news = (ListView) findViewById(R.id.lv_news);
		lv_news.setOnItemClickListener(this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 从本地获取数据
				ArrayList<NewsBean> array_local = Utils
						.getNewsForLocal(mContext);
				// 定义message
				Message msg = Message.obtain();
				msg.what = LocalNewsList;
				msg.obj = array_local;
				handler.sendMessage(msg);

				// 从服务端获取
				ArrayList<NewsBean> array_servlet = Utils
						.getNewsForServlet(mContext);
				Message msg2 = Message.obtain();
				msg2.what = ServerNewsList;
				msg2.obj = array_servlet;
				handler.sendMessage(msg2);

			}
		}).start();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		NewsBean nb=(NewsBean) parent.getItemAtPosition(position);
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(nb.news_url));
		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
