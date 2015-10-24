package com.itheima.view;

import java.util.ArrayList;

import com.example.day03_news.R;
import com.itheima.dao.NewsDAO;
import com.itheima.domain.NewsBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsView extends BaseAdapter {
	
	private ArrayList<NewsBean> array;
	private Context context;
	public NewsView(Context context,ArrayList<NewsBean> array)
	{
		this.array=array;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return array.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return array.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * 
		 * 4.封装一个Adapter继承BaseAdapter，通过构造方法接受新闻数据，实现4个方法：
			getcount: 显示多少个新闻条目？list集合中有多少个Bean对象，就有多少个条目    ok  


			getView:  getview方法需要返回一个view作为条目显示的内容，这个内容是一个复杂的布局，如何写一个复杂的布局返回？？？？？？ 要获取list集合中postion位置的新闻Bean,将新闻数据设置给view布局上的控件；  不ok

				1.模板代码复用convertview
				2.将一个布局文件转换成一个view对象，作为getview的返回值。
				3.找到布局中的那些控件
				4.找到list集合中指定position位置的bean对象 
				5.将bean对象中的新闻数据设置给那些控件
		 * */
		View view=null;
		if(convertView!=null)
		{
			view=convertView;
		}else{
			
			view=View.inflate(context, R.layout.heimanews, null);
			
		}
		ImageView im_con=(ImageView)view.findViewById(R.id.iv_icon);
		TextView tv_title=(TextView)view.findViewById(R.id.tv_title);
		TextView tv_des=(TextView)view.findViewById(R.id.tv_des);
		NewsBean nb=array.get(position);
		tv_title.setText(nb.title);
		tv_des.setText(nb.des);
		im_con.setImageDrawable(nb.icon);
		
		return view;
	}

}
