package com.example.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * ��дTextView��������������Զ���ؼ���ʹ�ÿռ��ܹ���ȡ����
 * @author ѩ����
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
