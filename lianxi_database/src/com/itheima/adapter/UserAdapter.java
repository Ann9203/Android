package com.itheima.adapter;

import java.util.ArrayList;

import com.example.lianxi_database.R;
import com.itheima.domain.UserBean.UserBean;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter {

	private ArrayList<UserBean> array;
	private Context context;
	public UserAdapter(ArrayList<UserBean> array,Context context)
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
		
		View view=null;
		if(convertView!=null)
		{
			view=convertView;
		}else{
			view=View.inflate(context,R.layout.showstudent, null);
			
		}
		TextView tv_name=(TextView) view.findViewById(R.id.tv_name);
		TextView tv_sex=(TextView)view.findViewById(R.id.tv_sex);
		TextView tv_school=(TextView)view.findViewById(R.id.tv_school);
		TextView tv_phone=(TextView)view.findViewById(R.id.tv_phone);
		UserBean user=new UserBean();
		user=array.get(position);
		tv_name.setText(user.name);
		tv_sex.setText(user.sex);
		tv_school.setText(user.shcool);
		tv_phone.setText(user.phone);
		return view;
	}

}
