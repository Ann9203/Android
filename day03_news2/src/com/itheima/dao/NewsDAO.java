package com.itheima.dao;

import java.util.ArrayList;
import android.content.Context;

import com.example.day03_news2.R;
import com.itheima.domain.NewsBean;

public class NewsDAO {
	//��ȡ���ŵ�ʵ��
	public static ArrayList<NewsBean> findAll(Context context)
	{
		
		ArrayList<NewsBean>array=new ArrayList<NewsBean>();
		for(int i=0;i<100;i++)
		{
			NewsBean nb=new NewsBean();
			nb.title="���췴����˹ʤ��70����";
			nb.des="�����������ǣ�����Ա���У���������ë�����������ǻӻ��ֱۣ����Ǿ��񲻼ӣ�����������ڿͣ��о���������Ӱ�ӵ�";
			nb.url="http://www.baidu.com";
			nb.icon=context.getResources().getDrawable(R.drawable.ic_launcher);
			array.add(nb);
			
			NewsBean nb1=new NewsBean();
			nb1.title="����д�棬д�滧��д�棬д�滧��д�棬д�滧��д�棬д�滧��д�棬д��";
			nb1.des="�羰�ú�ѽ�羰�ú�ѽ�羰�ú�ѽ�羰�ú�ѽ�羰�ú�ѽ�羰�ú�ѽ�羰�ú�ѽ";
			nb1.url="http://www.baidu.com";
			nb1.icon=context.getResources().getDrawable(R.drawable.java4);
			array.add(nb1);
			
			
			NewsBean nb2=new NewsBean();
			nb2.title="����Ԥ������Ԥ������Ԥ������Ԥ������Ԥ������Ԥ������Ԥ��";
			nb2.des="��������������������������������������������������������������������������������";
			nb2.url="http://www.baidu.com";
			nb2.icon=context.getResources().getDrawable(R.drawable.tupian1);
			array.add(nb2);
			
			NewsBean nb3=new NewsBean();
			nb3.title="�й��������й��������й��������й��������й��������й��������й�������";
			nb3.des="��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ��ѽ";
			nb3.url="http://www.baidu.com";
			nb3.icon=context.getResources().getDrawable(R.drawable.xh1);
			array.add(nb3);
		}
		
		
		
		
		
		
		
		return array;
	}

}
