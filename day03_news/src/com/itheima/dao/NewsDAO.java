package com.itheima.dao;

import java.util.ArrayList;

import android.content.Context;

import com.example.day03_news.R;
import com.itheima.domain.NewsBean;

public class NewsDAO {
	//��ȡ���е�����

	public static ArrayList<NewsBean> findAll(Context context)
	{
		ArrayList<NewsBean> array=new ArrayList<NewsBean>();
		for(int i=0;i<100;i++)
		{
			NewsBean nb=new NewsBean();
			nb.title="���췴����˹ʤ��70����";
			nb.des="�����������ǣ�����Ա���У���������ë�����������ǻӻ��ֱۣ����Ǿ��񲻼ӣ�����������ڿͣ��о���������Ӱ�ӵ�";
			nb.news_url="http://www.baidu.com";
			nb.icon=context.getResources().getDrawable(R.drawable.ic_launcher);
			array.add(nb);
			
			NewsBean nb1=new NewsBean();
			nb1.title="��ǧ�Ǵ��ִ��ֻ�ǧ�Ǵ��ִ��ֻ�ǧ�Ǵ��ִ��ֻ�ǧ�Ǵ��ִ���";
			nb1.des="�����������ǣ�����Ա���У���������ë�����������ǻӻ��ֱۣ����Ǿ��񲻼ӣ�����������ڿͣ��о���������Ӱ�ӵ�";
			nb1.news_url="http://www.baidu.com";
			nb1.icon=context.getResources().getDrawable(R.drawable.java4);
			array.add(nb1);
			
			NewsBean nb2=new NewsBean();
			nb2.title="������������������������������������������������������������";
			nb2.des="�����������ǣ�����Ա���У���������ë�����������ǻӻ��ֱۣ����Ǿ��񲻼ӣ�����������ڿͣ��о���������Ӱ�ӵ�";
			nb2.news_url="http://www.baidu.com";
			nb2.icon=context.getResources().getDrawable(R.drawable.tupian1);
			array.add(nb2);
		}
		
		
		
		return array;
	}
	

}
