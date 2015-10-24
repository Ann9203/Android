package com.jczb.car.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.adapter.ListViewNewsAdapter;
import com.jczb.car.adapter.NewsCommentAdapter;
import com.jczb.car.adapter.NewsFragmentPagerAdapter;
import com.jczb.car.bean.ChannelContentComment;
import com.jczb.car.bean.ChannelContentCommentVo;
import com.jczb.car.bean.ChannelContentComments;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;
import com.jczb.car.bean.MyCommentVo;
import com.jczb.car.bean.Notice;
import com.jczb.car.common.StringUtils;
import com.jczb.car.common.UIHelper;
import com.jczb.car.widget.PullToRefreshListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ContentEvaluationActivity extends Activity implements
		OnClickListener {
	private TextView comment_news_title, comment_news_type,
			comment_news_praise, comment_news_disagree, comment_news_comment;
	private LinearLayout back;
	private View lvNews_footer; // 底部视图
	private TextView lvNews_foot_more; // 底部加载更多视图
	private ProgressBar lvNews_foot_progress; // 底部加载更多进度条视图
	private PullToRefreshListView lvNews; // 下拉刷新列表
	private NewsCommentAdapter NewComAdapter; // 评论列表适配器

	/* 频道评论模型 */
	private ChannelContentCommentVo ChCComment = null;
	/* 传参用的集合 */
	private Map<String, Object> params = new HashMap<String, Object>();
	/* 用来获得服务器接口的评论内容集合 */
	private List<ChannelContentComments> CommentList = new ArrayList<ChannelContentComments>();

	/* msgs 供全局、测试用 */
	// private List<ChannelContentComments> msgs = new
	// ArrayList<ChannelContentComments>();
	private int curNewsCatalog = 1;
	private int lvNewsSumData; // 列表中评论总条数
	private Handler lvNewsHandler; // 频道新闻控制器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.channel_content_evaluation);

		initView();

		this.initData();
		// 启动子线程,获取服务器数据
		// new Thread(new ContentEvlThread()).start();

	}

	private void initView() {
		// TODO Auto-generated method stub
		// 初始化控件
		comment_news_title = (TextView) findViewById(R.id.news_evaluation_title);
		comment_news_type = (TextView) findViewById(R.id.news_evaluation_category);
		comment_news_praise = (TextView) findViewById(R.id.news_evaluation_praise);
		comment_news_disagree = (TextView) findViewById(R.id.news_evaluation_disagree);
		comment_news_comment = (TextView) findViewById(R.id.news_evaluation_comment);

		/* 返回按钮的绑定和响应事件处理 */
		back = (LinearLayout) findViewById(R.id.evaluation_back);
		back.setOnClickListener(this);
	}

	private void initData() {
		// 初始化listview控件
		this.initNewsListView();

		/* 绑定数据 */
		lvNewsHandler = this.getLvHandler(lvNews, NewComAdapter,
				lvNews_foot_more, lvNews_foot_progress, AppContext.PAGE_SIZE);

		// 加载新闻数据
		if (CommentList.isEmpty()) {
			loadLvNewsData(curNewsCatalog, 0, lvNewsHandler,
					UIHelper.LISTVIEW_ACTION_INIT);
		}
	}

	private void initNewsListView() {
		NewComAdapter = new NewsCommentAdapter(this, CommentList,
				R.layout.channel_content_evaluation_item);

		// /* fragment初始化加载更多视图 */
		lvNews_footer = getLayoutInflater().inflate(R.layout.listview_footer,
				null);

		lvNews_foot_more = (TextView) lvNews_footer
				.findViewById(R.id.listview_foot_more);

		lvNews_foot_progress = (ProgressBar) lvNews_footer
				.findViewById(R.id.listview_foot_progress);
		lvNews = (PullToRefreshListView) findViewById(R.id.content_evaluation_lv);

		lvNews.addFooterView(lvNews_footer);// 添加底部视图 必须在setAdapter前
		lvNews.setAdapter(NewComAdapter);

		/* 列表滑动加载更多 */
		lvNews.setOnScrollListener(new AbsListView.OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				lvNews.onScrollStateChanged(view, scrollState);

				// 数据为空--不用继续下面代码了
				if (CommentList.isEmpty())
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
					loadLvNewsData(curNewsCatalog, pageIndex, lvNewsHandler,
							UIHelper.LISTVIEW_ACTION_SCROLL);
				}
			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				lvNews.onScroll(view, firstVisibleItem, visibleItemCount,
						totalItemCount);
			}
		});

	}

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

				// 拿到频道id和类型id
				Intent intent = getIntent();
				Bundle bundle = intent.getExtras();

				// 实例化map类型的参数，并赋值频道、类型id
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("Id", bundle.getString("channelnews_id"));
				params.put("channelType", bundle.getString("channelType"));

				AppContext appContext = (AppContext) getApplication();

				/* 下拉刷新||活动加载更多触发 */
				if (action == UIHelper.LISTVIEW_ACTION_REFRESH
						|| action == UIHelper.LISTVIEW_ACTION_SCROLL)
					isRefresh = true;
				try {

					// 获取服务器数据
					ChCComment = appContext.getChannelComment(params, true, 1);

					CommentList = ChCComment.getContent().get(0).getComment();

					/* 根据频道id查询新闻信息 */
					// ContentVo contentInfo = appContext.getContentByCategory(
					// params, true, 1);
					//
					// List<Content> contentList = contentInfo.getContent();

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
					msg.what = CommentList.size();
					msg.obj = ChCComment;

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

					/* 线程绑定数据 */
					ChCComment = (ChannelContentCommentVo) msg.obj;
					/* 相关信息内容 */
					CommentList = ChCComment.getContent().get(0).getComment();

					comment_news_title.setText(ChCComment.getContent().get(0)
							.getTitle());
					comment_news_type.setText(ChCComment.getContent().get(0)
							.getChannelName());
					// comment_news_playicon.setText(ChCComment.getContent().get(0)
					// .getCount()
					// + "");
					comment_news_comment.setText(ChCComment.getContent().get(0)
							.getCommentCount()
							+ "");

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

			ChannelContentCommentVo nlist = (ChannelContentCommentVo) obj;
			notice = nlist.getNotice();
			lvNewsSumData = what;

			if (CommentList.size() > 0) {
				for (ChannelContentComments news1 : nlist.getContent().get(0)
						.getComment()) {
					boolean b = false;
					// for (ChannelContentComments news2 : msgs) {
					// if (news1.getId() == news2.getId()) {
					// b = true;
					// break;
					// }
					// }
					if (!b)
						newdata++;
				}
			} else {
				newdata = what;
			}

			CommentList.clear();// 先清除原有数据
			CommentList.addAll(nlist.getContent().get(0).getComment());
			break;

		case UIHelper.LISTVIEW_ACTION_SCROLL:
			ChannelContentCommentVo list = (ChannelContentCommentVo) obj;
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
			CommentList.addAll(list.getContent().get(0).getComment());
			break;
		}

		return notice;

	};

	public class ContentEvlThread implements Runnable {
		public void run() {
			Message msg = new Message();

			// 拿到频道id和类型id
			Intent intent = getIntent();
			Bundle bundle = intent.getExtras();

			// 实例化map类型的参数，并赋值频道、类型id
			Map<String, String> parmas = new HashMap<String, String>();
			parmas.put("Id", bundle.getString("channelnews_id"));
			parmas.put("channelType", bundle.getString("channelType"));

			// 获取全局对象Application
			AppContext appContext = (AppContext) getApplication();
			try {
				// 获取服务器数据
				ChCComment = appContext.getChannelComment(params, true, 1);

				// 返回contentVo则将消息的what值为1，为空what为-1，异常为0
				if (ChCComment != null) {
					msg.what = 1;
					msg.obj = ChCComment;
				} else {
					msg.what = -1;
				}

			} catch (AppException e) {
				msg.what = 0;
				e.printStackTrace();
			}
			mHandler.sendMessage(msg);

		}
	}

	/* 子线程-解析数据 */
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case -1:
				Toast.makeText(ContentEvaluationActivity.this, "服务器连接失败!",
						Toast.LENGTH_SHORT).show();
				break;
			case -2:
				Toast.makeText(ContentEvaluationActivity.this, "哎呀，出错啦...",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				/* 新闻主题内容 */
				ChCComment = (ChannelContentCommentVo) msg.obj;
				/* 相关信息内容 */
				CommentList = ChCComment.getContent().get(0).getComment();

				comment_news_title.setText(ChCComment.getContent().get(0)
						.getTitle());
				comment_news_type.setText(ChCComment.getContent().get(0)
						.getChannelName());
				// comment_news_playicon.setText(ChCComment.getContent().get(0)
				// .getCount()
				// + "");
				comment_news_comment.setText(ChCComment.getContent().get(0)
						.getCommentCount()
						+ "");

				break;

			default:
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.evaluation_back:
			this.finish();
			break;

		default:
			break;
		}
	}

}
