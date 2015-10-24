package com.jczb.checkpoint.ui.adapter;



import java.util.List;
import java.util.Map;

import com.jczb.checkpoint.common.ImageLoader;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.SimpleAdapter.ViewBinder;



public class MyAdapter extends SimpleAdapter {
	
	private int[] mTo;
	private String[] mFrom;
	private ViewBinder mViewBinder;
	private List<? extends Map<String, ?>> mData;
	private int mResource;
	private LayoutInflater mInflater;

	ImageLoader mImageLoader;
	private ListView mListView;
	public MyAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to, ListView listView) {
		super(context, data, resource, from, to);
		mData = data;
		mResource  = resource;
		mFrom = from;
		mTo = to;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// 王向阳采用新的图片加载工具
		mImageLoader = ImageLoader.getInstance(context);
		mListView = listView;
		mListView.setOnScrollListener(onScrollListener);
	}
	AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				mImageLoader.lock();
				break;
			case OnScrollListener.SCROLL_STATE_IDLE:
				mImageLoader.unlock();
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				mImageLoader.lock();
				break;
			default:
				break;
			}

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub

		}
	};
	public int getCount() {
		// 通过此项决定ListView一共有多少Item
		return mData.size();
	}

	/**
	 * @see android.widget.Adapter#getView(int, View, ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		return createViewFromResource(position, convertView, parent, mResource);
	}

	private View createViewFromResource(int position, View convertView,
			ViewGroup parent, int resource) {
		View v;
		if (convertView == null) {
			v = mInflater.inflate(resource, parent, false);
			final int[] to = mTo;
			final int count = to.length;
			final View[] holder = new View[count];
			for (int i = 0; i < count; i++) {
				holder[i] = v.findViewById(to[i]);
			}
			v.setTag(holder);
		} else {
			v = convertView; // ???????????????????????
		}
		bindView(position, v);
		return v;
	}

	private void bindView(int position, View view) {
		final Map dataSet = mData.get(position);
		if (dataSet == null) {
			return;
		}
		// Load the image and set it on the ImageView
		// Set the text on the TextView
		final ViewBinder binder = mViewBinder;
		final View[] holder = (View[]) view.getTag();
		final String[] from = mFrom;
		final int[] to = mTo;
		final int count = to.length;
		for (int i = 0; i < count; i++) {
			final View v = holder[i];
			if (v != null) {
				final Object data = dataSet.get(from[i]);
				String text = data == null ? "" : data.toString();
				if (text == null) {
					text = "";
				}
				boolean bound = false;
				if (binder != null) {
					bound = binder.setViewValue(v, data, text);
				}
				if (!bound) {
					if (v instanceof TextView) {
						// Note: keep the instanceof TextView check at the
						// bottom of these
						// ifs since a lot of views are TextViews (e.g.
						// CheckBoxes).
						setViewText((TextView) v, text);
				
						
					} else if (v instanceof ImageView) {
						if (data instanceof Integer) {
//							setViewImage((ImageView) v, (Integer) data);
//							 mImageFetcher.loadImage((Integer) data,(ImageView) v);
							 //bmpManager.loadBitmap(text, (ImageView)v);
							mImageLoader.addTask(text, (ImageView) v);
						} else if (data instanceof Drawable) {
//							((ImageView) v).setImageDrawable((Drawable) data);
							 //bmpManager.loadBitmap(text, (ImageView)v);
							mImageLoader.addTask(text, (ImageView) v);
						} else {
//							setViewImage((ImageView) v, text);
//							 mImageFetcher.loadImage(text,(ImageView) v);
							 //bmpManager.loadBitmap(text, (ImageView)v);
							mImageLoader.addTask(text, (ImageView) v);
						} 
					} else if (v instanceof RatingBar) {
						float fl = 5f;
						try {
							fl = Float.parseFloat(text);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						((RatingBar) v).setRating(fl);
					} else if (v instanceof Button) {

					} else {
						throw new IllegalStateException(
								v.getClass().getName()
										+ " is not a "
										+ " view that can be bounds by this SimpleAdapter");
					}
				}
			}
		}
	}
}
