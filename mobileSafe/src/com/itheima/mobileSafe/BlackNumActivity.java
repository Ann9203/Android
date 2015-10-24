package com.itheima.mobileSafe;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.Utils.MyAysncTask;
import com.itheima.bean.BlackNum;
import com.itheima.db.dao.BlackNumSQL;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class BlackNumActivity extends Activity {
	
	@ViewInject(R.id.lv_blacknum_list)
	private ListView lv_blacknum_list;
	private List<BlackNum> list;
	private BlackNumSQL sq;
	@ViewInject(R.id.pb_blacknum_progress)
	private ProgressBar pb_blacknum_pb;
	private BlackNumAdapter blackNumAdapter;
	private AlertDialog dialog;
	private static int limitmax=20;
	private int offsetnum=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blacknum);
  		ViewUtils.inject(this);	
  		//blackNumAdapter = new BlackNumAdapter();
  		sq = new BlackNumSQL(getApplicationContext());
  	
  		showPartData();
  		//设置控件的滑动事件
  		lv_blacknum_list.setOnScrollListener(new OnScrollListener(){

  			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				//判断滑动的空闲的时候是否是最后的
				if(scrollState==SCROLL_STATE_IDLE){
					//首先判断是不是最后一条数据,如果是就开始加载下边的数据，不是的话就让用户进行其他的操作
					int position=lv_blacknum_list.getLastVisiblePosition();
					if(position==list.size()-1){
						offsetnum+=limitmax;
						showPartData();
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
  			
  		});
		
	}
	
	/**
	 * 添加数据
	 * 思路：
	 * 	1.弹出一个对话框
	 * 	2.输入电话号码，输入类型
	 * 	3.选择添加，
	 * 	4.添加到数据库，添加到list中，同时更新listView
	 */

	public void add(View view){
		AlertDialog .Builder builder=new Builder(this);
		//创建view对象
		View view_black=View.inflate(getApplicationContext(), R.layout.activity_setblacknum, null);
		//获取控件
		final EditText et_phone=(EditText) view_black.findViewById(R.id.et_setblacknum_num);
		final RadioGroup rg_black=(RadioGroup) view_black.findViewById(R.id.rg_setblack_style);
		Button bt_ok=(Button) view_black.findViewById(R.id.bt_setblacknum_ok);
		Button bt_cancel=(Button)view_black.findViewById(R.id.bt_setblacknum_cansel);
		bt_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//点击OK添加数据，同事刷新listView
				String phone=et_phone.getText().toString().trim();
				String style="-1";
				if(TextUtils.isEmpty(phone)){
					Toast.makeText(getApplicationContext(), "请输入电话号码！", 0).show();
					return;
				}
				//获取索引值
				int index=rg_black.getCheckedRadioButtonId();
				//根据索引值找到对应的控件
				switch(index){
					case R.id.rb_setblacknum_call:
						style=sq.CALL;
						break;
					case R.id.rb_setblacknum_msg:
						style=sq.MSG;
						break;
					case R.id.rb_setblacknum_all:
						style=sq.ALL;
						break;
				}
				sq.addBlackNum(phone, style);
				list.add(0, new BlackNum(phone, style));
				blackNumAdapter.notifyDataSetChanged();
			
				dialog.dismiss();
				
			}
		});
		bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		builder.setView(view_black);
		 dialog= builder.create();
		dialog.show();
		
	}
	
	

	/**
	 * 做修改，每次只显示20条，然后加载的时候在继续加载
	 * 思路：
	 * 	1.设置listView的滑动事件
	 * 	2判断list中的数据是否为空，为空的话就是第一次加载数据
	 * 	3.不为空就是第二次加载数据
	 * 	4.判断界面查询的是不是最后一个数据，是的话就开始截至
	 * 	5.判断adapter是否为空，不为空的话为空的话就加载listView，不为空的话，就要更新adapter
	 */
	public void showPartData(){
		new MyAysncTask() {
			
			@Override
			public void preExecute() {
				// TODO Auto-generated method stub
				pb_blacknum_pb.setVisibility(View.VISIBLE);	
			}
			
			@Override
			public void nextExecute() {
				// TODO Auto-generated method stub
				if(blackNumAdapter==null){
					blackNumAdapter=new BlackNumAdapter();
					lv_blacknum_list.setAdapter(blackNumAdapter);
				}else{
					blackNumAdapter.notifyDataSetChanged();
				}
				pb_blacknum_pb.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void doInExecute() {
				// TODO Auto-generated method stub
				if(list==null){
					list=sq.queryPartData(limitmax, offsetnum);
					
				}else{
					list.addAll(sq.queryPartData(limitmax, offsetnum));
				}
			}
		}.execute();
	
		
	}
	
	

	
	/**
	 * 查询所有的数据
	 * @author 雪宝宝
	 *
	 */
	/*public void  fillAllData(){
		new MyAysncTask(){			
			@Override
			public void preExecute() {
				// TODO Auto-generated method stub
				//在这之前小咖啡旋转
				pb_blacknum_pb.setVisibility(View.VISIBLE);		
			}
			@Override
			public void nextExecute() {
				
				lv_blacknum_list.setAdapter(blackNumAdapter);
				pb_blacknum_pb.setVisibility(View.INVISIBLE);
			}

			@Override
			public void doInExecute() {
				// TODO Auto-generated method stub
				
				list = sq.queryBlackNum();
			}
			
		}.execute();
	}*/
	
	/**
	 * 定义一个adapter
	 * @author 雪宝宝
	 *
	 */
	class BlackNumAdapter extends BaseAdapter{

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
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ContainerTools containerTools=new ContainerTools();
			View view=null;
			if(convertView==null){
				view=View.inflate(BlackNumActivity.this, R.layout.activity_blacknum_tools, null);
				containerTools.tv_phonnum=(TextView) view.findViewById(R.id.tv_blacknum_tools_phone);
				containerTools.tv_style=(TextView)view.findViewById(R.id.tv_blacknum_tools_style);
				containerTools.iv_delete=(ImageView)view.findViewById(R.id.iv_blacknum_tools_delete);
				view.setTag(containerTools);
				
			}else{
				view=convertView;
				containerTools=(ContainerTools) view.getTag();
				
			}
			final BlackNum blackNum=list.get(position);
			
			
			containerTools.tv_phonnum.setText(blackNum.getBlacknum());
			String mode=blackNum.getMode();
			System.out.println(mode);
			if(mode.equals(sq.CALL)){
				 mode="电话拦截";
			}else if(mode.equals(sq.MSG)){
				mode="短信拦截";
			}else if(mode.equals(sq.ALL)){
				mode="短信电话拦截";
			}
			containerTools.tv_style.setText(mode);
			//删除数据
			containerTools.iv_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//按下之后显示弹出一个提示框
					AlertDialog.Builder builder=new Builder(BlackNumActivity.this);
					builder.setTitle("提示：");
					builder.setIcon(R.drawable.ic_launcher);
					builder.setMessage("您确定要删除电话号码为："+blackNum.getBlacknum()+"?");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							// TODO Auto-generated method stub
							//先删除数据
							sq.deleteBlackNum(blackNum.getBlacknum());
							//删除在list中的数据
							list.remove(position);
							//更新adapter中的数据
							blackNumAdapter.notifyDataSetChanged();
							dialog.dismiss();
						}
					});
					builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					AlertDialog dialog = builder.create();
					dialog.show();
					
					
					
					
				}
			});
			
			return view;
		}
		
	}
	/**
	 * 创建一个装载控件的 容器
	 */
	class ContainerTools{
		TextView tv_phonnum,tv_style;
		ImageView iv_delete;
		
	}

}