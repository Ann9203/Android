package com.jczb.checkpoint.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import com.jczb.checkpoint.R;
import com.jczb.checkpoint.manager.AppDownManager;
import com.jczb.checkpoint.model.AppDown;

/**
 * 查看安标证书下载列表类
 * 
 * @author wlc
 * @date 2015-4-2
 */
public class QueryDownloadListActivity extends BaseActivity implements OnClickListener{

	private ListView listView;
	private ImageView imgView;
	private List<AppDown> listAppDown;
	private AppDownManager anbiaoInfoManager;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_download_list);

		findViewById();
		initView();

		// 查询本地数据库中已经下载的安标证书记录并显示到ListView中
		anbiaoInfoManager = new AppDownManager(this);
		listAppDown = anbiaoInfoManager.getAnbiaoList();

		List<Map<String, Object>> anbiaoData = new ArrayList<Map<String, Object>>();
		// 将获取到的数据库记录加到Map的特定字段中
		for (int i = 0; i < listAppDown.size(); i++) {
			Map<String, Object> list = new HashMap<String, Object>();
			list.put("anbiao_code", listAppDown.get(i).anbiao_code);
			list.put("product_name", listAppDown.get(i).product_name);
			list.put("holder_person", listAppDown.get(i).holder_person);
			list.put("produce_unit", listAppDown.get(i).production_unit_id);
			list.put("produce_addr", listAppDown.get(i).production_address);
			anbiaoData.add(list);
		}
		// 使用SimpleAdapter为ListView添加数据
		// 下面的参数一定要正确匹配。。
		SimpleAdapter anbiaoInfoAdapter = new SimpleAdapter(
				QueryDownloadListActivity.this, anbiaoData,
				R.layout.activity_query_download_list_item, new String[] {
						"anbiao_code", "product_name", "holder_person",
						"produce_unit", "produce_addr" }, new int[] {
						R.id.anbiao_code_id, R.id.product_name_str_id,
						R.id.holder_person_id, R.id.production_unit_id_id,
						R.id.production_address_id });
		listView.setAdapter(anbiaoInfoAdapter);

		// 实现listView点击Item的时候通过intent传值给下个activity
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(QueryDownloadListActivity.this,
						AnbiaoDetailShowActivity.class);
				// 点击item位置，获取相应的id值，通过intent传递.而且查出的数据应该按照id进行排序
				intent.putExtra("params",Integer.toString(listAppDown.get(arg2).getId()));
				
				startActivity(intent);
				finish();
				System.out.println("aaa");
			}
		});

	}

	@Override
	protected void findViewById() {
		listView = (ListView) findViewById(R.id.download_list_lv_id);
		imgView = (ImageView) findViewById(R.id.download_list_goback_img_id);
	}

	@Override
	protected void initView() {
		imgView.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.download_list_goback_img_id:
			intent = new Intent(QueryDownloadListActivity.this, QueryCertificateActivity.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}
	}

}
