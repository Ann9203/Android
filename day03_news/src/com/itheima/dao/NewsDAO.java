package com.itheima.dao;

import java.util.ArrayList;

import android.content.Context;

import com.example.day03_news.R;
import com.itheima.domain.NewsBean;

public class NewsDAO {
	//获取所有的新闻

	public static ArrayList<NewsBean> findAll(Context context)
	{
		ArrayList<NewsBean> array=new ArrayList<NewsBean>();
		for(int i=0;i<100;i++)
		{
			NewsBean nb=new NewsBean();
			nb.title="欢庆反法西斯胜利70周年";
			nb.des="对面走来的是，程序员队列，手里拿走毛巾，正在向我们挥挥手臂，他们精神不加，由于最近反黑客，研究新软件，加班加点";
			nb.news_url="http://www.baidu.com";
			nb.icon=context.getResources().getDrawable(R.drawable.ic_launcher);
			array.add(nb);
			
			NewsBean nb1=new NewsBean();
			nb1.title="华千骨大结局大结局华千骨大结局大结局华千骨大结局大结局华千骨大结局大结局";
			nb1.des="对面走来的是，程序员队列，手里拿走毛巾，正在向我们挥挥手臂，他们精神不加，由于最近反黑客，研究新软件，加班加点";
			nb1.news_url="http://www.baidu.com";
			nb1.icon=context.getResources().getDrawable(R.drawable.java4);
			array.add(nb1);
			
			NewsBean nb2=new NewsBean();
			nb2.title="啦啦啦啦啦啦啦啦啦啦啦啦啦啦阿拉啦啦啦啦啦啦啦啦啦啦啦啦啦啦";
			nb2.des="对面走来的是，程序员队列，手里拿走毛巾，正在向我们挥挥手臂，他们精神不加，由于最近反黑客，研究新软件，加班加点";
			nb2.news_url="http://www.baidu.com";
			nb2.icon=context.getResources().getDrawable(R.drawable.tupian1);
			array.add(nb2);
		}
		
		
		
		return array;
	}
	

}
