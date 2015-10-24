package com.itheima.mobileSafe;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.Engien.ContactEngien;
import com.itheima.Utils.MyAysncTask;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ContactActivity extends Activity {
	@ViewInject(R.id.pb_contact_progress)
	private ProgressBar pb;
	@ViewInject(R.id.lv_contact_lists)
	private ListView lv_contact_list;
	private ArrayList<HashMap<String,String>> list;
//	private Handler handler=new Handler(){
//		public void handleMessage(android.os.Message msg) {
//			lv_contact_list.setAdapter(new MyAdapter());
//			pb.setVisibility(View.INVISIBLE);
//		};
//	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		ViewUtils.inject(this);
		
		//在没加载完数据的时候，滚动条先滚动
		//pb.setVisibility(View.VISIBLE);
		
		new MyAysncTask() {
			
			@Override
			public void preExecute() {
				// TODO Auto-generated method stub
				pb.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void nextExecute() {
				// TODO Auto-generated method stub
				lv_contact_list.setAdapter(new MyAdapter());
				pb.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void doInExecute() {
				// TODO Auto-generated method stub
				list= (ArrayList<HashMap<String, String>>) ContactEngien.getContact(ContactActivity.this);
				//handler.sendEmptyMessage(0);
			}
		}.execute();
//		new Thread(){
//			public void run() {
//				list= (ArrayList<HashMap<String, String>>) ContactEngien.getContact(ContactActivity.this);
//				handler.sendEmptyMessage(0);
//			};
//		}.start();
		lv_contact_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				HashMap<String, String> hashMap = list.get(position);
				//获取到触摸到的Item的值，然后呢。。
				//然后创建Intent，然后把值装载intent中，带到另一个界面
				Intent intent =new Intent(ContactActivity.this,SetSafe3Activity.class);
				intent.putExtra("phone", hashMap.get("phone"));
				setResult(RESULT_OK, intent);
				//移除界面
				finish();
			}
		});
		
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view=View.inflate(ContactActivity.this, R.layout.activity_list_contact, null);
			TextView tv_name=(TextView) view.findViewById(R.id.tv_list_contact_name);
			TextView tv_num=(TextView)view.findViewById(R.id.tv_list_contact_num);
			tv_name.setText(list.get(position).get("name"));
			tv_num.setText(list.get(position).get("phone"));
			return view;
		}
		
	}

}
