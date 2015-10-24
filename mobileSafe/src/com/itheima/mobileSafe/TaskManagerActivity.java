package com.itheima.mobileSafe;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.Engien.TaskAllInfo;
import com.itheima.Utils.MyAysncTask;
import com.itheima.Utils.SizeCont;
import com.itheima.bean.TaskInfo;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class TaskManagerActivity extends Activity {

	@ViewInject(R.id.lv_taskmanager_list)
	private ListView lv_taskmanager_list;
	@ViewInject(R.id.tv_taskmanager_des)
	private TextView tv_taskmanager_des;
	private List<TaskInfo> userTask;
	private List<TaskInfo> systemTask;
	@ViewInject(R.id.pb_taskmanager_progress)
	private ProgressBar pb;
	@ViewInject(R.id.tv_taskmanager_processes)
	private TextView tv_taskmanager_processes;
	@ViewInject(R.id.tv_taskmanager_ram)
	private TextView tv_taskmanager_ram;
	private MyAdapter myadapter;
	private List<TaskInfo> runningTaskinfo;
	private boolean isshowSystem=true;
	private int processCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taskmanager);
		ViewUtils.inject(this);
		processCount = SizeCont.getProcessCount(getApplicationContext());
		//判断SDK是什么版本的
		int sdkInt = android.os.Build.VERSION.SDK_INT;
		//获取剩余
		long avialbeRam=SizeCont.getAvailableRam(getApplicationContext());
		long totalRam;
		if(sdkInt>=16){
			 totalRam = SizeCont.getTotalRam(getApplicationContext());
		}else{
			totalRam=SizeCont.getStreamRam(getApplicationContext());
		}
		String RamSize = Formatter.formatFileSize(getApplicationContext(), totalRam);
		String formatFileSize = Formatter.formatFileSize(getApplicationContext(), avialbeRam);
		tv_taskmanager_processes.setText("正在运行进程："+processCount+"个");
		tv_taskmanager_ram.setText("剩余内存/总内存\n"+formatFileSize+"/"+RamSize);
		fileData();
		listScroll();
		listIteamClick();
		
	}
	/**
	 * 设置条目的点击事件
	 */
	public void listIteamClick(){
		lv_taskmanager_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//定义Taskinfo 对象
				TaskInfo itemTaskInfo=new TaskInfo();
				//如果是position是两个条目，直接跳出
				if(position==0 || position==userTask.size()+1){
					return;
				}
				//获取是用户的进程还是系统的进程
				if(position>=userTask.size()+1){
					itemTaskInfo=systemTask.get(position-userTask.size()-2);
				}else{
					itemTaskInfo=userTask.get(position-1);
				}
			if(itemTaskInfo.isChecked()){
				itemTaskInfo.setChecked(false);
			}else{
				if(!itemTaskInfo.getPackageName().equals(getPackageName())){
					itemTaskInfo.setChecked(true);
				}
			}
			//更新数据
			ViewContainer viewContainer=new ViewContainer();
			viewContainer=(ViewContainer) view.getTag();
			viewContainer.ch_taskmanager_checked.setChecked(itemTaskInfo.isChecked());
			
				
				
			}
			
		});
	}
	/**
	 *全选 
	 */
	public void  all(View v){
		for(int i=0;i<userTask.size();i++){
			if(!userTask.get(i).getPackageName().equals(getPackageName())){
				userTask.get(i).setChecked(true);
			}
		}
		for(int i=0;i<systemTask.size();i++){
			systemTask.get(i).setChecked(true);
		}
		//更新界面
		myadapter.notifyDataSetChanged();
		
	}
	/**
	 * 取消
	 */
	public void cancel(View v){
		for(int i=0;i<userTask.size();i++){
			userTask.get(i).setChecked(false);
		}
		//获取系统进程
		for(int i=0;i<systemTask.size();i++){
			systemTask.get(i).setChecked(false);
		}
		//更新界面
		myadapter.notifyDataSetChanged();
	}
	/**
	 * 清除缓存
	 */
	public void clear(View v){
		//获取杀死进程的管理者
		ActivityManager am=(ActivityManager) getSystemService(ACTIVITY_SERVICE);
		//设置一个集合来负责装载被杀死的对象的e
		List<TaskInfo>deletelist=new ArrayList<TaskInfo>();
		for(int i=0;i<userTask.size();i++){
			if(userTask.get(i).isChecked()){
				am.killBackgroundProcesses(userTask.get(i).getPackageName());
				deletelist.add(userTask.get(i));
			}
			
		}
		for(int i=0;i<systemTask.size();i++){
			if(systemTask.get(i).isChecked()){
				am.killBackgroundProcesses(systemTask.get(i).getPackageName());
				deletelist.add(systemTask.get(i));
			}

		}
		long memory=0;
		//在list集合中清除
		for(TaskInfo taskinfoo:deletelist){
			if(taskinfoo.isUser()){
				userTask.remove(taskinfoo);
			}else{
				systemTask.remove(taskinfoo);
			}
			memory+=taskinfoo.getRamSize();
		}
		//数据转化
		String memorySize = Formatter.formatFileSize(getApplicationContext(), memory);
		Toast.makeText(getApplicationContext(), "共清理进程"+deletelist.size()+"个"+",释放内存"+memorySize, 0).show();
		//更新数据
		processCount-=deletelist.size();
		//tv_taskmanager_processes.setText("正在运行进程："+processCount+"个");
		//更新还有多少内存
		long avialbeRam=SizeCont.getAvailableRam(getApplicationContext());
		long totalRam;
		//判断SDK是什么版本的
		int sdkInt = android.os.Build.VERSION.SDK_INT;
		if(sdkInt>=16){
			 totalRam = SizeCont.getTotalRam(getApplicationContext());
		}else{
			totalRam=SizeCont.getStreamRam(getApplicationContext());
		}
		String RamSize = Formatter.formatFileSize(getApplicationContext(), totalRam);
		String formatFileSize = Formatter.formatFileSize(getApplicationContext(), avialbeRam);
		tv_taskmanager_processes.setText("正在运行进程："+processCount+"个");
		tv_taskmanager_ram.setText("剩余内存/总内存\n"+formatFileSize+"/"+RamSize);
		myadapter.notifyDataSetChanged();
	}
	/**
	 * 点击设置，隐藏系统的进程
	 */
	public void setting(View v){
		isshowSystem =!isshowSystem;
		myadapter.notifyDataSetChanged();
	}
	/**
	 * 关于listVIew的滑动的事件
	 */
	public void listScroll(){
		lv_taskmanager_list.setOnScrollListener(new OnScrollListener() {
			
			//滑动状态的改变
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			//滑动中
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				//首先判断不能为空
				if(userTask!=null && systemTask!=null){
					if(firstVisibleItem>=userTask.size()+1){
						//判断第一个的条目是否大于userTask.size+1这个
						tv_taskmanager_des.setText("系统进程("+systemTask.size()+")");
						
						
					}else{
						tv_taskmanager_des.setText("用户进程("+userTask.size()+")");
					}
				}
			}
		});
	}
	
	/**
	 * 加载数据
	 */
	public void fileData(){
		new MyAysncTask() {
			
			

			@Override
			public void preExecute() {
				// TODO Auto-generated method stub
				//加载进度条
				pb.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void nextExecute() {
			//之后要更新界面
				if(myadapter==null){
					//如果为空先创建Myadapt而对象
					myadapter=new MyAdapter();
					lv_taskmanager_list.setAdapter(myadapter);
				}else{
					myadapter.notifyDataSetChanged();
				}
				pb.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void doInExecute() {
				runningTaskinfo = TaskAllInfo.getRunningTaskinfo(TaskManagerActivity.this);
				userTask=new ArrayList<TaskInfo>();
				systemTask=new ArrayList<TaskInfo>();                                   
				for(TaskInfo taskinfo:runningTaskinfo){
					if(taskinfo.isUser()){
						userTask.add(taskinfo);
					}else{
						systemTask.add(taskinfo);
					}
				}
			}
		}.execute();
	}
	/**
	 * 加载adapter，自定义写
	 * @author 雪宝宝
	 *
	 */
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return isshowSystem==true? userTask.size()+systemTask.size()+2:userTask.size()+1;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return runningTaskinfo.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(position==0){
				TextView textView=new TextView(getApplicationContext());
				textView.setText("用户进程:"+userTask.size());
				textView.setTextSize(15);
				textView.setTextColor(Color.WHITE);
				textView.setBackgroundColor(Color.GRAY);
				return textView;
			}else if(position==userTask.size()+1){
				TextView textView=new TextView(getApplicationContext());
				textView.setText("系统进程:"+systemTask.size());
				textView.setTextSize(15);
				textView.setTextColor(Color.WHITE);
				textView.setBackgroundColor(Color.GRAY);
				return textView;
			}
			View view;
			ViewContainer viewcontainer;
			//convertView!=null && convertView instanceof RelativeLayout
			if(convertView!=null  && convertView instanceof RelativeLayout){
				view=convertView;
				viewcontainer=(ViewContainer) view.getTag();
			}else{
				view=View.inflate(getApplicationContext(), R.layout.activity_taskmanager_tools, null);
				//初始化空间
				viewcontainer=new ViewContainer();
				viewcontainer.tv_taskmanager_name=(TextView) view.findViewById(R.id.tv_taskmanager_name);
				viewcontainer.tv_taskmanager_size=(TextView)view.findViewById(R.id.tv_taskmanager_size);
				viewcontainer.ch_taskmanager_checked=(CheckBox)view.findViewById(R.id.ch_taskmanager_checked);
				viewcontainer.iv_taskmanager_icon=(ImageView)view.findViewById(R.id.iv_taskmanager_icon);				
				//添加属性容器
				view.setTag(viewcontainer);
			}
			//创建进程对象
			TaskInfo taskinfos=null;
			//要获取position上的对象，是用户系统还是系统进程
			if(position<=userTask.size()){
				taskinfos=userTask.get(position-1);
				//System.out.println(taskinfos.toString());
			}else {
				taskinfos=systemTask.get(position-userTask.size()-2);
			}
			if(taskinfos.getIcon()==null){
				viewcontainer.iv_taskmanager_icon.setImageResource(R.drawable.ic_launcher);
			}else{
				viewcontainer.iv_taskmanager_icon.setImageDrawable(taskinfos.getIcon());
			}
			if(TextUtils.isEmpty(taskinfos.getName())){
				viewcontainer.tv_taskmanager_name.setText(getPackageName());
			}else{
				viewcontainer.tv_taskmanager_name.setText(taskinfos.getName());
			}
			long size=taskinfos.getRamSize();
			String formatFileSize = Formatter.formatFileSize(getApplicationContext(), size);
			viewcontainer.tv_taskmanager_size.setText("内存大小："+formatFileSize);
			if(taskinfos.isChecked()){
				viewcontainer.ch_taskmanager_checked.setChecked(true);
			}else{
				viewcontainer.ch_taskmanager_checked.setChecked(false);
			}
			if(taskinfos.getPackageName().equals(getPackageName())){
				viewcontainer.ch_taskmanager_checked.setVisibility(View.INVISIBLE);
			}else{
				viewcontainer.ch_taskmanager_checked.setVisibility(View.VISIBLE);
			}
			
			return view;
		}
		
	}
	class ViewContainer{
		TextView tv_taskmanager_name,tv_taskmanager_size;
		ImageView iv_taskmanager_icon;
		CheckBox ch_taskmanager_checked;
	}
}
