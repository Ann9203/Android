package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilesafe_2.R;

public class MyGridViewAdapter extends BaseAdapter {

	private Context context;
	public MyGridViewAdapter(Context context){
		this.context=context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		int[] images={R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app,
				R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
				R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings};
		String[] dess={"手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理",
				"高级工具", "设置中心" };
		view=View.inflate(context,R.layout.activity_actionutil, null);
		//获取控件
		ImageView iv_icon=(ImageView) view.findViewById(R.id.iv_actionutil_icon);
		TextView tv_des=(TextView)view.findViewById(R.id.tv_actionutil_des);
		iv_icon.setImageResource(images[position]);
		tv_des.setText(dess[position]);	
		return view;
	}

}
