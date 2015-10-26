package com.itheima.downlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {

    private PopupWindow popupWindow;
	private ListView listView;
	private List<String> database;
	private EditText et_num;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_num = (EditText) findViewById(R.id.et_nun);       
    }
    public void select(View v){
    	showPopu();
    	
    }
    private void showPopu(){
    	iteam();
    	//设置popuWindow的内容，宽高
    	popupWindow=new PopupWindow(listView,et_num.getWidth(),300);
    	//popupWindow = new PopupWindow();
    	popupWindow.setFocusable(true);
    	popupWindow.setOutsideTouchable(true);//外部kechum
    	//设置背景,响应点击事件
    	popupWindow.setBackgroundDrawable(new BitmapDrawable());
    	popupWindow.showAsDropDown(et_num, 0, -2);
    }
    public void iteam(){
    	 database=new ArrayList<String>();
    	listView = new ListView(this);
    	listView.setBackgroundResource(R.drawable.listview_background);
    	listView.setDividerHeight(0);
    	  //设置数据
        for(int i=0;i<100;i++){
        	database.add("123456"+i);
        }
    	listView.setAdapter(new MyAdapter()); 
    	//设置条目的点击事件
    	listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				et_num.setText(database.get(position));
				popupWindow.dismiss();
			}

			
		
    	});
    }
    class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return database.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return database.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder viewHolder;
			if(convertView!=null){
				view=convertView;
				viewHolder=(ViewHolder) view.getTag();
			}else{
				view=View.inflate(getApplicationContext(), R.layout.iteam_list, null);
				viewHolder=new ViewHolder();
				viewHolder.iv_delete=(ImageView) view.findViewById(R.id.iv_delete);
				viewHolder.iv_user=(ImageView) view.findViewById(R.id.iv_user);
				viewHolder.tv_des=(TextView) view.findViewById(R.id.tv_des);
				view.setTag(viewHolder);
			
			}
			//获取数据
			viewHolder.iv_delete.setImageResource(R.drawable.delete);
			viewHolder.iv_user.setImageResource(R.drawable.user);
			viewHolder.tv_des.setText(database.get(position));
			//设置图片的点击事件
			viewHolder.iv_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					database.remove(position);
					notifyDataSetChanged();
				}
			});

			return view;
		}
    	
    }
    class ViewHolder{
    	TextView tv_des;
    	ImageView iv_user,iv_delete;
    }
}
