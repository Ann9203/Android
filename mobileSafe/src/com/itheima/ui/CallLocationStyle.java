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
		//�ڲ����г�ʼ���Զ��������
		//�ȳ�ʼ���ؼ�Ȼ���ٳ�ʼ���ռ������
		init();
		 title = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "title");
		 des_on = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "des_on");
		 des_off = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "des_off");
		tv_title.setText(title);
		//�жϻ�ȡ��CheckBox��״̬

	}

	public CallLocationStyle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * �������֣�������ÿ��init�е���
	 */
	private  void init() {
		View view = View.inflate(getContext(), R.layout.activity_calllocalstyle, this);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_des = (TextView) view.findViewById(R.id.tv_update_des);

		

	}

	/**
	 * ���ñ���
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		tv_title.setText(title);
	}

	/**
	 * ������������
	 * 
	 * @param des
	 */
	public void setDes(String des) {
		tv_des.setText(des);
	}

}
	
