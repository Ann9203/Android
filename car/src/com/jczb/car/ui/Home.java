package com.jczb.car.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.jczb.car.R;
import com.jczb.car.R.layout;
import com.jczb.car.R.menu;
import com.jczb.car.adapter.NewsFragmentPagerAdapter;
import com.jczb.car.bean.ChannelCategory;
import com.jczb.car.bean.ChannelCategoryVo;
import com.jczb.car.common.UIHelper;
import com.jczb.car.fragment.NewsFragment;
import com.jczb.car.tool.*;
import com.jczb.car.ui.PictureViewInfoActivity.pictureViewInfoThread;
import com.jczb.car.view.*;
import com.jczb.car.AppContext;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends FragmentActivity {
	/** 自定义HorizontalScrollView */
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	LinearLayout mRadioGroup_content;
	LinearLayout ll_more_columns;
	RelativeLayout rl_column;
	private ViewPager mViewPager;
	private ImageView button_more_columns;
	/** 当前选中的栏目*/
	private int columnSelectIndex = 0;
	/** 左阴影部分*/
	public ImageView shade_left;
	/** 右阴影部分 */
	public ImageView shade_right;
	/** 屏幕宽度 */
	private int mScreenWidth = 0;
	/** Item宽度 */
	private int mItemWidth = 0;
	
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	/** 用户选择的新闻分类列表*/	
	private ChannelCategoryVo myChannelResult=new ChannelCategoryVo();
	private List<ChannelCategory> categoryList=new ArrayList<ChannelCategory>();
	
	/** 请求CODE */
	public final static int CHANNELREQUEST = 1;
	/** 调整返回的RESULTCODE */
	public final static int CHANNELRESULT = 10;
	
	//private AppContext appContext;// 全局Context,封装好的接口类
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		mScreenWidth = BaseTools.getWindowsWidth(this);
		mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
		findviewbyId();		
		
		// 启动子线程,获取服务器数据及之后的初始化工作
		 new Thread(new MyThread()).start();	 
		 		
	}
	
	/** 初始化layout控件*/
	private void findviewbyId() {
		mColumnHorizontalScrollView =  (ColumnHorizontalScrollView)findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
		ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
		rl_column = (RelativeLayout) findViewById(R.id.rl_column);
		button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);
		
		shade_left = (ImageView) findViewById(R.id.shade_left);
		shade_right = (ImageView) findViewById(R.id.shade_right);
		
		button_more_columns.setOnClickListener(new OnClickListener(){			
			@Override
			public void onClick(View v) {
				Intent intent_channel = new  Intent(getApplicationContext(),ChannelActivity.class);
				startActivityForResult(intent_channel, CHANNELREQUEST);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			}
		});
