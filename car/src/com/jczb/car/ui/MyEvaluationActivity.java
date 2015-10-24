package com.jczb.car.ui;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.bean.MyComment;
import com.jczb.car.bean.MyCommentVo;
import com.jczb.car.bean.MyEvaluation;
import com.jczb.car.bean.SiteUser;

/**
 * 我的评论功能页面
 * 
 * @author 吴利昌
 * @date 2015-9-6下午1:50:20
 */
public class MyEvaluationActivity extends Activity {

	// 声明控件
	// 5条评论
	private LinearLayout llEvaluation1;
	private LinearLayout llEvaluation2;
	private LinearLayout llEvaluation3;
	private LinearLayout llEvaluation4;
	private LinearLayout llEvaluation5;
	private TextView tvEvaluation1;
	private TextView tvEvaluation2;
	private TextView tvEvaluation3;
	private TextView tvEvaluation4;
	private TextView tvEvaluation5;
	private ImageView ivHeadImg1;
	private ImageView ivHeadImg2;
	private ImageView ivHeadImg3;
	private ImageView ivHeadImg4;
	private ImageView ivHeadImg5;
	private TextView tvDate1;
	private TextView tvDate2;
	private TextView tvDate3;
	private TextView tvDate4;
	private TextView tvDate5;

	// 评论上面的控件
	private ImageView ivCarImg;
	private TextView tvNewsTile;
	private TextView tvVideoNum;
	private TextView tvEvaluationNum;
	private TextView tvNewCar;

	// ListView
	private ListView listView;

	private static MyCommentVo commentVo;
	private static boolean isGetMyComment = false;
	private static SiteUser siteUser;
	
	//定义需要的参数集合
	Map<String, Object> map1 = new HashMap<String, Object>();
	Map<String, Object> map2 = new HashMap<String, Object>();
	Map<String, Object> map3 = new HashMap<String, Object>();
	Map<String, Object> map4 = new HashMap<String, Object>();
	Map<String, Object> map5 = new HashMap<String, Object>();

	/** 用来填充ListView的List */
	private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.myevaluation);

		initView();

		// 启动子线程
		Thread thread = new Thread(sRunnable);
		thread.start();

