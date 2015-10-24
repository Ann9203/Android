package com.itheima.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	//在代码中使用
	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 在布局文件使用的时候调用
	 * @param context
	 * @param attrs  控件的所有的属性
	 * @param defStyle
	 * 控件的所有的属性都可以 用代码来代替的
	 */
	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 在布局文件的使用的时候调用
	 * @param context
	 * @param attrs
	 */

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 自定义设置一个获取焦点
	 * 重写这个方法的原因就是因为：TextView中没有自动获取焦点触摸的这个功能，必须先要自动获取焦点为true,那个方法才可以使用
	 */
	@Override
	public boolean isFocused() {
		// TODO Auto-generated method stub
		//true：表示获取焦点，
		//false：表示不获取焦点
		return true;
	}

}
