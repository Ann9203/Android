package com.jczb.car.adapter;

import java.util.List;
import com.jczb.car.R;
import com.jczb.car.bean.Content;
import com.jczb.car.common.StringUtils;
import com.jczb.car.ui.ContentEvaluationActivity;
import com.jczb.car.ui.VedioInfoActivity;
import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 首页——新闻Adapter类
 * 
 * @author zhanghui
 * @version 1.0
 * @created 2015-3-21
 */
public class ListViewNewsAdapter extends BaseAdapter {
	private Context context;// 运行上下文
	private List<Content> listItems;// 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源

	static class ListItemView { // 自定义控件集合
		public TextView title;
		public TextView channelType;
		public TextView playIcon;
		public TextView comment;
		public LinearLayout btnCollect, btnComment, btnShare;
	}

	/**
	 * 实例化Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public ListViewNewsAdapter(Context context, List<Content> data, int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
	}

	public int getCount() {
		return listItems.size();
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	/**
	 * ListView Item设置
	 */
	public View getView(int position, View convertView, ViewGroup parent) {

		// 自定义视图
		ListItemView listItemView = null;

		if (convertView == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);

			listItemView = new ListItemView();
			// 获取控件对象
			listItemView.title = (TextView) convertView
					.findViewById(R.id.channel_news_title);
			/* 标题栏保持单行、超过部分省略号代替 */
			listItemView.title.setSingleLine();
			listItemView.title.setEllipsize(TruncateAt.valueOf("END"));

			listItemView.channelType = (TextView) convertView
					.findViewById(R.id.channel_news_category);
			listItemView.playIcon = (TextView) convertView
					.findViewById(R.id.channel_news_playIcon);
			listItemView.comment = (TextView) convertView
					.findViewById(R.id.channel_news_comment);
			listItemView.btnCollect = (LinearLayout) convertView
					.findViewById(R.id.LL_collecstar);
			listItemView.btnComment = (LinearLayout) convertView
					.findViewById(R.id.LL_comment);
			listItemView.btnShare = (LinearLayout) convertView
					.findViewById(R.id.LL_Share);

			// item标题响应单击事件
			listItemView.title.setOnClickListener(new ScanInfo(position));
			// 收藏
			listItemView.btnCollect.setOnClickListener(new Collect(position));
			// 评论
			listItemView.btnComment.setOnClickListener(new Comment(position));
			// 转载
			listItemView.btnShare.setOnClickListener(new Share(position));

			// 设置控件集到convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// 设置文字和图片
		Content news = listItems.get(position);

		listItemView.title.setText(news.getTitle());
		listItemView.title.setTag(news);// 设置隐藏参数(实体类)
		listItemView.channelType.setText(news.getChannelName());
		listItemView.playIcon.setText(news.getBrowseNumber() + "");
		listItemView.comment.setText(news.getCommentbrowseNumber() + "");

		// if(StringUtils.isToday(news.getPubDate()))
		// listItemView.flag.setVisibility(View.VISIBLE);
		// else
		// listItemView.flag.setVisibility(View.GONE);

		return convertView;
	}

	class ScanInfo implements OnClickListener {

		public int position;

		public ScanInfo(int pos) {
			position = pos;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(context, VedioInfoActivity.class);

			/* 将需要使用的数据添加到bundle中，供详情、评论页面使用 */
			Bundle bundle = new Bundle();
			bundle.putString("channelnews_id", listItems.get(position).getId()
					+ "");
			bundle.putString("channelType", listItems.get(position)
					.getChannelType() + "");

			intent.putExtras(bundle);

			context.startActivity(intent);
		}
	}

	class Collect implements OnClickListener {

		public int position;

		public Collect(int pos) {
			position = pos;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}

	}

	class Comment implements OnClickListener {

		public int position;

		public Comment(int pos) {
			position = pos;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(context, ContentEvaluationActivity.class);

			/* 将需要使用的数据添加到bundle中，供商品详情页面使用 */
			Bundle bundle = new Bundle();
			bundle.putString("channelnews_id", listItems.get(position).getId()
					+ "");
			bundle.putString("channelType", listItems.get(position)
					.getChannelType() + "");

			intent.putExtras(bundle);

			context.startActivity(intent);
		}
	}

	class Share implements OnClickListener {

		public int position;

		public Share(int pos) {
			position = pos;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	}

}