package com.jczb.checkpoint.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jczb.checkpoint.R;
import com.jczb.checkpoint.common.ImageTools;
import com.jczb.checkpoint.db.PhotoFile.FIELDS;
import com.jczb.checkpoint.manager.AppPhotoFileManager;
import com.jczb.checkpoint.model.AppPhotoFile;

/**
 * 产品详细信息页
 * 
 * @author wlc
 * @date 2015-3-19
 */
public class ProductInfoActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {

	private ImageView image,imgGoback;
	private GridView gridview;
	private TextView tvEpcCode, tvTidCode, tvCoalMine, tvCheckdate,
			tvCheckResult, tvWorker;
	private List<AppPhotoFile> appPhotoFiles;
	private String Rfid;
	

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_product_info);
		
		//接收传递过来的hashmap
				HashMap<String, Object> data = new HashMap<String, Object>();
				data = (HashMap<String, Object>) getIntent().getSerializableExtra(
						"data");
		//根据Id获取记录
		AppPhotoFileManager photoManager = new AppPhotoFileManager(ProductInfoActivity.this);
		appPhotoFiles = photoManager.getPhotoFileById(data.get("id").toString());
		
		findViewById();
		initView();
		
		//将hashmap中的数据添加到各个控件上
		tvEpcCode.setText(data.get("epccode").toString());
		tvTidCode.setText(data.get("tidcode").toString());
		tvCoalMine.setTag(data.get("mekuanginame").toString());
		tvCheckdate.setTag(data.get("checkdate").toString());
		tvCheckResult.setTag(data.get("checkresult").toString());
		tvWorker.setTag(data.get("jobname").toString());
			
		}

		

	@Override
	protected void findViewById() {
		// image = (ImageView) findViewById(R.id.product_img_id);
		tvCheckdate = (TextView) findViewById(R.id.check_date_id);
		tvCheckResult = (TextView) findViewById(R.id.check_result_id);
		tvEpcCode = (TextView) findViewById(R.id.epc_code_id);
		tvCoalMine = (TextView) findViewById(R.id.coalmine_name_id);
		tvWorker = (TextView) findViewById(R.id.workder_name_id);
		tvTidCode = (TextView) findViewById(R.id.tid_code_id);
		gridview = (GridView) findViewById(R.id.gridview);
		imgGoback = (ImageView) findViewById(R.id.product_info_img_goback_id);
	}

	@Override
	protected void initView() {
		// listView.setOnClickListener(this);
		imgGoback.setOnClickListener(this);
		gridview.setAdapter(new ImageAdapter(this));// 调用ImageAdapter.java
		gridview.setOnItemClickListener(new OnItemClickListener() {// 监听事件
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(ProductInfoActivity.this, "" + position,
						Toast.LENGTH_SHORT).show();// 显示信息;
			}
		});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.product_info_img_goback_id:
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			//return mThumbIds.length;
			return appPhotoFiles.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return appPhotoFiles.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return appPhotoFiles.get(position).getID();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			ImageView imageview;
			if (convertView == null) {
				imageview = new ImageView(mContext);
				imageview.setLayoutParams(new GridView.LayoutParams(140, 140));
				imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageview.setPadding(8, 8, 8, 8);
			} else {
				imageview = (ImageView) convertView;
			}
			final AppPhotoFile appPhotoFile = appPhotoFiles.get(position);
			imageview.setImageBitmap(ImageTools.getBitmapFromByte(appPhotoFile.getImage()));
			//imageview.setImageResource(mThumbIds[position]);
			//imageview.setb
			return imageview;
		}

		

	}

}
