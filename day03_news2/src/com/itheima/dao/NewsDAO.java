package com.itheima.dao;

import java.util.ArrayList;
import android.content.Context;

import com.example.day03_news2.R;
import com.itheima.domain.NewsBean;

public class NewsDAO {
	//获取新闻的实例
	public static ArrayList<NewsBean> findAll(Context context)
	{
		
		ArrayList<NewsBean>array=new ArrayList<NewsBean>();
		for(int i=0;i<100;i++)
		{
			NewsBean nb=new NewsBean();
			nb.title="欢庆反法西斯胜利70周年";
			nb.des="对面走来的是，程序员队列，手里拿走毛巾，正在向我们挥挥手臂，他们精神不加，由于最近反黑客，研究新软件，加班加点";
			nb.url="http://www.baidu.com";
			nb.icon=context.getResources().getDrawable(R.drawable.ic_launcher);
			array.add(nb);
			
			NewsBean nb1=new NewsBean();
			nb1.title="户外写真，写真户外写真，写真户外写真，写真户外写真，写真户外写真，写真";
			nb1.des="风景好好呀风景好好呀风景好好呀风景好好呀风景好好呀风景好好呀风景好好呀";
			nb1.url="http://www.baidu.com";
			nb1.icon=context.getResources().getDrawable(R.drawable.java4);
			array.add(nb1);
			
			
			NewsBean nb2=new NewsBean();
			nb2.title="天气预报天气预报天气预报天气预报天气预报天气预报天气预报";
			nb2.des="下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨下雨";
			nb2.url="http://www.baidu.com";
			nb2.icon=context.getResources().getDrawable(R.drawable.tupian1);
			array.add(nb2);
			
			NewsBean nb3=new NewsBean();
			nb3.title="中国好声音中国好声音中国好声音中国好声音中国好声音中国好声音中国好声音";
			nb3.des="好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀好呀";
			nb3.url="http://www.baidu.com";
			nb3.icon=context.getResources().getDrawable(R.drawable.xh1);
			array.add(nb3);
		}
		
		
		
		
		
		
		
		return array;
	}

}
