package com.jczb.car.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.adapter.ListViewNewsAdapter;
import com.jczb.car.adapter.ListViewSearchAdapter;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;
import com.jczb.car.bean.Notice;
import com.jczb.car.bean.SearchContent;
import com.jczb.car.common.StringUtils;
import com.jczb.car.common.UIHelper;

import android.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SearchContentActivity extends BaseActivity {
	/** 搜索按钮 */
	private Button btSearchPicture;
	/** 搜索的输入框 */
	private EditText etSearchFrame;
	/** 进度条 */
	private ProgressBar mProgressbar;

	private ListView lvSearch;
	private View lvSearch_footer;
	private ListViewSearchAdapter lvSearchAdapter;
	private List<Content> lvSearchData = new ArrayList<Content>();
	private TextView lvSearch_foot_more;
	private ProgressBar lvSearch_foot_progress;
	 private Handler mSearchHandler;
	 private int lvSumData;
	 
	private int curLvDataState;
	private String curSearchContent = "";
	private InputMethodManager imm;

	private final static int DATA_LOAD_ING = 0x001;
	private final static int DATA_LOAD_COMPLETE = 0x002;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_content);

		this.initView();

		this.initData();
	}

	/**
	 * 头部按钮展示
	 * 
	 * @param type
	 */
	private void headButtonSwitch(int type) {
		switch (type) {
		case DATA_LOAD_ING:
			btSearchPicture.setClickable(false);
			mProgressbar.setVisibility(View.VISIBLE);
			break;
		case DATA_LOAD_COMPLETE:
			btSearchPicture.setClickable(true);
			mProgressbar.setVisibility(View.GONE);
			break;
		}
	}


	// 初始化视图控件
	private void initView() {
		imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

		btSearchPicture = (Button) findViewById(R.id.search_content_btn);
		etSearchFrame = (EditText) findViewById(R.id.search_content_key);
		mProgressbar = (ProgressBar) findViewById(R.id.search_content_progress);

		btSearchPicture.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				etSearchFrame.clearFocus();
				curSearchContent = etSearchFrame.getText().toString();

			}
		});
		etSearchFrame
				.setOnFocusChangeListener(new View.OnFocusChangeListener() {
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
							imm.showSoftInput(v, 0);
						} else {
							imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
						}
					}
				});
		etSearchFrame.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER
						|| keyCode == KeyEvent.KEYCODE_SEARCH) {
					if (v.getTag() == null) {
						v.setTag(1);
						etSearchFrame.clearFocus();
						curSearchContent = etSearchFrame.getText().toString();

					} else {
						v.setTag(null);
					}
					return true;
				}
				return false;
			}
		});
		lvSearchAdapter = new ListViewSearchAdapter(this, lvSearchData, R.layout.search_result_item);
		lvSearch = (ListView)findViewById(R.id.search_content_listview);
		lvSearch.setVisibility(ListView.GONE);
    	lvSearch.addFooterView(lvSearch_footer);//添加底部视图  必须在setAdapter前
    	lvSearch.setAdapter(lvSearchAdapter); 
    	
    	
    	lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		//点击底部栏无效
        		if(view == lvSearch_footer) return;
        		
        		SearchContent res = null;
        		//判断是否是TextView
        		if(view instanceof TextView){
        			res = (SearchContent)view.getTag();
        		}else{
        			TextView title = (TextView)view.findViewById(R.id.search_result_news_title);
        			res = (SearchContent)title.getTag();
        		} 
        		if(res == null) return;
        	
		
		
        	}
    	});
	}
	private void initData() {
		// TODO Auto-generated method stub
		mSearchHandler = new Handler()
		{
			public void handleMessage(Message msg) {
				
				headButtonSwitch(DATA_LOAD_COMPLETE);

				if(msg.what >= 0){						
					ContentVo list = (ContentVo)msg.obj;
					Notice notice = list.getNotice();
					//处理listview数据
					switch (msg.arg1) {
					case UIHelper.LISTVIEW_ACTION_INIT:
					case UIHelper.LISTVIEW_ACTION_REFRESH:
					case UIHelper.LISTVIEW_ACTION_CHANGE_CATALOG:
						lvSumData = msg.what;
						lvSearchData.clear();//先清除原有数据
						lvSearchData.addAll(list.getContent());
						break;
					case UIHelper.LISTVIEW_ACTION_SCROLL:
						lvSumData += msg.what;
//						if(lvSearchData.size() > 0){
//							for(Result res1 : list.getResultlist()){
//								boolean b = false;
//								for(Result res2 : lvSearchData){
//									if(res1.getObjid() == res2.getObjid()){
//										b = true;
//										break;
//									}
//								}
//								if(!b) lvSearchData.add(res1);
//							}
//						}else{
//							lvSearchData.addAll(list.getResultlist());
//						}
						lvSearchData.addAll(list.getContent());
						break;
					}	
					
					if(msg.what < 20){
						curLvDataState = UIHelper.LISTVIEW_DATA_FULL;
						lvSearchAdapter.notifyDataSetChanged();
						lvSearch_foot_more.setText(R.string.load_full);
					}else if(msg.what == 20){					
						curLvDataState = UIHelper.LISTVIEW_DATA_MORE;
						lvSearchAdapter.notifyDataSetChanged();
						lvSearch_foot_more.setText(R.string.load_more);
					}
					//发送通知广播
					if(notice != null){
						//UIHelper.sendBroadCast(SearchContentActivity.this, notice);
					}
				}
				else if(msg.what == -1){
					//有异常--显示加载出错 & 弹出错误消息
					curLvDataState = UIHelper.LISTVIEW_DATA_MORE;
					lvSearch_foot_more.setText(R.string.load_error);
					((AppException)msg.obj).makeToast(SearchContentActivity.this);
				}
				if(lvSearchData.size()==0){
					curLvDataState = UIHelper.LISTVIEW_DATA_EMPTY;
					lvSearch_foot_more.setText(R.string.load_empty);
				}
				lvSearch_foot_progress.setVisibility(View.GONE);
				if(msg.arg1 != UIHelper.LISTVIEW_ACTION_SCROLL){
					lvSearch.setSelection(0);//返回头部
				}
			}
		};
		
	}
	/**
     * 线程加载收藏数据
     * @param type 0:全部收藏 1:软件 2:话题 3:博客 4:新闻 5:代码
     * @param pageIndex 当前页数
     * @param handler 处理器
     * @param action 动作标识
     */
	private void loadLvSearchData(final String catalog,final int pageIndex,final Handler handler,final int action){  
		if(StringUtils.isEmpty(curSearchContent)){
			UIHelper.ToastMessage(SearchContentActivity.this, "请输入搜索内容");
			return;
		}
		
		headButtonSwitch(DATA_LOAD_ING);
		lvSearch.setVisibility(ListView.VISIBLE);
		
		new Thread(){
			public void run() {
				Message msg = new Message();
				Map<String, String> searchContentMap=new HashMap<String,String>();
				searchContentMap.put("id", "丁国华");
				
				try {
					/*发现*/
					ContentVo searchList = ((AppContext)getApplication()).discover(true, 4);
					
					//msg.what = searchList.getPageSize();
					msg.obj = searchList;
	            } catch (AppException e) {
	            	e.printStackTrace();
	            	msg.what = -1;
	            	msg.obj = e;
	            }
				msg.arg1 = action;//告知handler当前action
				//if(curSearchCatalog.equals(catalog))
				//	handler.sendMessage(msg);
			}
		}.start();
	} 
	private View.OnClickListener searchBtnClick(final Button btn,final String catalog){
    	return new View.OnClickListener() {
			public void onClick(View v) {
				btSearchPicture.setEnabled(true);
				
				//开始搜索
				etSearchFrame.clearFocus();
				curSearchContent = etSearchFrame.getText().toString();
				//curSearchCatalog = catalog;
				loadLvSearchData(catalog, 0, mSearchHandler, UIHelper.LISTVIEW_ACTION_CHANGE_CATALOG);		    	
			}
		};
    }
}


