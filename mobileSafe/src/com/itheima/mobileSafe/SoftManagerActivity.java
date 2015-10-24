package com.itheima.mobileSafe;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.Engien.AppliCationInFo;
import com.itheima.Utils.MyAysncTask;
import com.itheima.Utils.PixToDp;
import com.itheima.Utils.appUtils;
import com.itheima.bean.InstallSoft;
import com.itheima.db.WatchDogSQLHelper;
import com.itheima.db.dao.WatchDogDAO;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SoftManagerActivity extends Activity implements OnClickListener {
	
	@ViewInject(R.id.lv_softmanager_list)
	private ListView lv_softmanager_list;
	@ViewInject(R.id.pb_softmanager_progress)
	private ProgressBar pb;
	private List<InstallSoft> list;
	private AppInfoAdapter appinfoAdapter;
	private List<InstallSoft> systemapp;
	private List<InstallSoft> userapp;
	@ViewInject(R.id.tv_softmanager_des)
	private TextView tv_softmanager_des;
	private View contentView;
	@ViewInject(R.id.tv_softmanage_ram)
	private TextView tv_softmanage_ram;
	@ViewInject(R.id.tv_softmanage_sd)
	private TextView tv_softmanage_sd;
	private LinearLayout ll_uninstall;
	private LinearLayout ll_start;
	private LinearLayout ll_share;
	private LinearLayout ll_des;
	private InstallSoft installSofts;
	private PopupWindow popupWindow;
	private WatchDogDAO watchDogdao;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_softmanager);
		ViewUtils.inject(this);
		watchDogdao=new WatchDogDAO(getApplicationContext());
		list = new ArrayList<InstallSoft>();
		userapp = new ArrayList<InstallSoft>();
		systemapp = new ArrayList<InstallSoft>();
		addAppInfo();
		slideShow();
		listClick();
		longIteamClick();
		//获取空间的大小
		long ram=appUtils.getRam();
		long sd=appUtils.getSD();
		System.out.println(ram+"hello");
		String ramsize = Formatter.formatFileSize(getApplicationContext(), ram);
		String sdsize=Formatter.formatFileSize(getApplicationContext(), sd);
		tv_softmanage_ram.setText("内存可用："+ramsize);
		tv_softmanage_sd.setText("SD卡可用:"+sdsize);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.ll_uninstall:
				uninstallApp();
				break;
			case R.id.ll_start:
				openApp();
				break;
			case R.id.ll_share:
				//System.out.println("start share function success ++++++++++++++");
				shareApp();
				
				break;
			case R.id.ll_des:
				//System.out.println("start des function success ++++++++++++++");
				desApp();
				break;
		
		}
	}
	
	/**
	 * 设置点击一个条目的时候吐出气泡
	 * 思路：
	 * 	1.显示判断这个条目是第一条，还是userapp.size+1,这样的话是标题，直接return
	 * 	2.设置一个view对象
	 * 	3.加载view对象
	 *	4.popu对象加载View，设置长宽高，以及保存他的坐标点
	 */
	public void listClick(){
		//设置条目的点击事件
		lv_softmanager_list.setOnItemClickListener(new OnItemClickListener() {

			
			

			//设置条目的点击事件
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position==0 || position==userapp.size()+1){
					return;
				}
				//判断加载的是系统应用还是用户应用
				if(position<=userapp.size()){
					installSofts=userapp.get(position-1);
				}else{
					installSofts=systemapp.get(position-userapp.size()-2);	
				}
				//隐藏气泡
				hiddenPopup();
				contentView = View.inflate(getApplicationContext(), R.layout.activity_popu, null);
				ll_uninstall=(LinearLayout) contentView.findViewById(R.id.ll_uninstall);
				ll_start=(LinearLayout) contentView.findViewById(R.id.ll_start);
				ll_share=(LinearLayout)contentView.findViewById(R.id.ll_share);
				ll_des=(LinearLayout)contentView.findViewById(R.id.ll_des);
				ll_uninstall.setOnClickListener(SoftManagerActivity.this);
				ll_start.setOnClickListener(SoftManagerActivity.this);
				ll_share.setOnClickListener(SoftManagerActivity.this);
				ll_des.setOnClickListener(SoftManagerActivity.this);
				popupWindow = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				
				//PopupWindow popupWindow=new PopupWindow(contentView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				//获取这个条目的x，y轴的坐标
				int[] location=new int[2];
				view.getLocationInWindow(location);
				int x=location[0];
				int y=location[1];
				int pixtodp=PixToDp.px2dip(getApplicationContext(), 50);
				popupAnimation();
				popupWindow.showAtLocation(parent, Gravity.LEFT|Gravity.TOP, pixtodp, y);
				
				
			}
		});
	}
	/**
	 * 实现气泡中的卸载的方法
	 */
	public void uninstallApp(){
		/**
		 * <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <action android:name="android.intent.action.DELETE" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:scheme="package" />
            </intent-filter>
		 */
		
		//卸载不能卸载系统程序，除非是root过的，不能卸载直接
		if(installSofts.isUser()){
			if(!installSofts.getPackageName().equals(getPackageName())){
				Intent intent=new Intent();
				intent.setAction("android.intent.action.DELETE");
				intent.addCategory("android.intent.category.DEFAULT");
				intent.setData(Uri.parse("package:"+installSofts.getPackageName()));
				startActivityForResult(intent, 0);
			}else{
				Toast.makeText(getApplicationContext(), "文明社会，谢绝自杀！！", 0).show();
			}
			
		}else{
			Toast.makeText(getApplicationContext(), "卸载核心应用程序，请先root!", 0).show();
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//返回结果之后要重新加载一下
		addAppInfo();
		
	}
	/**
	 * 实现气泡中的打开的方法
	 */
	public void openApp(){

	//打开应用程序
		PackageManager pm=getPackageManager();
		Intent intent = pm.getLaunchIntentForPackage(installSofts.getPackageName());
		if(intent!=null){
			startActivity(intent);
		}else{
			Toast.makeText(getApplicationContext(), "核心程序不能启动！", 0).show();
		}
		
	}
	/**
	 * 实现气泡中的分享
	 */
	public void shareApp(){
		/**
		 *  Intent 
			{ 
				act=android.intent.action.SEND 
				typ=text/plain 
				flg=0x3000000 
				cmp=com.android.mms/.ui.ComposeMessageActivity (has extras)   intent中包含信息
			} from pid 228
		 */
		/*System.out.print("share");
		Intent intent=new Intent();
		intent.setAction("android.intent.action.SEND ");
		intent.setType("text/plain");
		//添加数据
		intent.putExtra(Intent.EXTRA_TEXT, "发现一款，很牛的软件，欢迎下载哟！！！"+installSofts.getName()+"，下载地址www.baidu.com");
		startActivity(intent);*/
		//System.out.println("分享");
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SEND");
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "发现一个很牛x软件"+installSofts.getName()+",下载地址:www.baidu.com,自己去搜");
		startActivity(intent);
	}
	/**
	 * 实现气泡中的详情
	 */
	public void desApp(){
		/**
		 *  Intent 
			{ 
			act=android.settings.APPLICATION_DETAILS_SETTINGS    action
			dat=package:com.example.android.apis   data
			cmp=com.android.settings/.applications.InstalledAppDetails 
			} from pid 228
		 */
		Intent intent=new Intent();
		intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
		intent.setData(Uri.parse("package:"+installSofts.getPackageName()));
		startActivity(intent);
	}
	/**
	 * 隐藏气泡
	 */
	public void hiddenPopup(){
		if(popupWindow!=null){
			popupWindow.dismiss();
			popupWindow=null;
		}
	}
	/**
	 * 气泡的动画效果
	 * 思路：
	 * 	1.缩放动画
	 * 	2.渐变的动画
	 * 	3.组合动画
	 * 	4.添加动画
	 * 	5.设置背景
	 */
	public void popupAnimation(){
		//创建缩放动画
		ScaleAnimation scaleAnimation=new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF	, 0	, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(500);
		//创建渐变动画
		AlphaAnimation alphaAnimation=new AlphaAnimation(0.4f, 1.0f);
		alphaAnimation.setDuration(500);
		//创建组合动画
		AnimationSet animationSet=new AnimationSet(true);
		//添加动画
		animationSet.addAnimation(alphaAnimation);
		animationSet.addAnimation(scaleAnimation);
		//开始动画
		contentView.startAnimation(animationSet);
		//popup设置背景才能进行开始动画呢
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	
	/**
	 * 当控件在滑动的时候，显示那个是系统的软件还是用户软件
	 */
	public void slideShow(){
		lv_softmanager_list.setOnScrollListener(new OnScrollListener() {
			
			//在滑动的状态改变的时候进行改变
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			//当滑动的时候进行显示
			//firstVisibleItem:显示的第一个条目
			//visibleItemCount:显示的总个数
			//totalItemCount：条目的总个数
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				//滑动的时候，popup也要小时
				hiddenPopup();
				if(userapp!=null && systemapp!=null){
					//根据显示的第一个条目，来判断使用户程序还是系统程序
					if(firstVisibleItem>=userapp.size()+1){
						tv_softmanager_des.setText("系统程序("+systemapp.size()+")");
					}else{
						tv_softmanager_des.setText("用户程序("+userapp.size()+")");
						
					} 
				}
				
				
				
			}
		});
	}
	/**
	 * 长按锁定应用程序
	 */
	public void longIteamClick(){
		lv_softmanager_list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position==0 || position==userapp.size()+1){
					return true;
				}
				//判断加载的是系统应用还是用户应用
				if(position<=userapp.size()){
					installSofts=userapp.get(position-1);
				}else{
					installSofts=systemapp.get(position-userapp.size()-2);	
				}
				//枷锁
				ContainerTools tools=(ContainerTools) view.getTag();
				if(watchDogdao.queryPackageName(installSofts.getPackageName())){
					watchDogdao.deletePackageName(installSofts.getPackageName());
					tools.iv_watchdog_icon.setImageResource(R.drawable.unlock);
				}else{
					if(!installSofts.getPackageName().equals(getPackageName())){
						watchDogdao.addPackageName(installSofts.getPackageName());
						tools.iv_watchdog_icon.setImageResource(R.drawable.lock);
					}else{
						Toast.makeText(getApplicationContext(), "这个程序不可以枷锁", 0).show();
					}
					
				}
				return true;
			}
		});
	}
	
	/**
	 * 加载数据
	 */
	public void addAppInfo(){
		new MyAysncTask() {
			
			@Override
			public void preExecute() {
				// TODO Auto-generated method stub
				pb.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void nextExecute() {
				// TODO Auto-generated method stub
				if(appinfoAdapter==null){
					appinfoAdapter=new AppInfoAdapter();
					lv_softmanager_list.setAdapter(appinfoAdapter);
				}else{
					appinfoAdapter.notifyDataSetChanged();
				}
				
				pb.setVisibility(View.INVISIBLE);
				
			}
			
			@Override
			public void doInExecute() {
				// TODO Auto-generated method stub
				list=AppliCationInFo.getAppInfo(SoftManagerActivity.this);
				for(InstallSoft installinfo:list){
					if(installinfo.isUser()){
						userapp.add(installinfo);
					}else{
						systemapp.add(installinfo);
					}
				}
			}
		}.execute();
	}
	class AppInfoAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return userapp.size()+systemapp.size()+2;
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
			//在listView的显示的时候添加用户软件，和系统软件两个条目
			//如果在第一个条目的时候就显示用户的管理系统软件
			InstallSoft installSoft;
			if(position==0){
				TextView textView=new TextView(getApplicationContext());
				textView.setText("用户程序("+userapp.size()+")");
				textView.setTextColor(Color.WHITE);
				textView.setBackgroundColor(Color.GRAY);
				textView.setTextSize(15);			
				return textView;
			}else if(position==(userapp.size()+1)){
				TextView textView=new TextView(getApplicationContext());
				textView.setText("系统程序("+systemapp.size()+")");
				textView.setTextColor(Color.WHITE);
				textView.setBackgroundColor(Color.GRAY);
				textView.setTextSize(15);
				return textView;
			}
			//判断加载的是系统应用还是用户应用
			if(position<=userapp.size()){
				installSoft=userapp.get(position-1);
			}else{
				installSoft=systemapp.get(position-userapp.size()-2);
					
				
			}
			//System.out.println(installSoft.toString());
			
			View view=null;
			ContainerTools tools=new ContainerTools();
			//判断缓存复用，首先是如果不为空，其次就是必须是属于RelativeLayout的
			if(convertView!=null && convertView instanceof RelativeLayout){
				view=convertView;
				tools=(ContainerTools) view.getTag();
			}else{
				view=View.inflate(SoftManagerActivity.this, R.layout.activity_softmanager_tools, null);
				//tools=new ContainerTools();
				tools.iv_softmanager_icon=(ImageView)view.findViewById(R.id.iv_softmanager_icon);
				tools.tv_softmanager_versionname=(TextView)view.findViewById(R.id.tv_softmanager_versionname);
				tools.tv_softmanager_issd=(TextView)view.findViewById(R.id.tv_softmanager_issd);
				tools.tv_softmanager_name=(TextView)view.findViewById(R.id.tv_softmanager_name);
				tools.iv_watchdog_icon=(ImageView) view.findViewById(R.id.iv_softmanager_lock);
				view.setTag(tools);
			}
			//=list.get(position);
			//System.out.println(installSoft.toString());
			tools.tv_softmanager_name.setText(installSoft.getName());
			tools.iv_softmanager_icon.setImageDrawable(installSoft.getIcon());
			tools.tv_softmanager_versionname.setText(installSoft.getVersionName());
			//tools.tv_softmanager_issd.setText(installSoft.getName());
			//tools.tv_softmanager_tools_phone.setText(installSoft.getName());
			boolean flags=installSoft.isSD();
			if(flags){
				tools.tv_softmanager_issd.setText("SD卡");
			}else{
				tools.tv_softmanager_issd.setText("手机内存");
			}
			if(watchDogdao.queryPackageName(installSoft.getPackageName())){
				tools.iv_watchdog_icon.setImageResource(R.drawable.lock);
			}else{
				tools.iv_watchdog_icon.setImageResource(R.drawable.unlock);
			}
			
			return view;
		}
		
	}
	/**
	 * 创建一个装载控件的 容器
	 */
	 class ContainerTools{
		TextView tv_softmanager_name,tv_softmanager_issd,tv_softmanager_versionname;
		ImageView iv_softmanager_icon,iv_watchdog_icon;
		
	}
	

}
