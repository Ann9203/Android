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
		1.д����   ok
		2.�ҵ��ؼ�   ok 
		3.��ȡ���ŵ�����(������)����javaBean��װ��list������,��Ҫ���ݸ�adapter������ ;NewsBean:icon����ͼ�� ,title���� ,des����,url����    ok
		4.��װһ��Adapter�̳�BaseAdapter��ͨ�����췽�������������ݣ�ʵ��4��������
			getcount: ��ʾ���ٸ�������Ŀ��list�������ж��ٸ�Bean���󣬾��ж��ٸ���Ŀ    ok  


			getView:  getview������Ҫ����һ��view��Ϊ��Ŀ��ʾ�����ݣ����������һ�����ӵĲ��֣����дһ�����ӵĲ��ַ��أ����������� Ҫ��ȡlist������postionλ�õ�����Bean,�������������ø�view�����ϵĿؼ���  ��ok

				1.ģ����븴��convertview
				2.��һ�������ļ�ת����һ��view������Ϊgetview�ķ���ֵ��
				3.�ҵ������е���Щ�ؼ�
				4.�ҵ�list������ָ��positionλ�õ�bean���� 
				5.��bean�����е������������ø���Щ�ؼ�


		5.����һ��adapter�������ø�listview��չʾ��  ok
		6.����listview��Ŀ�ĵ���¼��������������ת��ָ��������url�鿴���š�������������������   ��ok
	 * */
	private Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//1.�ҵ�listview�ؼ�
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
		//������ͼ
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
