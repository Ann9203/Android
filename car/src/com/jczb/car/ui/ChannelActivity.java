package com.jczb.car.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jczb.car.AppContext;
import com.jczb.car.R;
import com.jczb.car.adapter.DragAdapter;
import com.jczb.car.adapter.OtherAdapter;
import com.jczb.car.bean.ChannelCategory;
import com.jczb.car.bean.ChannelCategoryVo;
import com.jczb.car.view.DragGrid;
import com.jczb.car.view.OtherGridView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ChannelActivity extends BaseActivity implements
		OnItemClickListener {

	public static String TAG = "ChannelActivity";
	/** 用户栏目的GRIDVIEW */
	private DragGrid userGridView;
	/** 其它栏目的GRIDVIEW */
	private OtherGridView otherGridView;

	/** 用户栏目对应的适配器，可以拖动 */
	DragAdapter userAdapter;
	/** 其它栏目对应的适配器 */
	OtherAdapter otherAdapter;

	private ChannelCategoryVo myChannelResult = new ChannelCategoryVo();
	private List<ChannelCategory> categoryList = new ArrayList<ChannelCategory>();

	// /** 其它栏目列表 */
	// ArrayList<Channel> otherChannelList = new ArrayList<Channel>();
	// /** 用户栏目列表 */
	// ArrayList<Channel> userChannelList = new ArrayList<Channel>();
	/** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
	boolean isMove = false;

	/* 全局变量，调用接口 */
	private AppContext appContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.channel_car);
		findviewById();
		/* 实例化调用接口 */
		appContext = (AppContext) getApplication();
		// 网络连接判断
		if (!appContext.isNetworkConnected())
			Toast.makeText(this, R.string.network_not_connected,
					Toast.LENGTH_SHORT).show();

		/* 开辟线程，加载频道数据 */
		new Thread(new MyThread()).start();
	}

	/** 初始化布局 */
	private void findviewById() {
		userGridView = (DragGrid) findViewById(R.id.userGridView);
		otherGridView = (OtherGridView) findViewById(R.id.otherGridView);
	}

	/* 主线程-拿数据 */
	public class MyThread implements Runnable {
		@Override
		public void run() {
			Message msg = new Message();
			Bitmap bitemapBitmap = null;

			try {

				myChannelResult = appContext.getChannelCategory(true, 1);

				// 如果返回的为空或者初始化时输入的ip地址无效(会返回下面的字符串)，说明服务器连接失败！
				if (myChannelResult == null) {
					// 使用-1代表服务器连接失败
					msg.what = -1;
				} else {
					msg.what = 1;
					msg.obj = myChannelResult;
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 使用-1代表程序异常
				msg.what = -2;
				msg.obj = e;
			}
			handler.sendMessage(msg);
		}
	}

	/* 子线程-解析数据 */
	private Handler handler = new Handler() {
		// 在Handler中获取消息，重写handleMessage()方法
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case -1:
				Toast.makeText(ChannelActivity.this, "服务器连接失败!",
						Toast.LENGTH_SHORT).show();
				break;
			case -2:
				Toast.makeText(ChannelActivity.this, "哎呀，出错啦...",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				ChannelCategoryVo category = (ChannelCategoryVo) msg.obj;

				categoryList = category.getChannelCategory();

				/* 我的、更多频道数据适配 */
				userAdapter = new DragAdapter(ChannelActivity.this,
						categoryList);
				userGridView.setAdapter(userAdapter);
				otherAdapter = new OtherAdapter(ChannelActivity.this,
						categoryList);
				otherGridView.setAdapter(otherAdapter);
				/* 设置GRIDVIEW的ITEM的点击监听 */
				otherGridView.setOnItemClickListener(ChannelActivity.this);
				userGridView.setOnItemClickListener(ChannelActivity.this);

				break;

			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	/** GRIDVIEW对应的ITEM点击监听接口 */
	@Override
	public void onItemClick(AdapterView<?> parent, final View view,
			final int position, long id) {
		// TODO Auto-generated method stub
		if (isMove) {
			return;
		}
		switch (parent.getId()) {
		case R.id.userGridView:
			// position为 0，1 的不可以进行任何操作
			if (position != 0 && position != 1) {
				final ImageView moveImageView = getView(view);
				if (moveImageView != null) {
					TextView newTextView = (TextView) view
							.findViewById(R.id.text_item);
					final int[] startLocation = new int[2];
					newTextView.getLocationInWindow(startLocation);
					final ChannelCategory channel = ((DragAdapter) parent
							.getAdapter()).getItem(position);// 获取点击的频道内容
					otherAdapter.setVisible(false);
					// 添加到最后一个
					otherAdapter.addItem(channel);
					new Handler().postDelayed(new Runnable() {
						public void run() {
							try {
								int[] endLocation = new int[2];
								// 获取终点的坐标
								otherGridView.getChildAt(
										otherGridView.getLastVisiblePosition())
										.getLocationInWindow(endLocation);
								MoveAnim(moveImageView, startLocation,
										endLocation, channel, userGridView);
								userAdapter.setRemove(position);
							} catch (Exception localException) {
							}
						}
					}, 50L);
				}
			}
			break;
		case R.id.otherGridView:
			final ImageView moveImageView = getView(view);
			if (moveImageView != null) {
				TextView newTextView = (TextView) view
						.findViewById(R.id.text_item);
				final int[] startLocation = new int[2];
				newTextView.getLocationInWindow(startLocation);
				final ChannelCategory channel = ((OtherAdapter) parent
						.getAdapter()).getItem(position);
				userAdapter.setVisible(false);
				// 添加到最后一个
				userAdapter.addItem(channel);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						try {
							int[] endLocation = new int[2];
							// 获取终点的坐标
							userGridView.getChildAt(
									userGridView.getLastVisiblePosition())
									.getLocationInWindow(endLocation);
							MoveAnim(moveImageView, startLocation, endLocation,
									channel, otherGridView);
							otherAdapter.setRemove(position);
						} catch (Exception localException) {
						}
					}
				}, 50L);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 点击ITEM移动动画
	 * 
	 * @param moveView
	 * @param startLocation
	 * @param endLocation
	 * @param moveChannel
	 * @param clickGridView
	 */
	private void MoveAnim(View moveView, int[] startLocation,
			int[] endLocation, final ChannelCategory moveChannel,
			final GridView clickGridView) {
		int[] initLocation = new int[2];
		// 获取传递过来的VIEW的坐标
		moveView.getLocationInWindow(initLocation);
		// 得到要移动的VIEW,并放入对应的容器中
		final ViewGroup moveViewGroup = getMoveViewGroup();
		final View mMoveView = getMoveView(moveViewGroup, moveView,
				initLocation);
		// 创建移动动画
		TranslateAnimation moveAnimation = new TranslateAnimation(
				startLocation[0], endLocation[0], startLocation[1],
				endLocation[1]);
		moveAnimation.setDuration(300L);// 动画时间
		// 动画配置
		AnimationSet moveAnimationSet = new AnimationSet(true);
		moveAnimationSet.setFillAfter(false);// 动画效果执行完毕后，View对象不保留在终止的位置
		moveAnimationSet.addAnimation(moveAnimation);
		mMoveView.startAnimation(moveAnimationSet);
		moveAnimationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				isMove = true;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				moveViewGroup.removeView(mMoveView);
				// instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
				if (clickGridView instanceof DragGrid) {
					otherAdapter.setVisible(true);
					otherAdapter.notifyDataSetChanged();
					userAdapter.remove();
				} else {
					userAdapter.setVisible(true);
					userAdapter.notifyDataSetChanged();
					otherAdapter.remove();
				}
				isMove = false;
			}
		});
	}

	/**
	 * 获取移动的VIEW，放入对应ViewGroup布局容器
	 * 
	 * @param viewGroup
	 * @param view
	 * @param initLocation
	 * @return
	 */
	private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
		int x = initLocation[0];
		int y = initLocation[1];
		viewGroup.addView(view);
		LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mLayoutParams.leftMargin = x;
		mLayoutParams.topMargin = y;
		view.setLayoutParams(mLayoutParams);
		return view;
	}

	/**
	 * 创建移动的ITEM对应的ViewGroup布局容器
	 */
	private ViewGroup getMoveViewGroup() {
		ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
		LinearLayout moveLinearLayout = new LinearLayout(this);
		moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		moveViewGroup.addView(moveLinearLayout);
		return moveLinearLayout;
	}

	/**
	 * 获取点击的Item的对应View，
	 * 
	 * @param view
	 * @return
	 */
	private ImageView getView(View view) {
		view.destroyDrawingCache();
		view.setDrawingCacheEnabled(true);
		Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
		view.setDrawingCacheEnabled(false);
		ImageView iv = new ImageView(this);
		iv.setImageBitmap(cache);
		return iv;
	}

	/** 退出时候保存选择后数据库的设置 */
	private void saveChannel() {

	}

	/* 返回操作 */
	@Override
	public void onBackPressed() {
		saveChannel();
		if (userAdapter.isListChanged()) {
			Intent intent = new Intent(getApplicationContext(), Home.class);
			setResult(Home.CHANNELRESULT, intent);
			finish();
			Log.d(TAG, "数据发生改变");
		} else {
			super.onBackPressed();
		}
		// overridePendingTransition(R.anim.slide_in_left,
		// R.anim.slide_out_right);
	}

}
