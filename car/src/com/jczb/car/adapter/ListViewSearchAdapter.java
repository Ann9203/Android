package com.jczb.car.adapter;

import java.util.List;
/**
 * 搜索资源Adapter类
 * @author 丁国华 
 * @version 1.0
 * @created 2015年9月9日 15:14:26
 */


import com.jczb.car.R;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;
import com.jczb.car.bean.SearchContent;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewSearchAdapter extends BaseAdapter {
	
	private Context context; //运行上下文
	private List<Content> listItems;//数据集合
	private LayoutInflater listContainer; //视图容器
	private int itemViewResource;//自定义项视图源 
	
	static class ListItemView{				//自定义控件集合  
		public TextView title;
        public ImageView isCollect;  
	    public TextView channelName;
	    public TextView browseNumber; 
	    public TextView commentCount;
} 
	/**
	 * 实例化Adapter
	 * @param context
	 * @param data
	 * @param resource
	 */
	
	public ListViewSearchAdapter(Context context, List<Content> data,int resource) {
		this.context = context;			
		this.listContainer = LayoutInflater.from(context);	//创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
	}
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Log.d("method", "getView");
		
				//自定义视图
				ListItemView  listItemView = null;
				
				if (convertView == null) {
					//获取list_item布局文件的视图
					convertView = listContainer.inflate(this.itemViewResource, null);
					
					listItemView = new ListItemView();
					//获取控件对象
					listItemView.title=(TextView)convertView.findViewById(R.id.search_result_news_title);
					listItemView.isCollect=(ImageView)convertView.findViewById(R.id.search_result_news_collecstar);
					listItemView.channelName=(TextView)convertView.findViewById(R.id.search_result_news_category);
					listItemView.browseNumber=(TextView)convertView.findViewById(R.id.search_result_news_playIcon);
					listItemView.commentCount=(TextView)convertView.findViewById(R.id.search_result_news_comment);
					//设置控件集到convertView
					convertView.setTag(listItemView);
				}else {
					listItemView = (ListItemView)convertView.getTag();
				}	
				
				//设置文字和图片
//				ContentVo sct= listItems.get(position);
//				listItemView.title.setText(sct.gett());
//				listItemView.channelName.setText(sct.getChannelName());
//				listItemView.browseNumber.setText(sct.getBrowseNumber()+"");
//				listItemView.commentCount.setText(sct.getCommentCount()+"");
//				
				
				return convertView;
}
	
	

}
