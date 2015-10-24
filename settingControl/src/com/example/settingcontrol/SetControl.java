package com.example.settingcontrol;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetControl extends RelativeLayout {

	private TextView tv_title;
	private TextView tv_des;
	private CheckBox ch_ischecked;

	public SetControl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public SetControl(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public SetControl(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * �������֣�������ÿ��init�е���
	 */
	public void init() {
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

	/**
	 * ���ö�ѡ���Ƿ�ѡ��
	 * 
	 * @param flag
	 */
	public void isChecked(boolean flag) {
		ch_ischecked.setChecked(flag);
	}

}
