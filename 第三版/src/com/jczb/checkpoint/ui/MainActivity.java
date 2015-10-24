package com.jczb.checkpoint.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.jczb.checkpoint.R;

/**
 * 主界面
 * @author wlc
 * @date 2015-3-13
 */
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity implements OnClickListener {
	
	//定义tab的四个按钮
	public static String TAB_TAG_ANBIAOCHECK="anbiaocheck";
	public static String TAB_TAG_SETTINGS="settings";
	public static String TAB_TAG_DOWNLOAD="download";
	public static String TAB_TAG_SPOTCHECK="spotcheck";
	
	public static TabHost mTabHost;
	static final int COLOR1=Color.parseColor("#787878");
	static final int COLOR_White=Color.parseColor("#ffffff");
	static final int COLOR_WHITESMOKE=Color.parseColor("#F5F5F5");
	static final int COLOR3=Color.parseColor("#FFE4E1");
	static final int COLOR_LIGHTGREY=Color.parseColor("#D3D3D3");
	ImageView ivAnbiaoCheck,ivSettings,ivDownload,ivSpotcheck;
	TextView tvAnbiaoCheck,tvSettings,tvDownload,tvSpotcheck;
	Intent itAnbiaoCheck,itSettings,itDownload,itSpotCheck;
	LinearLayout llchannel1,llchannel2,llchannel3,llchannel4;
	
	int mCurTabId = R.id.channel1;
	
	private Animation left_in,left_out;
	private Animation right_in,right_out;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main_new);
		prepareAnim();
		prepareIntent();
		setupIntent();
		findViewById();
		initView();
		//初始化背景色
		llchannel1.setBackgroundColor(COLOR_LIGHTGREY);
	}
	
	/**
	 * 动画效果
	 */
	private void prepareAnim() {
		
		left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
		left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);

		right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
		right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
	}
	

	protected void findViewById() {
		/*imgSecurityCheck = (ImageView)this.findViewById(R.id.security_check_id);
		imgSettings = (ImageView)this.findViewById(R.id.settings_id);
		imgSpotCheck = (ImageView)this.findViewById(R.id.spotcheck_id);
		imgUpAndDownload = (ImageView)this.findViewById(R.id.updownload_id);*/
		tvAnbiaoCheck = (TextView)findViewById(R.id.textView1);
		tvSpotcheck = (TextView)findViewById(R.id.textView2);
		tvDownload = (TextView)findViewById(R.id.textView3);
		tvSettings = (TextView)findViewById(R.id.textView4);
		ivAnbiaoCheck = (ImageView)findViewById(R.id.imageView1);
		ivSpotcheck = (ImageView)findViewById(R.id.imageView2);
		ivSettings = (ImageView)findViewById(R.id.imageView4);
		ivDownload = (ImageView)findViewById(R.id.imageView3);
		llchannel1 = (LinearLayout)findViewById(R.id.channel1);
		llchannel2 = (LinearLayout)findViewById(R.id.channel2);
		llchannel3 = (LinearLayout)findViewById(R.id.channel3);
		llchannel4 = (LinearLayout)findViewById(R.id.channel4);
		
	}

	protected void initView() {
		/*imgSpotCheck.setOnClickListener(this);
		imgSecurityCheck.setOnClickListener(this);
		imgUpAndDownload.setOnClickListener(this);
		imgSettings.setOnClickListener(this);*/
		llchannel1.setOnClickListener(this);
		llchannel2.setOnClickListener(this);
		llchannel3.setOnClickListener(this);
		llchannel4.setOnClickListener(this);
	}

	/**
	 * Intent跳转信息
	 */
	private void prepareIntent(){
		itAnbiaoCheck = new Intent(this,ScanActivity.class);
		itDownload = new Intent(this,QueryCertificateActivity.class);
		itSettings = new Intent(this,SettingsActivity.class);
		itSpotCheck = new Intent(this,SpotcheckQueryActivity.class);
	}
	
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			ivAnbiaoCheck.performClick();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 获取Tabhost
	 */
	private  void setupIntent() {
		mTabHost = getTabHost();
		mTabHost.addTab(buildTabSpec(TAB_TAG_ANBIAOCHECK, R.string.securitycheck_btn_str,
				R.drawable.securitycheck_btn_img, itAnbiaoCheck));
		mTabHost.addTab(buildTabSpec(TAB_TAG_SPOTCHECK,
				R.string.spotcheck_btn_str, R.drawable.spotcheck_btn_img, itSpotCheck));
		mTabHost.addTab(buildTabSpec(TAB_TAG_DOWNLOAD, R.string.updwonload_btn_str,
				R.drawable.updownload_btn_img, itDownload));
		mTabHost.addTab(buildTabSpec(TAB_TAG_SETTINGS,
				R.string.settings_btn_str, R.drawable.settings_btn_img, itSettings));
		
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
	public void onClick(View v){
		if (mCurTabId == v.getId()) {
			return;
		}
		/*ivAnbiaoCheck.setImageResource(R.drawable.securitycheck_btn_img);
		ivSpotcheck.setImageResource(R.drawable.spotcheck_btn_img);
		ivDownload.setImageResource(R.drawable.updownload_btn_img);
		ivSettings.setImageResource(R.drawable.settings_btn_img);*/
		
		int checkedId = v.getId();
		final boolean o;
		if (mCurTabId < checkedId) {
			o = true;
		} else {
			o = false;
		}
		if (o) {
			mTabHost.getCurrentView().startAnimation(left_out);
		} else {
			mTabHost.getCurrentView().startAnimation(right_out);
		}
		switch (checkedId) {
		case R.id.channel1:
			mTabHost.setCurrentTabByTag(TAB_TAG_ANBIAOCHECK);
			llchannel1.setBackgroundColor(COLOR_LIGHTGREY);
			llchannel2.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel3.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel4.setBackgroundColor(COLOR_WHITESMOKE);
			ivAnbiaoCheck.setImageResource(R.drawable.securitycheck_btn_img);
			//ivAnbiaoCheck.setBackgroundColor(COLOR3);
			//tvAnbiaoCheck.setTextColor(COLOR2);
			break;
		case R.id.channel2:
			mTabHost.setCurrentTabByTag(TAB_TAG_SPOTCHECK);
			llchannel2.setBackgroundColor(COLOR_LIGHTGREY);
			llchannel1.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel3.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel4.setBackgroundColor(COLOR_WHITESMOKE);
			ivSpotcheck.setImageResource(R.drawable.spotcheck_btn_img);
			//tvSpotcheck.setTextColor(COLOR2);
			break;
		case R.id.channel3:
			mTabHost.setCurrentTabByTag(TAB_TAG_DOWNLOAD);
			llchannel3.setBackgroundColor(COLOR_LIGHTGREY);
			llchannel2.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel1.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel4.setBackgroundColor(COLOR_WHITESMOKE);
			ivDownload.setImageResource(R.drawable.updownload_btn_img);
			//tvDownload.setTextColor(COLOR2);
			break;
		case R.id.channel4:
			mTabHost.setCurrentTabByTag(TAB_TAG_SETTINGS);
			llchannel4.setBackgroundColor(COLOR_LIGHTGREY);
			llchannel2.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel3.setBackgroundColor(COLOR_WHITESMOKE);
			llchannel1.setBackgroundColor(COLOR_WHITESMOKE);
			ivSettings.setImageResource(R.drawable.settings_btn_img);
			//tvSettings.setTextColor(COLOR2);
			break;

		default:
			break;
		}
		if (o) {
			mTabHost.getCurrentView().startAnimation(left_in);
		}else {
			mTabHost.getCurrentView().startAnimation(right_in);
		}
		mCurTabId = checkedId;
	}
	
}
