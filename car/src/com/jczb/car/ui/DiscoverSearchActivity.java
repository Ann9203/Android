package com.jczb.car.ui;

import com.jczb.car.R;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

/**
 * 从发现中搜索之后跳转过来的页面
 * @author 吴利昌
 * @date 2015-9-2下午4:45:39
 */
public class DiscoverSearchActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discover_search);
	}

	
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
