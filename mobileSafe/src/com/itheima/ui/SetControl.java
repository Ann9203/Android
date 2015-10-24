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
		//在布局中初始化自定义的属性
		//先初始化控件然后再初始化空间的属性
		init();
		 title = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "title");
		 des_on = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "des_on");
		 des_off = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobileSafe", "des_off");
		tv_title.setText(title);
		//判断获取的CheckBox的状态

	}

	public SetControl(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟街ｏ拷锟斤拷锟斤拷锟斤拷每锟斤拷init锟叫碉拷锟斤拷
	 */
	private  void init() {
		View view = View.inflate(getContext(), R.layout.setting, this);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_des = (TextView) view.findViewById(R.id.tv_update_des);
		ch_ischecked = (CheckBox) view.findViewById(R.id.ch_ischecked);
		

	}

	/**
	 * 锟斤拷锟矫憋拷锟斤拷
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		tv_title.setText(title);
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
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
	 * 锟斤拷锟矫讹拷选锟斤拷锟角凤拷选锟斤拷
	 * 
	 * @param flag
	 */
	public void setChecked(boolean flag) {
		//根据设置完的checkBox事件来判断下一次再重新进入界面的时候描述框应该存在的状态
		ch_ischecked.setChecked(flag);
		//在设置setChecked的状态的之前，要先进行判断一下checked的状态，然后给描述框赋值
		if(ch_ischecked.isChecked()){
			//判断checkeBox的状态来初始化des_off/des_on这两个属性
			tv_des.setText(des_on);
		}else{
			tv_des.setText(des_off);
		}

		
	}
	public boolean isChecked(){
		return ch_ischecked.isChecked();
	}

}
