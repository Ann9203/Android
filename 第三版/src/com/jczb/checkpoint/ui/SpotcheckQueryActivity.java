package com.jczb.checkpoint.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jczb.checkpoint.R;
import com.jczb.checkpoint.manager.AppDownCongManager;
import com.jczb.checkpoint.manager.AppUpManager;
import com.jczb.checkpoint.model.AppDownCong;
import com.jczb.checkpoint.model.AppUp;
import com.jczb.checkpoint.ui.adapter.MyAdapter;

import com.jczb.widget.PullDownView.PullDownView;
import com.jczb.widget.PullDownView.PullDownView.OnPullDownListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 抽查查询主页面
 * 
 * @author wlc
 * @date 2015-3-16
 */
public class SpotcheckQueryActivity extends BaseActivity implements
		OnClickListener {

	private int nowpage = 1; // 第n页
	private int pagesize = 3;// 每页条数
	/** Handler What加载数据完毕 **/
	private static final int WHAT_DID_LOAD_DATA = 0;
	/** Handler What更新数据完毕 **/
	private static final int WHAT_DID_REFRESH = 1;
	/** Handler What更多数据完毕 **/
	private static final int WHAT_DID_MORE = 2;
	private LayoutInflater layoutInflater;
	private Intent intent;
	private PullDownView mPullDownView;
	private ListView cateListView;
	private MyAdapter adapter;
	private List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

	String[] from = { "mekuanginame", "eprcode",
			"checkresult", "checkdate","state"};
	int[] to = { R.id.txtMeikuangname, R.id.txtErpCode,
			R.id.txtCheckResult, R.id.txtCheckDate,R.id.txtState };

	@Override
	public void onClick(View v) {
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spotcheck_query);

		findViewById();
		initView();
		//设置ListView滚动条不能用
		cateListView.setVerticalScrollBarEnabled(false);
	}

	@Override
	protected void findViewById() {
		mPullDownView = (PullDownView) this.findViewById(R.id.pull_down_view);
		mPullDownView.enableAutoFetchMore(true, 0);
		cateListView = mPullDownView.getListView();
		cateListView.setCacheColorHint(Color.TRANSPARENT);
		cateListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(SpotcheckQueryActivity.this, ProductInfoActivity.class);
						/*//使用bundle传递
						Bundle bundle = new Bundle();
						bundle.putSerializable("data", data.get(arg2));
						intent.putExtras(bundle);*/
						//intent.putStringArrayListExtra("data", data.get(arg2));
						//data.get(arg2);
						intent.putExtra("data",data.get(arg2));
						startActivity(intent);

						SpotcheckQueryActivity.this.overridePendingTransition(
								R.anim.out_right, R.anim.out_left);
					}

				});

		adapter = new MyAdapter(this, data,
				R.layout.activity_spotcheck_query_item, from, to, cateListView);
		cateListView.setAdapter(adapter);
		// 设置可以自动获取更多 滑到最后一个自动获取 改成false将禁用自动获取更多

		mPullDownView.setOnPullDownListener(new OnPullDownListener() {

			@Override
			public void onRefresh() {
				new Thread(new Runnable() {

					@Override
					public void run() {
						data.clear();
						nowpage=1;
					


//						 */
//						private String ExceRemark;

						List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
						AppUpManager appUpManger = new AppUpManager(
								SpotcheckQueryActivity.this);

						List<AppUp> list = appUpManger.getListByPage("1=1",
								pagesize, nowpage);				
						HashMap<String, Object> map =null;
						for(int i=0;i<list.size();i++)
						{	
						 	map = new HashMap<String, Object>();
							map.put("id",list.get(i).getID()+"");
							map.put("name", list.get(i).getName()+"");
							map.put("anbiaocode", list.get(i).getAnBiaoCode()+"");
							map.put("begindate",list.get(i).getBeginDate());
							map.put("epccode",list.get(i).getEPRCode()+"");
							map.put("mekuanginame",list.get(i).getMeKuangiName()+"");
							map.put("anjiancode",list.get(i).getAnJianCode()+"");
							map.put("jobname", list.get(i).getJobName()+"");
							map.put("checkdate",list.get(i).getCheckDate()+"");
							map.put("exceremark", list.get(i).getExceRemark()+"");
							map.put("tidcode", list.get(i).getTIDCode()+"");
							map.put("checkresult", list.get(i).getCheckResult());
							map.put("state", list.get(i).getState());
							data.add(map);
						}

						Message msg = mUIHandler
								.obtainMessage(WHAT_DID_REFRESH);
						msg.obj =data;
						msg.sendToTarget();
					}
				}).start();
			}

			@Override
			public void onLoadMore() {
				new Thread(new Runnable() {

					@Override
					public void run() {
						nowpage=nowpage+1;
						List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
						AppUpManager appUpManger = new AppUpManager(
								SpotcheckQueryActivity.this);

						List<AppUp> list = appUpManger.getListByPage("1=1",
								pagesize, nowpage);				
						HashMap<String, Object> map =null;
						for(int i=0;i<list.size();i++)
						{	
						 	map = new HashMap<String, Object>();
							map.put("id",list.get(i).getID()+"");
							map.put("name", list.get(i).getName()+"");
							map.put("anbiaocode", list.get(i).getAnBiaoCode()+"");
							map.put("begindate",list.get(i).getBeginDate());
							map.put("epccode",list.get(i).getEPRCode()+"");
							map.put("mekuanginame",list.get(i).getMeKuangiName()+"");
							map.put("anjiancode",list.get(i).getAnJianCode()+"");
							map.put("jobname", list.get(i).getJobName()+"");
							map.put("checkdate",list.get(i).getCheckDate()+"");
							map.put("exceremark", list.get(i).getExceRemark()+"");
							map.put("tidcode", list.get(i).getTIDCode()+"");
							map.put("checkresult", list.get(i).getCheckResult());
							map.put("state", list.get(i).getState());
							data.add(map);
						}


						// 告诉它获取更多完毕 这个事线程安全的 可看源代码
						// mPullDownView.notifyDidMore();
						Message msg = mUIHandler.obtainMessage(WHAT_DID_MORE);
						msg.obj = data;
						msg.sendToTarget();
					}
				}).start();
			}
		});
		// 加载数据 本类使用
		loadData();
	}

	private void loadData() {
		// mLoadingDialog = ProgressDialog.show(PullDownActivity.this,
		// "Loading...", "Please wait...", true, false);

		new Thread(new Runnable() {

			@Override
			public void run() {
				nowpage=1;
				List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
				AppUpManager appUpManger = new AppUpManager(
						SpotcheckQueryActivity.this);

				List<AppUp> list = appUpManger.getListByPage("1=1",
						pagesize, nowpage);				
				HashMap<String, Object> map =null;
				for(int i=0;i<list.size();i++)
				{	
				 	map = new HashMap<String, Object>();
					map.put("id",list.get(i).getID()+"");
					map.put("name", list.get(i).getName()+"");
					map.put("anbiaocode", list.get(i).getAnBiaoCode()+"");
					map.put("begindate",list.get(i).getBeginDate());
					map.put("epccode",list.get(i).getEPRCode()+"");
					map.put("mekuanginame",list.get(i).getMeKuangiName()+"");
					map.put("anjiancode",list.get(i).getAnJianCode()+"");
					map.put("jobname", list.get(i).getJobName()+"");
					map.put("checkdate",list.get(i).getCheckDate()+"");
					map.put("exceremark", list.get(i).getExceRemark()+"");
					map.put("tidcode", list.get(i).getTIDCode()+"");
					map.put("checkresult", list.get(i).getCheckResult());
					map.put("state", list.get(i).getState());
					data.add(map);
				}

				
				Message msg = mUIHandler.obtainMessage(WHAT_DID_LOAD_DATA);
				msg.obj = data;
				msg.sendToTarget();
			}
		}).start();
		// mPullDownView.onRefreshComplete();
	}

	private Handler mUIHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case WHAT_DID_LOAD_DATA: {
				if (msg.obj != null) {
					List<HashMap<String, Object>> dataMsg = (List<HashMap<String, Object>>) msg.obj;
					if (!dataMsg.isEmpty()) {
						data.addAll(dataMsg);
						// mStrings.addAll(strings);
						adapter.notifyDataSetChanged();
						if (dataMsg.size() < pagesize)
							mPullDownView.notifyDidDataLoad(true);
						else
							mPullDownView.notifyDidDataLoad(false);
						
					}					
					else
					{
						mPullDownView.notifyDidDataLoad(true);
					}
					// closeLoadingProgressBar();
				}
				// Toast.makeText(PullDownActivity.this,
				// ""+mListView.getAdapter().getCount(),
				// Toast.LENGTH_LONG).show();
				// 诉它数据加载完毕;
				break;
			}
			case WHAT_DID_REFRESH: {
				List<HashMap<String, Object>> dataMsg = (List<HashMap<String, Object>>) msg.obj;
				if (dataMsg != null && !dataMsg.isEmpty()) {
					data.clear();
					data.addAll(dataMsg);
					adapter.notifyDataSetChanged();
		
					if (dataMsg.size() < pagesize) {
						mPullDownView.notifyDidRefresh(true);
						mPullDownView.notifyDidLoadMore(true);
					} else
						mPullDownView.notifyDidRefresh(dataMsg.isEmpty());
				}

				break;
			}

			case WHAT_DID_MORE: {
				List<HashMap<String, Object>> dataMsg = (List<HashMap<String, Object>>) msg.obj;

				if (dataMsg != null && !dataMsg.isEmpty()) {
					data.addAll(dataMsg);
					adapter.notifyDataSetChanged();
					mPullDownView
							.notifyDidLoadMore(dataMsg.size() < pagesize ? false
									: true);
				} else {
					mPullDownView.notifyDidLoadMore(true);
				}
				break;
			}
			}

		}

	};

	@Override
	protected void initView() {

	}

}