//		setChangelView();
	}
	
	
	/*主线程-拿数据*/
	 public class MyThread implements Runnable { 
         @Override
         public void run() {
				Message msg = new Message();						
				boolean isRefresh=false;
				
				AppContext appContext = (AppContext) getApplication();
				
				try {
					myChannelResult = appContext.getChannelCategory(true,1);					
																					
						// 如果返回的为空或者初始化时输入的ip地址无效(会返回下面的字符串)，说明服务器连接失败！
						if (myChannelResult == null) {
							// 使用-1代表服务器连接失败
							msg.what = -1;
						} else {								
	                        msg.what=1;
	                        msg.obj=myChannelResult; 
						}
					} catch (Exception e) {
						e.printStackTrace();						
						// 使用-1代表程序异常
						msg.what = -2;
						msg.obj = e;
					}
				handler.sendMessage(msg);			        	  
		    }
	 }
	 
	 
	 /*子线程-解析数据*/
	 private  Handler handler = new Handler() {		 
          // 在Handler中获取消息，重写handleMessage()方法
          @Override
          public void handleMessage(Message msg) {
        	  
        	  switch (msg.what) {
				case -1:
					Toast.makeText(Home.this, "服务器连接失败!",
							Toast.LENGTH_SHORT).show();
					break;
				case -2:
					Toast.makeText(Home.this, "哎呀，出错啦...",
							Toast.LENGTH_SHORT).show();
					break;				
				case 1:				
					ChannelCategoryVo category=(ChannelCategoryVo) msg.obj;
					
					categoryList=category.getChannelCategory();  //查询所有的频道栏目	
					
					initTabColumn();     //初始化滑动菜单
					initFragment();		//初始化fragment
					
					break;
					
				default:
					break;
        	  }
          }   
	 };
	
	
	/** 
	 *  初始化Column栏目项
	 * */
	private void initTabColumn() {
		
		mRadioGroup_content.removeAllViews();
				
		int count =  categoryList.size();
		mColumnHorizontalScrollView.setParam(this, mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
		for(int i = 0; i< count; i++){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , LayoutParams.WRAP_CONTENT);
			params.leftMargin = 5;
			params.rightMargin = 5;
			TextView columnTextView = new TextView(this);
			/*横向列表频道样式*/
			columnTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
			columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			
			columnTextView.setText(categoryList.get(i).getChannelName());
			columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
			
			if(columnSelectIndex == i){
				columnTextView.setSelected(true);
			}
			
			/*频道列表单击事件*/
			columnTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			          for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
				          View localView = mRadioGroup_content.getChildAt(i);
				          if (localView != v)
				        	  localView.setSelected(false);
				          else{
				        	  localView.setSelected(true);
				        	  mViewPager.setCurrentItem(i);
				          }
			          }
			          /*打印选中的栏目*/
			          Toast.makeText(getApplicationContext(), categoryList.get(v.getId()).getChannelName(), Toast.LENGTH_SHORT).show();
				}
			});
			mRadioGroup_content.addView(columnTextView, i ,params);
		}
	}
	
	
	/** 
	 * 初始化Fragment
	 * */
	private void initFragment() {
		fragments.clear();//清空
		int count =  categoryList.size();
		for(int i = 0; i< count;i++){
			/*数据同步*/
			Bundle data = new Bundle();
    		data.putString("name", categoryList.get(i).getChannelName());
    		data.putInt("id", categoryList.get(i).getId());
    		
			NewsFragment newfragment = new NewsFragment();			
			newfragment.setArguments(data);
			fragments.add(newfragment);
		}
		
		/*适配中实现了左右滑动的效果*/
		NewsFragmentPagerAdapter mAdapter = new NewsFragmentPagerAdapter(getSupportFragmentManager(),fragments);
		mViewPager.setOffscreenPageLimit(0);
//		/*绑定新闻主题数据*/
		mViewPager.setAdapter(mAdapter);
		//mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(pageListener);
	}
	
	
	/** 
	 *  ViewPager切换监听方法，使tab和page同步！
	 * */
	public OnPageChangeListener pageListener= new OnPageChangeListener(){

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};
	
	
	/** 
	 *  选择的Column里面的Tab
	 * */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
			View checkView = mRadioGroup_content.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
//			 mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
//			 mItemWidth , 0);
		}
		//判断是否选中
		for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
				
				/*拿到点击频道栏目ID*/
				Intent channelIntent=new Intent();
				Bundle channelData = new Bundle();
				channelData.putInt("channelID", categoryList.get(j).getId());
				channelData.putString("channelName", categoryList.get(j).getChannelName());
				
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	
	/*退出系统、提示*/
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出",
						Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		//拦截MENU按钮点击事件，让他无任何操作
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	/*自选频道返回主页、数据同步*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case CHANNELREQUEST:
			if(resultCode == CHANNELRESULT){
				setChangelView();
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
	/**--------------------------------------测试用、假数据----------------------------- 
	 *  当栏目项发生变化时候调用
	 * */
	private void setChangelView() {
		initColumnData();
		initTabColumn();
		initFragment();
	}

	/** 获取Column栏目 数据*/
	private void initColumnData() {
//		userChannelList = ((ArrayList<Channel>)ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).getUserChannel());
		new Thread() {
			public void run() {
								
			}				
		};		

				
	}
	
	

}
