package com.example.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 重写TextView这个方法，创建自定义控件，使得空间能够获取焦点
 * @author 雪宝宝
 *
 */
public class MyTextView extends TextView {

	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isFocused() {
		// TODO Auto-generated method stub	
		return true;
	}

}
