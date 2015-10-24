package com.ithei.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.day04_news.R;
import com.ithei.domain.newsbean.NewsBean;
import com.loopj.android.image.SmartImageView;

public class newsAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<NewsBean> array;

	// 写一个构造方法
	public newsAdapter(Context context, ArrayList<NewsBean> array) {
		this.mContext = context;
		this.array = array;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return array.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return array.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = View.inflate(mContext, R.layout.itheima_content, null);
		}

		NewsBean nb = array.get(position);
		TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
		TextView tv_des = (TextView) view.findViewById(R.id.tv_des);
		TextView tv_comment = (TextView) view.findViewById(R.id.tv_comment);
		TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
		SmartImageView siv_img = (SmartImageView) view
				.findViewById(R.id.smi_img);
		tv_title.setText(nb.title);
		tv_des.setText(nb.des);
		tv_comment.setText("评论"+nb.comment);
		siv_img.setImageUrl(nb.icon_url);
		switch (nb.type) {
		case 0:
			tv_type.setText("头条");
			break;
		case 1:
			tv_type.setText("娱乐");
			break;
		case 2:
			tv_type.setText("体育");
			break;
		default:
			break;
		}

		return view;
	}

}