//		if (isGetMyComment) {
//			setMycommentContent();
//		}
	}

	/**
	 * 为我的评论相关控件赋值
	 * 
	 * @user 吴利昌
	 * @date 2015-9-6 下午3:14:15
	 */
	public void setMycommentContent() {

		/** 我的评论整体 */
		List<MyComment> list = commentVo.getContent();

		for (int i = 0; i < list.size(); i++) {

			// 判断对一条新闻发表的评论条数,如果少于5条则隐藏对应的LinearLayout控件
			/**对一条新闻的评论数量*/
			int commentNum = list.get(i).getComment().size();

			/** 我对该条新闻的所有评论 */
			List<MyEvaluation> evaluationList = list.get(i).getComment();

			switch (commentNum) {
			case 1:
				
				//隐藏第2-5条评论的布局
				llEvaluation2.setVisibility(View.GONE);
				llEvaluation3.setVisibility(View.GONE);
				llEvaluation4.setVisibility(View.GONE);
				llEvaluation5.setVisibility(View.GONE);
				
				
				map1.put("carImg", list.get(i).getPath());
				map1.put("newsTitle", list.get(i).getTitle());
				map1.put("videoNum", list.get(i).getBrowseNumber());
				map1.put("evaluationNum", list.get(i).getCommentCount());
				
				map1.put("personImg1", siteUser.getHeadimage());
				map1.put("evaluation1", evaluationList.get(0).getContent());
				map1.put("date1", evaluationList.get(0).getTime());

				data.add(map1);

				// 定义填充ListView的Adapter并填充到ListView中
				// String[]{}与map中填的名字和位置必须保持一致
				SimpleAdapter commentAdapter1 = new SimpleAdapter(this, data,
						R.layout.myevaluation_item, new String[] { "carImg",
								"newsTitle", "videoNum", "evaluationNum",
								"personImg1", "evaluation1", "date1" },
						new int[] { R.id.iv_myEvaluation_carImg_id,
								R.id.tv_myEvaluation_newsTitle_id,
								R.id.tv_myEvaluation_videoNum_id,
								R.id.tv_myEvaluation_evaluationNum_id,
								R.id.iv_myEvaluation_headImg_id1,
								R.id.tv_myEvaluation_Evaluation1,
								R.id.tv_myEvaluation_date1 });
				listView.setAdapter(commentAdapter1);
				break;
			case 2:
				
//				llEvaluation3.setVisibility(View.GONE);
//				llEvaluation4.setVisibility(View.GONE);
//				llEvaluation5.setVisibility(View.GONE);

				
				map2.put("carImg", list.get(i).getPath());
				map2.put("newsTitle", list.get(i).getTitle());
				map2.put("videoNum", list.get(i).getBrowseNumber());
				map2.put("evaluationNum", list.get(i).getCommentCount());
				
				map2.put("personImg1", siteUser.getHeadimage());
				map2.put("evaluation1", evaluationList.get(0).getContent());
				map2.put("date1", evaluationList.get(0).getTime());
				map2.put("personImg2", siteUser.getHeadimage());
				map2.put("evaluation2", evaluationList.get(1).getContent());
				map2.put("date2", evaluationList.get(1).getTime());

				data.add(map2);

				// 定义填充ListView的Adapter并填充到ListView中
				// String[]{}与map中填的名字和位置必须保持一致
				SimpleAdapter commentAdapter2 = new SimpleAdapter(this, data,
						R.layout.myevaluation_item, new String[] { "carImg",
								"newsTitle", "videoNum", "evaluationNum",
								"personImg1", "evaluation1", "date1",
								"personImg2", "evaluation2", "date2" },
						new int[] { R.id.iv_myEvaluation_carImg_id,
								R.id.tv_myEvaluation_newsTitle_id,
								R.id.tv_myEvaluation_videoNum_id,
								R.id.tv_myEvaluation_evaluationNum_id,
								R.id.iv_myEvaluation_headImg_id1,
								R.id.tv_myEvaluation_Evaluation1,
								R.id.tv_myEvaluation_date1,
								R.id.iv_myEvaluation_headImg_id2,
								R.id.tv_myEvaluation_Evaluation2,
								R.id.tv_myEvaluation_date2 });
				listView.setAdapter(commentAdapter2);
				break;
			case 3:
				
//				llEvaluation4.setVisibility(View.GONE);
//				llEvaluation5.setVisibility(View.GONE);
//				
				
				map3.put("carImg", list.get(i).getPath());
				map3.put("newsTitle", list.get(i).getTitle());
				map3.put("videoNum", list.get(i).getBrowseNumber());
				map3.put("evaluationNum", list.get(i).getCommentCount());
				
				map3.put("personImg1", siteUser.getHeadimage());
				map3.put("evaluation1", evaluationList.get(0).getContent());
				map3.put("date1", evaluationList.get(0).getTime());
				map3.put("personImg2", siteUser.getHeadimage());
				map3.put("evaluation2", evaluationList.get(1).getContent());
				map3.put("date2", evaluationList.get(1).getTime());
				map3.put("personImg3", siteUser.getHeadimage());
				map3.put("evaluation3", evaluationList.get(2).getContent());
				map3.put("date3", evaluationList.get(2).getTime());

				data.add(map3);
				
				// 定义填充ListView的Adapter并填充到ListView中
				// String[]{}与map中填的名字和位置必须保持一致
				SimpleAdapter commentAdapter3 = new SimpleAdapter(this, data,
						R.layout.myevaluation_item, new String[] { "carImg",
								"newsTitle", "videoNum", "evaluationNum",
								"personImg1", "evaluation1", "date1",
								"personImg2", "evaluation2", "date2",
								"personImg3", "evaluation3", "date3"},
						new int[] { R.id.iv_myEvaluation_carImg_id,
								R.id.tv_myEvaluation_newsTitle_id,
								R.id.tv_myEvaluation_videoNum_id,
								R.id.tv_myEvaluation_evaluationNum_id,
								R.id.iv_myEvaluation_headImg_id1,
								R.id.tv_myEvaluation_Evaluation1,
								R.id.tv_myEvaluation_date1,
								R.id.iv_myEvaluation_headImg_id2,
								R.id.tv_myEvaluation_Evaluation2,
								R.id.tv_myEvaluation_date2,
								R.id.iv_myEvaluation_headImg_id3,
								R.id.tv_myEvaluation_Evaluation3,
								R.id.tv_myEvaluation_date3});
				listView.setAdapter(commentAdapter3);
				break;
			case 4:
				
				llEvaluation5.setVisibility(View.GONE);
				
				
				map4.put("carImg", list.get(i).getPath());
				map4.put("newsTitle", list.get(i).getTitle());
				map4.put("videoNum", list.get(i).getBrowseNumber());
				map4.put("evaluationNum", list.get(i).getCommentCount());
				
				map4.put("personImg1", siteUser.getHeadimage());
				map4.put("evaluation1", evaluationList.get(0).getContent());
				map4.put("date1", evaluationList.get(0).getTime());
				map4.put("personImg2", siteUser.getHeadimage());
				map4.put("evaluation2", evaluationList.get(1).getContent());
				map4.put("date2", evaluationList.get(1).getTime());
				map4.put("personImg3", siteUser.getHeadimage());
				map4.put("evaluation3", evaluationList.get(2).getContent());
				map4.put("date3", evaluationList.get(2).getTime());
				map4.put("personImg4", siteUser.getHeadimage());
				map4.put("evaluation4", evaluationList.get(3).getContent());
				map4.put("date4", evaluationList.get(3).getTime());

				data.add(map4);
				
				// 定义填充ListView的Adapter并填充到ListView中
				// String[]{}与map中填的名字和位置必须保持一致
				SimpleAdapter commentAdapter4 = new SimpleAdapter(this, data,
						R.layout.myevaluation_item, new String[] { "carImg",
								"newsTitle", "videoNum", "evaluationNum",
								"personImg1", "evaluation1", "date1",
								"personImg2", "evaluation2", "date2",
								"personImg3", "evaluation3", "date3",
								"personImg4", "evaluation4", "date4"},
						new int[] { R.id.iv_myEvaluation_carImg_id,
								R.id.tv_myEvaluation_newsTitle_id,
								R.id.tv_myEvaluation_videoNum_id,
								R.id.tv_myEvaluation_evaluationNum_id,
								R.id.iv_myEvaluation_headImg_id1,
								R.id.tv_myEvaluation_Evaluation1,
								R.id.tv_myEvaluation_date1,
								R.id.iv_myEvaluation_headImg_id2,
								R.id.tv_myEvaluation_Evaluation2,
								R.id.tv_myEvaluation_date2,
								R.id.iv_myEvaluation_headImg_id3,
								R.id.tv_myEvaluation_Evaluation3,
								R.id.tv_myEvaluation_date3,
								R.id.iv_myEvaluation_headImg_id4,
								R.id.tv_myEvaluation_Evaluation4,
								R.id.tv_myEvaluation_date4});
				listView.setAdapter(commentAdapter4);
				break;
			case 5:
				
				
				map5.put("carImg", list.get(i).getPath());
				map5.put("newsTitle", list.get(i).getTitle());
				map5.put("videoNum", list.get(i).getBrowseNumber());
				map5.put("evaluationNum", list.get(i).getCommentCount());
				
				map5.put("personImg1", siteUser.getHeadimage());
				map5.put("evaluation1", evaluationList.get(0).getContent());
				map5.put("date1", evaluationList.get(0).getTime());
				map5.put("personImg2", siteUser.getHeadimage());
				map5.put("evaluation2", evaluationList.get(1).getContent());
				map5.put("date2", evaluationList.get(1).getTime());
				map5.put("personImg3", siteUser.getHeadimage());
				map5.put("evaluation3", evaluationList.get(2).getContent());
				map5.put("date3", evaluationList.get(2).getTime());
				map5.put("personImg4", siteUser.getHeadimage());
				map5.put("evaluation4", evaluationList.get(3).getContent());
				map5.put("date4", evaluationList.get(3).getTime());
				map5.put("personImg5", siteUser.getHeadimage());
				map5.put("evaluation5", evaluationList.get(4).getContent());
				map5.put("date5", evaluationList.get(4).getTime());

				data.add(map5);
				
				// 定义填充ListView的Adapter并填充到ListView中
				// String[]{}与map中填的名字和位置必须保持一致
				SimpleAdapter commentAdapter5 = new SimpleAdapter(this, data,
						R.layout.myevaluation_item, new String[] { "carImg",
								"newsTitle", "videoNum", "evaluationNum",
								"personImg1", "evaluation1", "date1",
								"personImg2", "evaluation2", "date2",
								"personImg3", "evaluation3", "date3",
								"personImg4", "evaluation4", "date4",
								"personImg5", "evaluation5", "date5"},
						new int[] { R.id.iv_myEvaluation_carImg_id,
								R.id.tv_myEvaluation_newsTitle_id,
								R.id.tv_myEvaluation_videoNum_id,
								R.id.tv_myEvaluation_evaluationNum_id,
								R.id.iv_myEvaluation_headImg_id1,
								R.id.tv_myEvaluation_Evaluation1,
								R.id.tv_myEvaluation_date1,
								R.id.iv_myEvaluation_headImg_id2,
								R.id.tv_myEvaluation_Evaluation2,
								R.id.tv_myEvaluation_date2,
								R.id.iv_myEvaluation_headImg_id3,
								R.id.tv_myEvaluation_Evaluation3,
								R.id.tv_myEvaluation_date3,
								R.id.iv_myEvaluation_headImg_id4,
								R.id.tv_myEvaluation_Evaluation4,
								R.id.tv_myEvaluation_date4,
								R.id.iv_myEvaluation_headImg_id5,
								R.id.tv_myEvaluation_Evaluation5,
								R.id.tv_myEvaluation_date5});
				listView.setAdapter(commentAdapter5);
				break;

			default:
				break;
			}

		}
	}

	// --------------------------------获取验证码线程处理过程--开始----------------------------------
	// 声明
				final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			
				switch (msg.what) {
				case 1:
					commentVo = (MyCommentVo) msg.obj;
					setMycommentContent();
					break;
				case -1:
					Toast.makeText(MyEvaluationActivity.this, "获取评论失败!", Toast.LENGTH_SHORT)
							.show();
					break;
				case 0:
					Toast.makeText(MyEvaluationActivity.this, "哎呀,出错啦..", Toast.LENGTH_SHORT)
							.show();
					break;
				default:
					break;
				}
		}};

	/** 实例化自定义的handler */
	//private final MyHandler mHandler = new MyHandler(this);

	/** 自定义子线程 */
	private Runnable sRunnable = new Runnable() {
		@Override
		public void run() {
			Message msg = new Message();

			// 获取全局对象Application
			AppContext appContext = (AppContext) getApplication();

			siteUser = appContext.getCacheUser();
			if (siteUser == null) {
				msg.what = -1;
				return;
			}

			// 接口所需参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pagenow", 1);
			params.put("count", 20);
			params.put("Id", siteUser.getId());

			try {
				// 获取服务器数据
				MyCommentVo myCommentVo = appContext.getMyComment(params, true,
						1);

				// 返回值非空则将消息的what值为1，否则what为-1，异常为0
				if (myCommentVo == null) {
					msg.what = -1;
				} else {
					msg.what = 1;
					msg.obj = myCommentVo;
				}

			} catch (AppException e) {
				msg.what = 0;
				e.printStackTrace();
			}
			handler.sendMessage(msg);
		}
	};

	// -------------------------------获取验证码线程处理过程---完成-----------------------------------

	/**
	 * 初始化控件和监听事件
	 * 
	 * @user 吴利昌
	 * @date 2015-9-6 下午1:51:19
	 */
	public void initView() {
		
		//每条评论的整体布局
		llEvaluation1 = (LinearLayout) findViewById(R.id.rl_myEvaluation_id1);
		llEvaluation2 = (LinearLayout) findViewById(R.id.rl_myEvaluation_id2);
		llEvaluation3 = (LinearLayout) findViewById(R.id.rl_myEvaluation_id3);
		llEvaluation4 = (LinearLayout) findViewById(R.id.rl_myEvaluation_id4);
		llEvaluation5 = (LinearLayout) findViewById(R.id.rl_myEvaluation_id5);

		//每条评论
		tvEvaluation1 = (TextView) findViewById(R.id.tv_myEvaluation_Evaluation1);
		tvEvaluation2 = (TextView) findViewById(R.id.tv_myEvaluation_Evaluation2);
		tvEvaluation3 = (TextView) findViewById(R.id.tv_myEvaluation_Evaluation3);
		tvEvaluation4 = (TextView) findViewById(R.id.tv_myEvaluation_Evaluation4);
		tvEvaluation5 = (TextView) findViewById(R.id.tv_myEvaluation_Evaluation5);

		//我的每条评论的头像
		ivHeadImg1 = (ImageView) findViewById(R.id.iv_myEvaluation_headImg_id1);
		ivHeadImg2 = (ImageView) findViewById(R.id.iv_myEvaluation_headImg_id2);
		ivHeadImg3 = (ImageView) findViewById(R.id.iv_myEvaluation_headImg_id3);
		ivHeadImg4 = (ImageView) findViewById(R.id.iv_myEvaluation_headImg_id4);
		ivHeadImg5 = (ImageView) findViewById(R.id.iv_myEvaluation_headImg_id5);

		//我的每条评论的日期
		tvDate1 = (TextView) findViewById(R.id.tv_myEvaluation_date1);
		tvDate2 = (TextView) findViewById(R.id.tv_myEvaluation_date2);
		tvDate3 = (TextView) findViewById(R.id.tv_myEvaluation_date3);
		tvDate4 = (TextView) findViewById(R.id.tv_myEvaluation_date4);
		tvDate5 = (TextView) findViewById(R.id.tv_myEvaluation_date5);
		
		//新闻的相关信息控件
		ivCarImg = (ImageView) findViewById(R.id.iv_myEvaluation_carImg_id);
		tvNewsTile = (TextView) findViewById(R.id.tv_myEvaluation_newsTitle_id);
		tvVideoNum = (TextView) findViewById(R.id.tv_myEvaluation_videoNum_id);
		tvEvaluationNum = (TextView) findViewById(R.id.tv_myEvaluation_evaluationNum_id);
		tvNewCar = (TextView) findViewById(R.id.tv_myEvaluation_isNewCar_id);

		//我的评论页面整体需要的ListView
		listView = (ListView) findViewById(R.id.lv_myEvaluation_id);
	}

}
