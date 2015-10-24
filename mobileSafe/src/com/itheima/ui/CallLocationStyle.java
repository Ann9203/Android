package com.itheima.ui;

import com.itheima.mobileSafe.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CallLocationStyle extends RelativeLayout {

	private TextView tv_title;
	private TextView tv_des;
	private String title;
	private String des_on;
	private String des_off;

	public CallLocationStyle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public CallLocationStyle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//ÔÚ²¼¾ÖÖÐ³õÊ¼»¯×Ô¶¨ÒåµÄÊôÐÔ
		//ÏÈ³õÊ¼»¯¿Ø¼þÈ»ºóÔÙ³õÊ¼»¯¿Õ¼äµÄÊôÐÔ
		init();
		 title = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "title");
		 des_on = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "des_on");
		 des_off = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "des_off");
		tv_title.setText(title);
		//ÅÐ¶Ï»ñÈ¡µÄCheckBoxµÄ×´Ì¬

	}

	public CallLocationStyle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ö£ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ã¿ï¿½ï¿½initï¿½Ðµï¿½ï¿½ï¿½
	 */
	private  void init() {
		View view = View.inflate(getContext(), R.layout.activity_calllocalstyle, this);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_des = (TextView) view.findViewById(R.id.tv_update_des);

		

	}

	/**
	 * ï¿½ï¿½ï¿½Ã±ï¿½ï¿½ï¿½
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		tv_title.setText(title);
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	 * 
	 * @param des
	 */
	public void setDes(String des) {
		tv_des.setText(des);
	}

}
	
