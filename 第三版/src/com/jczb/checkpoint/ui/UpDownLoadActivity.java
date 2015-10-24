package com.jczb.checkpoint.ui;

import com.jczb.checkpoint.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 上传下载主页面
 * @author wlc
 * @date 2015-3-16
 */
public class UpDownLoadActivity extends BaseActivity implements OnClickListener{

	private ImageView downloadImageView;						//下载页面跳转按钮
	private ImageView updateImageView;							//上传按钮
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.download:
			Intent intent = new Intent(UpDownLoadActivity.this, QueryCertificateActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.update:
			Toast.makeText(this, "数据上传成功", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updownload);
		initView();
		findViewById();
	
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		downloadImageView = (ImageView)findViewById(R.id.download);
		downloadImageView.setOnClickListener(this);
		
		updateImageView = (ImageView)findViewById(R.id.update);
		updateImageView.setOnClickListener(this);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}
	
}
