package com.jczb.car.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;

/**
 * 发现功能页面
 * 
 * @author 吴利昌
 * @date 2015-9-2下午2:02:58
 */
public class DiscoveryActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	//RelativeLayout rlFoundSearch;
	//ImageView ivTuBiaoSearch;
	

	// 声明用到的几个控件
	private TextView etSearch;
	private ImageView ivSearch;
	private ListView lvHotChannel;
	private RelativeLayout rlFoundSearchEmpty;
	//private ImageView ivTuBiao;
	
	
	/**解析发现接口用的实体类*/
	private ContentVo contentVo = null;
	
	/**用来填充ListView的List*/
	private List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
	
	/**用来获得服务器接口的发现内容集合*/
	private List<Content> discoverContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.discovery);
		
		findViewById();
		
		// 初始化页面控件
		initView();
		/*跳转到搜索关键字的空白的页面*/
		rlFoundSearchEmpty.setOnClickListener(this);
		//ivTuBiao.setOnClickListener(this);
		

		// 启动子线程,获取服务器数据
		discoverThread.start();

	}

	private void findViewById() {
		// TODO Auto-generated method stub
		rlFoundSearchEmpty=(RelativeLayout) findViewById(R.id.rl_search_id);
		//ivTuBiao=(ImageView) findViewById(R.id.iv_search_tubiao_id);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/*case R.id.iv_search_tubiao_id:
           break;*/
		case R.id.rl_search_id:
			Intent intentFoundSerch = new Intent(this,SearchResourceActivity.class);
		    startActivity(intentFoundSerch);
			break;
			

		default:
			break;
		}

	}

	/**
	 * 初始化控件和监听事件
	 * 
	 * @user 吴利昌
	 * @date 2015-9-2 下午2:12:49
	 */
	public void initView() {

		// 初始化控件
		etSearch = (TextView) findViewById(R.id.et_seachkey_id);
		ivSearch = (ImageView) findViewById(R.id.iv_search_id);
		lvHotChannel = (ListView) findViewById(R.id.lv_hotchannel_id);
		//rlFoundSearch=(RelativeLayout)findViewById(R.id.rl_search_id);

		// 初始化监听事件
		ivSearch.setOnClickListener(this);
		// ListView想要实现点击事件本Activity必须实现OnItemClickListener接口
		lvHotChannel.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				//点击item之后能够根据点击位置获取将要携带的参数跳转到搜索结果页
				Intent intent = new Intent(DiscoveryActivity.this, VedioInfoActivity.class);
				
				intent.putExtra("channelType", discoverContent.get(position).getChannelType());
				intent.putExtra("Id", discoverContent.get(position).getId());
				
				startActivity(intent);

			}

		});
	};

	/**
	 * 从服务器端获取并加载数据到ListView中
	 * 
	 * @user 吴利昌
	 * @date 2015-9-2 下午2:54:27
	 */
	public void loadData() {
		
	}

	/**
	 * 定义一个线程等待
	 */
	 Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			// 需要根据线程中传递的msg中what的返回值进行判断
			// 消息的what值为1，为空what为-1，异常为0
			switch (msg.what) {
			case 1:
				
				//获取线程得到的服务器数据
				contentVo = (ContentVo)msg.obj;
				discoverContent = contentVo.getContent();
				
				//将数据赋给List<Map<>>集合
				for (int i = 0;  i< discoverContent.size(); i++) {
					
					Map<String, Object> map = new HashMap<String, Object>();
					//map.put("Id", discoverContent.get(i).getId());
					//map.put("channelType", discoverContent.get(i).getChannelType());
					map.put("title", discoverContent.get(i).getTitle());
					map.put("channelName", discoverContent.get(i).getChannelName());
					data.add(map);
				}
				
				//定义填充ListView的Adapter并填充到ListView中
				//String[]{}与map中填的名字必须保持一致
				SimpleAdapter contentAdapter = new SimpleAdapter(DiscoveryActivity.this, data, R.layout.discovery_item,new String[]{"title","channelName"}, new int[]{R.id.tv_channelTopNews_id,R.id.tv_channelName_id});
				lvHotChannel.setAdapter(contentAdapter);
				
				break;
			case -1:
				Toast.makeText(DiscoveryActivity.this, "数据为空!", Toast.LENGTH_LONG)
				.show();
				break;
			case 0:
				Toast.makeText(DiscoveryActivity.this, "数据获取失败!", Toast.LENGTH_LONG)
				.show();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 开启一个发现子线程
	 */
	Thread discoverThread = new Thread(new Runnable() {

		@Override
		public void run() {
			Message msg = new Message();

			// 获取全局对象Application
			AppContext appContext = (AppContext) getApplication();

			try {
				// 获取服务器数据
				contentVo = appContext.discover(true, 1);

				// 返回contentVo则将消息的what值为1，为空what为-1，异常为0
				if (contentVo != null) {
					msg.what = 1;
					msg.obj = contentVo;
				} else {
					msg.what = -1;
				}

			} catch (AppException e) {
				msg.what = 0;
				e.printStackTrace();
			}
			handler.sendMessage(msg);

		}
	});

	/**
	 * 根据handler传过来的类型显示不同的Toast提示
	 * 
	 * @param what
	 *            handler传的what值
	 * @user 吴利昌
	 * @date 2015-9-2 下午3:53:30
	 */
	public void showToast(int what) {
		switch (what) {
		case -1:
			Toast.makeText(DiscoveryActivity.this, "数据为空!", Toast.LENGTH_LONG)
					.show();
			break;
		case 0:
			Toast.makeText(DiscoveryActivity.this, "数据获取失败!", Toast.LENGTH_LONG)
					.show();
		default:
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}
