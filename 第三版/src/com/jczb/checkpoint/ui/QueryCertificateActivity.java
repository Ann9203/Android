package com.jczb.checkpoint.ui;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.jczb.checkpoint.R;
import com.jczb.checkpoint.R.layout;
import com.jczb.checkpoint.common.Constants;
import com.jczb.checkpoint.common.ImageGetFromHttp;
import com.jczb.checkpoint.common.ImageTools;
import com.jczb.checkpoint.manager.AppPhotoFileManager;
import com.jczb.checkpoint.model.AppPhotoFile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 安标证书下载
 * @author wlc
 * @date 2015-3-19
 */
public class QueryCertificateActivity extends BaseActivity implements OnClickListener {

	private ImageView ivSearchSecuritylicence;
	private EditText searchEditText;
	private Button btQueryAnbiaoList;
	private String barcode;
	private ImageView ivShowImg;
	private AppPhotoFileManager photoManager;
	private String imgTypeId;
	private List<AppPhotoFile> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_certificate);
		findViewById();
		initView();
		searchEditText.setText("MCB150037");
		/*
		photoManager = new AppPhotoFileManager(this);
		mHandler = new Handler(){
			public void handleMessage(Message msg){
				if (msg.what == 3 && msg.obj != null) {
					Bitmap bm = (Bitmap)msg.obj;
					ivShowImg.setImageBitmap(bm);
				}
				
			}
		};
		new Thread(){
			
			public void run() {
				Message msg = new Message();
				try {
					imgTypeId = "10";
					
					
					list = photoManager.getPhotoFileByCondition(imgTypeId);
					byte[] test = list.get(0).getImage();
					Bitmap bm =ImageTools.getBitmapFromByte(test);
					
					msg.what = 3;
					msg.obj = bm;
				} catch (Exception e) {
					e.printStackTrace();
					msg.what = -1;
					msg.obj = e;
				}
				mHandler.sendMessage(msg);
			}
			
		}.start();*/
		
		
		//模拟数据录入
		   	
		   	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search_btn_id:    //搜索下载
			if (searchEditText.getText().toString().trim().equals("") || searchEditText.getText().toString().trim() == null) {
				Toast.makeText(QueryCertificateActivity.this, "请输入安标编号", Toast.LENGTH_SHORT).show();
			} else {
				barcode = searchEditText.getText().toString().trim();				
				Intent intentSearch = new Intent(QueryCertificateActivity.this, ManualScanActivity.class);			
				intentSearch.putExtra("barcode", barcode);
				startActivity(intentSearch);
				/*finish();*/
			}
			break;
			
		case R.id.query_download_list_btn_id:    //查看下载列表项
			Intent intentQuery = new Intent(QueryCertificateActivity.this,QueryDownloadListActivity.class);
			startActivity(intentQuery);
			/*finish();*/
			break;
		default:
			break;
		}
		
	}

	@Override
	protected void findViewById() {
		ivSearchSecuritylicence = (ImageView)findViewById(R.id.search_btn_id);
		
		btQueryAnbiaoList = (Button)findViewById(R.id.query_download_list_btn_id);
		searchEditText = (EditText)findViewById(R.id.searchEditText2);
		//ivShowImg = (ImageView)findViewById(R.id.imgShow_Test_id);
	}

	@Override
	protected void initView() {
		ivSearchSecuritylicence.setOnClickListener(this);	
		btQueryAnbiaoList.setOnClickListener(this);
	}
}
