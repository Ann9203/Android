package com.itheima.adapter;

import java.util.ArrayList;

import com.example.day03_news2.R;
import com.itheima.domain.NewsBean;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<NewsBean> array;
	public NewsAdapter(Context context,ArrayList<NewsBean> array)
	{
		this.context=context;
		this.array=array;
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
		View view=null;
		//1.首先复用模板
		if(convertView!=null)
		{
			view=convertView;
			
		}else{
			//将我们自己所坐的新闻的页面转换为view；
			view=View.inflate(context, R.layout.itheima_news, null);	
		}
		//通过页面获取到每个空间
		ImageView img=(ImageView)view.findViewById(R.id.img_con);
		TextView tv_title=(TextView)view.findViewById(R.id.tv_title);
		TextView tv_des=(TextView)view.findViewById(R.id.tv_des);
		//通过position获取到相应的对应的新闻
		NewsBean nb=array.get(position);
		//给每个空间赋值
		tv_title.setText(nb.title);
		tv_des.setText(nb.des);
		img.setImageDrawable(nb.icon);
		return view;
	}

}
