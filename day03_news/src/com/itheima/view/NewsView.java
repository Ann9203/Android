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
		 * 4.��װһ��Adapter�̳�BaseAdapter��ͨ�����췽�������������ݣ�ʵ��4��������
			getcount: ��ʾ���ٸ�������Ŀ��list�������ж��ٸ�Bean���󣬾��ж��ٸ���Ŀ    ok  


			getView:  getview������Ҫ����һ��view��Ϊ��Ŀ��ʾ�����ݣ����������һ�����ӵĲ��֣����дһ�����ӵĲ��ַ��أ����������� Ҫ��ȡlist������postionλ�õ�����Bean,�������������ø�view�����ϵĿؼ���  ��ok

				1.ģ����븴��convertview
				2.��һ�������ļ�ת����һ��view������Ϊgetview�ķ���ֵ��
				3.�ҵ������е���Щ�ؼ�
				4.�ҵ�list������ָ��positionλ�õ�bean���� 
				5.��bean�����е������������ø���Щ�ؼ�
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
