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
		//1.���ȸ���ģ��
		if(convertView!=null)
		{
			view=convertView;
			
		}else{
			//�������Լ����������ŵ�ҳ��ת��Ϊview��
			view=View.inflate(context, R.layout.itheima_news, null);	
		}
		//ͨ��ҳ���ȡ��ÿ���ռ�
		ImageView img=(ImageView)view.findViewById(R.id.img_con);
		TextView tv_title=(TextView)view.findViewById(R.id.tv_title);
		TextView tv_des=(TextView)view.findViewById(R.id.tv_des);
		//ͨ��position��ȡ����Ӧ�Ķ�Ӧ������
		NewsBean nb=array.get(position);
		//��ÿ���ռ丳ֵ
		tv_title.setText(nb.title);
		tv_des.setText(nb.des);
		img.setImageDrawable(nb.icon);
		return view;
	}

}
