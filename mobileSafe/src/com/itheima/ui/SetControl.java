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

public class SetControl extends RelativeLayout {

	private TextView tv_title;
	private TextView tv_des;
	private CheckBox ch_ischecked;
	private String title;
	private String des_on;
	private String des_off;

	public SetControl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public SetControl(Context context, AttributeSet attrs) {
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

	public SetControl(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * �������֣�������ÿ��init�е���
	 */
	private  void init() {
		View view = View.inflate(getContext(), R.layout.setting, this);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_des = (TextView) view.findViewById(R.id.tv_update_des);
		ch_ischecked = (CheckBox) view.findViewById(R.id.ch_ischecked);
		

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

	
	public void setBg(){
		ch_ischecked.setBackgroundColor(Color.RED);
	}
	/**
	 * ���ö�ѡ���Ƿ�ѡ��
	 * 
	 * @param flag
	 */
	public void setChecked(boolean flag) {
		//�����������checkBox�¼����ж���һ�������½�������ʱ��������Ӧ�ô��ڵ�״̬
		ch_ischecked.setChecked(flag);
		//������setChecked��״̬��֮ǰ��Ҫ�Ƚ����ж�һ��checked��״̬��Ȼ���������ֵ
		if(ch_ischecked.isChecked()){
			//�ж�checkeBox��״̬����ʼ��des_off/des_on����������
			tv_des.setText(des_on);
		}else{
			tv_des.setText(des_off);
		}

		
	}
	public boolean isChecked(){
		return ch_ischecked.isChecked();
	}

}
