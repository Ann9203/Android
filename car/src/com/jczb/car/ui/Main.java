package com.jczb.car.ui;

import com.jczb.car.AppContext;
import com.jczb.car.R;
import com.jczb.car.common.UIHelper;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class Main extends TabActivity implements OnClickListener {
	
	public static String TAB_TAG_HOME = "home";
	public static String TAB_TAG_DISCOVERY = "discovery";
	public static String TAB_TAG_MINE = "test";
	
	public static TabHost mTabHost;
	static final int COLOR1 = Color.parseColor("#787878");
	static final int COLOR2 = Color.parseColor("#000000");
	ImageView mBut1, mBut2, mBut3;
	TextView mCateText1, mCateText2, mCateText3;

	Intent mHomeItent,  mDiscoveryIntent,
			mMineIntent;

	int mCurTabId = R.id.channel1;

	// Animation
	private Animation left_in, left_out;
	private Animation right_in, right_out;
	
	private AppContext appContext;// 全局Context

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//setContentView(R.layout.mine);
		
		appContext = (AppContext) getApplication();
		// 网络连接判断
		if (!appContext.isNetworkConnected())
			UIHelper.ToastMessage(this, R.string.network_not_connected);
		// 初始化登录
		appContext.initLoginInfo();
		prepareAnim();
		prepareIntent();
		setupIntent();
		prepareView();
	}

	private void prepareAnim() {
		left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
		left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);

		right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
		right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
	}

	private void prepareView() {
		mBut1 = (ImageView) findViewById(R.id.imageView1);
		mBut2 = (ImageView) findViewById(R.id.imageView2);
		mBut3 = (ImageView) findViewById(R.id.imageView3);
		
		findViewById(R.id.channel1).setOnClickListener(this);
		findViewById(R.id.channel2).setOnClickListener(this);
		findViewById(R.id.channel3).setOnClickListener(this);
		
		mCateText1 = (TextView) findViewById(R.id.textView1);
		mCateText2 = (TextView) findViewById(R.id.textView2);
		mCateText3 = (TextView) findViewById(R.id.textView3);
	
	}

	private void prepareIntent() {
		/*主页面*/
		mHomeItent = new Intent(this, Home.class);
		/*发现*/
		mDiscoveryIntent = new Intent(this, DiscoveryActivity.class);
		
		/*我的*/
		//是首次登录则跳转到登录页面,否则跳转到我的页面
		//获取登录Xml文件
		SharedPreferences loginXml = getSharedPreferences("loginXml", 0);
		//读取login文件中的值
		boolean isFirst = loginXml.getBoolean("login", true);
		
		if (isFirst) {
			mMineIntent = new Intent(this, LoginActivity.class);
		}else {
			mMineIntent = new Intent(this,Mine.class);
		}
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			mBut1.performClick();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void setupIntent() {
		mTabHost = getTabHost();
		mTabHost.addTab(buildTabSpec(TAB_TAG_HOME, R.string.category_home,
				R.drawable.icon_1_n, mHomeItent));
		mTabHost.addTab(buildTabSpec(TAB_TAG_DISCOVERY,
				R.string.category_discovery, R.drawable.icon_2_n, mDiscoveryIntent));
		mTabHost.addTab(buildTabSpec(TAB_TAG_MINE, R.string.category_mine,
				R.drawable.icon_3_n, mMineIntent));
		
	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return mTabHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}

	public static void setCurrentTabByTag(String tab) {
		mTabHost.setCurrentTabByTag(tab);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (mCurTabId == v.getId()) {
			return;
		}
		mBut1.setImageResource(R.drawable.icon_1_n);
		mBut2.setImageResource(R.drawable.icon_2_n);
		mBut3.setImageResource(R.drawable.icon_3_n);
		
		mCateText1.setTextColor(COLOR1);
		mCateText2.setTextColor(COLOR1);
		mCateText3.setTextColor(COLOR1);
	
		int checkedId = v.getId();
		final boolean o;
		if (mCurTabId < checkedId)
			o = true;
		else
			o = false;
		if (o)
			mTabHost.getCurrentView().startAnimation(left_out);
		else
			mTabHost.getCurrentView().startAnimation(right_out);
		switch (checkedId) {
		case R.id.channel1:
			mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
			mBut1.setImageResource(R.drawable.icon_1_c);
			mCateText1.setTextColor(COLOR2);
			break;
		case R.id.channel2:
			mTabHost.setCurrentTabByTag(TAB_TAG_DISCOVERY);
			mBut2.setImageResource(R.drawable.icon_2_c);
			mCateText2.setTextColor(COLOR2);
			break;
		case R.id.channel3:
			mTabHost.setCurrentTabByTag(TAB_TAG_MINE);
			mBut3.setImageResource(R.drawable.icon_3_c);
			mCateText3.setTextColor(COLOR2);
			break;
		
		default:
			break;
		}

		if (o)
			mTabHost.getCurrentView().startAnimation(left_in);
		else
			mTabHost.getCurrentView().startAnimation(right_in);
		mCurTabId = checkedId;
	}
}