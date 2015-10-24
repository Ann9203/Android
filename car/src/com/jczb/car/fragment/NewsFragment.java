package com.jczb.car.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Source;
import org.w3c.dom.Text;
import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.adapter.ListViewNewsAdapter;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;
import com.jczb.car.bean.NewsEntity;
import com.jczb.car.bean.Notice;
import com.jczb.car.common.StringUtils;
import com.jczb.car.common.UIHelper;
import com.jczb.car.ui.DiscoveryActivity;
import com.jczb.car.ui.Home;
import com.jczb.car.widget.NewDataToast;
import com.jczb.car.widget.PullToRefreshListView;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewsFragment extends Fragment {
	private final static String TAG = "NewsFragment";
	Activity activity;

	String text; // 频道名称
	int channel_id; // 频道ID
	ImageView detail_loading;
	public final static int SET_NEWSLIST = 0;
	// Toast提示框
	private RelativeLayout notify_view;
	private TextView notify_view_text;
	private AppContext appContext;// 全局Context,封装好的接口类
	private ListViewNewsAdapter lvNewsAdapter; // 列表适配器
	private View lvNews_footer; // 底部视图
	private TextView lvNews_foot_more; // 底部加载更多视图
	private ProgressBar lvNews_foot_progress; // 底部加载更多进度条视图
	private PullToRefreshListView lvNews; // 下拉刷新列表
	private Handler lvNewsHandler; // 频道新闻控制器

	// private int curNewsCatalog =ChannelMsgList.CATALOG_ALL;
	private int curNewsCatalog = 1;
	private int lvNewsSumData; // 列表中新闻总共条数
	private List<Content> msgs = new ArrayList<Content>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle args = getArguments();
		text = args != null ? args.getString("name") : "";
		channel_id = args != null ? args.getInt("id", 0) : 0;
		// initData();

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		this.activity = activity;
		super.onAttach(activity);
	}

	/** 此方法意思为fragment是否可见 ,可见时候加载数据 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {

		super.setUserVisibleHint(isVisibleToUser);
	}

	private View rootView;// 缓存Fragment view

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// // TODO Auto-generated method stub

		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			lvNewsAdapter = new ListViewNewsAdapter(activity, msgs,
					R.layout.channelmsg_item);
			/* fragment初始化布局页面news_fragment */
			rootView = LayoutInflater.from(getActivity()).inflate(
					R.layout.news_fragment, null);
			/* fragment初始化加载更多视图 */
			lvNews_footer = LayoutInflater.from(getActivity()).inflate(
					R.layout.listview_footer, null);

			lvNews_foot_more = (TextView) lvNews_footer
					.findViewById(R.id.listview_foot_more);

			lvNews_foot_progress = (ProgressBar) lvNews_footer
					.findViewById(R.id.listview_foot_progress);
			lvNews = (PullToRefreshListView) rootView
					.findViewById(R.id.fragment_channel_lv);

			lvNews.addFooterView(lvNews_footer);// 添加底部视图 必须在setAdapter前
			lvNews.setAdapter(lvNewsAdapter);

			/* 列表Item单击事件 */
			lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Toast.makeText(activity, "click", 1);
				}
			});

			/* 列表滑动加载更多 */
			lvNews.setOnScrollListener(new AbsListView.OnScrollListener() {
				public void onScrollStateChanged(AbsListView view,
						int scrollState) {
					lvNews.onScrollStateChanged(view, scrollState);

					// 数据为空--不用继续下面代码了
					if (msgs.isEmpty())
						return;

					// 判断是否滚动到底部
					boolean scrollEnd = false;
					try {
						if (view.getPositionForView(lvNews_footer) == view
								.getLastVisiblePosition())
							scrollEnd = true;
					} catch (Exception e) {
						scrollEnd = false;
					}

					int lvDataState = StringUtils.toInt(lvNews.getTag());
					if (scrollEnd && lvDataState == UIHelper.LISTVIEW_DATA_MORE) {
						lvNews.setTag(UIHelper.LISTVIEW_DATA_LOADING);
						lvNews_foot_more.setText(R.string.load_ing);
						lvNews_foot_progress.setVisibility(View.VISIBLE);
						// 当前pageIndex
						int pageIndex = lvNewsSumData / AppContext.PAGE_SIZE;
						loadLvNewsData(curNewsCatalog, pageIndex,
								lvNewsHandler, UIHelper.LISTVIEW_ACTION_SCROLL);
					}
				}

				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					lvNews.onScroll(view, firstVisibleItem, visibleItemCount,
							totalItemCount);
				}
			});

			lvNews.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
				public void onRefresh() {
					loadLvNewsData(curNewsCatalog, 0, lvNewsHandler,
							UIHelper.LISTVIEW_ACTION_REFRESH);
				}
			});

			/* 绑定数据 */
			lvNewsHandler = this.getLvHandler(lvNews, lvNewsAdapter,
					lvNews_foot_more, lvNews_foot_progress,
					AppContext.PAGE_SIZE);

			// 加载新闻数据
			if (msgs.isEmpty()) {
				loadLvNewsData(curNewsCatalog, 0, lvNewsHandler,
						UIHelper.LISTVIEW_ACTION_INIT);
			}

		}

		return rootView;
	}

	/**
	 * 获取listview的初始化Handler
	 * 
	 * @param lv
	 * @param adapter
	 * @return
	 */
	private Handler getLvHandler(final PullToRefreshListView lv,
			final BaseAdapter adapter, final TextView more,
			final ProgressBar progress, final int pageSize) {
		return new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what >= 0) {
					// listview数据处理
					Notice notice = handleLvData(msg.what, msg.obj, msg.arg2,
							msg.arg1);

					if (msg.what < pageSize) {
						lv.setTag(UIHelper.LISTVIEW_DATA_FULL);
						adapter.notifyDataSetChanged();
						more.setText(R.string.load_full);
					} else if (msg.what == pageSize) {
						lv.setTag(UIHelper.LISTVIEW_DATA_MORE);
						adapter.notifyDataSetChanged();

						more.setText(R.string.load_more);

					}
				} else if (msg.what == -1) {
					// 有异常--显示加载出错 & 弹出错误消息
					lv.setTag(UIHelper.LISTVIEW_DATA_MORE);
					more.setText(R.string.load_error);
					// ((AppException) msg.obj).makeToast(Home.this);
				}

				if (adapter.getCount() == 0) {
					lv.setTag(UIHelper.LISTVIEW_DATA_EMPTY);
					more.setText(R.string.load_empty);
				}
				progress.setVisibility(ProgressBar.GONE);

				lvNews_foot_progress.setVisibility(ProgressBar.GONE);

				if (msg.arg1 == UIHelper.LISTVIEW_ACTION_REFRESH) {
					lv.onRefreshComplete(getString(R.string.pull_to_refresh_update)
							+ new Date().toLocaleString());
					lv.setSelection(0);
				}
				// else if (msg.arg1 == UIHelper.LISTVIEW_ACTION_CHANGE_CATALOG)
				// {
				// lv.onRefreshComplete();
				// lv.setSelection(0);
				// }

			}
		};
	}

	/**
	 * listview数据处理
	 * 
	 * @param what
	 *            数量
	 * @param obj
	 *            数据
	 * @param objtype
	 *            数据类型
	 * @param actiontype
	 *            操作类型
	 * @return notice 通知信息
	 */
	private Notice handleLvData(int what, Object obj, int objtype,
			int actiontype) {
		Notice notice = null;

		switch (actiontype) {
		case UIHelper.LISTVIEW_ACTION_INIT:
		case UIHelper.LISTVIEW_ACTION_REFRESH:
			// case UIHelper.LISTVIEW_ACTION_CHANGE_CATALOG:

			int newdata = 0;// 新加载数据-只有刷新动作才会使用到

			ContentVo nlist = (ContentVo) obj;
			notice = nlist.getNotice();
			lvNewsSumData = what;

			if (msgs.size() > 0) {
				for (Content news1 : nlist.getContent()) {
					boolean b = false;
					for (Content news2 : msgs) {
						if (news1.getId() == news2.getId()) {
							b = true;
							break;
						}
					}
					if (!b)
						newdata++;
				}
			} else {
				newdata = what;
			}

			msgs.clear();// 先清除原有数据
			msgs.addAll(nlist.getContent());

			/* 更新提示 */
			// if (actiontype == UIHelper.LISTVIEW_ACTION_REFRESH) {
			// // 提示新加载数据
			// if (newdata > 0) {
			// NewDataToast
			// .makeText(
			// this,
			// getString(R.string.new_data_toast_message,
			// newdata), appContext.isAppSound())
			// .show();
			// }
			// else {
			// NewDataToast.makeText(this,
			// getString(R.string.new_data_toast_none), false)
			// .show();
			// }
			// }
			// break;

		case UIHelper.LISTVIEW_ACTION_SCROLL:
			ContentVo list = (ContentVo) obj;
			// notice = list.getNotice();
			lvNewsSumData += what;
			// if (msgs.size() > 0) {
			// for (ChannelMsg news1 : list.getChannelMsglist()) {
			// boolean b = false;
			// for (ChannelMsg news2 : msgs) {
			// if (news1.getId() == news2.getId()) {
			// b = true;
			// break;
			// }
			// }
			// if (!b)
			// msgs.add(news1);
			// }
			// } else {
			// msgs.addAll(list.getChannelMsglist());
			// }
			msgs.addAll(list.getContent());
			break;
		}

		return notice;

	};

	/**
	 * 线程加载频道新闻数据
	 * 
	 * @param catalog
	 *            分类
	 * @param pageIndex
	 *            当前页数
	 * @param handler
	 *            处理器
	 * @param action
	 *            动作标识
	 */
	private void loadLvNewsData(final int catalog, final int pageIndex,
			final Handler handler, final int action) {
		lvNews_foot_progress.setVisibility(ProgressBar.VISIBLE);
		new Thread() {
			public void run() {
				Message msg = new Message();
				boolean isRefresh = false;
				AppContext appContext = (AppContext) getActivity()
						.getApplication();

				if (action == UIHelper.LISTVIEW_ACTION_REFRESH
						|| action == UIHelper.LISTVIEW_ACTION_SCROLL)
					isRefresh = true;
				try {

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("id", channel_id);
					params.put("type", 0);

					/* 根据频道id查询新闻信息 */
					ContentVo contentInfo = appContext.getContentByCategory(
							params, true, 1);

					List<Content> contentList = contentInfo.getContent();

					/* 测试数据 */
					// ChannelMsgList ChaMsgLt = new ChannelMsgList();
					// List<ChannelMsg> channelMsgs = new
					// ArrayList<ChannelMsg>();
					//
					// channelMsgs.add(new ChannelMsg("0体育", "张三", "国际赛事"));
					// channelMsgs.add(new ChannelMsg("1教育", "李四", "传统教育理念"));
					// channelMsgs.add(new ChannelMsg("2科普", "王五", "原子理论"));
					// channelMsgs.add(new ChannelMsg("3民生", "赵六", "三农经济带动"));
					// channelMsgs.add(new ChannelMsg("4体育", "张三", "国际赛事"));
					// channelMsgs.add(new ChannelMsg("5教育", "李四", "传统教育理念"));
					// channelMsgs.add(new ChannelMsg("6科普", "王五", "原子理论"));
					// channelMsgs.add(new ChannelMsg("3民生", "赵六", "三农经济带动"));
					// channelMsgs.add(new ChannelMsg("0体育", "张三", "国际赛事"));
					// channelMsgs.add(new ChannelMsg("1教育", "李四", "传统教育理念"));
					// channelMsgs.add(new ChannelMsg("2科普", "王五", "原子理论"));
					// channelMsgs.add(new ChannelMsg("3民生", "赵六", "三农经济带动"));
					// channelMsgs.add(new ChannelMsg("0体育", "张三", "国际赛事"));
					// channelMsgs.add(new ChannelMsg("11教育", "李四", "传统教育理念"));
					// channelMsgs.add(new ChannelMsg("12科普", "王五", "原子理论"));
					// channelMsgs.add(new ChannelMsg("13民生", "赵六", "三农经济带动"));
					// ChaMsgLt.setChannelMsglist(channelMsgs);

					// msg.what = ChaMsgLt.getPageSize();
					msg.what = contentList.size();
					msg.obj = contentInfo;

				} catch (Exception e) {
					e.printStackTrace();
					msg.what = -1;
					msg.obj = e;
				}
				msg.arg1 = action;
				// msg.arg2 = UIHelper.LISTVIEW_DATATYPE_NEWS;
				// if (curNewsCatalog == catalog)
				handler.sendMessage(msg);
			}
		}.start();
	}

	/* 初始化通知栏目 */
	private void initNotify() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// notify_view_text.setText(String.format(getString(R.string.ss_pattern_update),
				// 10));
				notify_view.setVisibility(View.VISIBLE);
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						notify_view.setVisibility(View.GONE);
					}
				}, 2000);
			}
		}, 1000);
	}

	/* 摧毁视图 */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d("onDestroyView", "channel_id = " + channel_id);
		lvNewsAdapter = null;
	}

	/* 摧毁该Fragment，一般是FragmentActivity 被摧毁的时候伴随着摧毁 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "channel_id = " + channel_id);
	}
}
