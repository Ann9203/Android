package com.jczb.car.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.R;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;
import com.jczb.car.ui.PictureViewInfoActivity.pictureViewInfoThread;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VedioInfoActivity extends Activity implements OnClickListener {

	// 声明需要用到的控件
	/* 视频详情的名称 */
	private TextView tvVedioTitle;
	/* 视频详情的时间 */
	private TextView tvVedioTime;
	/* 视频详情的作者 */
	private TextView tvVedioAuthor;
	/* 视频详情的视频播放数 */
	private TextView tvVedioPlayNumber;
	/* 视频详情的赞数 */
	private TextView tvPraiseNumber;
	private LinearLayout back;
	/* 解析发现接口用的实体类 */
	private ContentVo contentVo = null;
	/* 传参用的集合 */
	private Map<String, Object> params = new HashMap<String, Object>();
	/* 用来获得服务器接口的发现内容集合 */
	private List<Content> vedioInfo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_info);

		initView();
		// 启动子线程,获取服务器数据
		new Thread(new vedioInfoThread()).start();

	}

	private void initView() {
		// TODO Auto-generated method stub
		// 初始化控件
		tvVedioTitle = (TextView) findViewById(R.id.video_title_id);
		tvVedioTime = (TextView) findViewById(R.id.video_time_id);
		tvVedioAuthor = (TextView) findViewById(R.id.video_author_id);
		tvVedioPlayNumber = (TextView) findViewById(R.id.video_play_number_id);
		tvPraiseNumber = (TextView) findViewById(R.id.video_praise_number_id);
		back = (LinearLayout) findViewById(R.id.vedio_back);

		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.vedio_back:
			this.finish();
			break;

		default:
			break;
		}

	}

	/* 子线程-解析数据 */
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case -1:
				Toast.makeText(VedioInfoActivity.this, "服务器连接失败!",
						Toast.LENGTH_SHORT).show();
				break;
			case -2:
				Toast.makeText(VedioInfoActivity.this, "哎呀，出错啦...",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				contentVo = (ContentVo) msg.obj;
				vedioInfo = contentVo.getContent();
				tvVedioTitle.setText(vedioInfo.get(0).getTitle());
				tvVedioTime.setText(vedioInfo.get(0).getIssueTime().toString());
				tvVedioAuthor.setText(vedioInfo.get(0).getAuthor());
				tvVedioPlayNumber.setText(vedioInfo.get(0).getBrowseNumber()
						+ "");
				tvPraiseNumber.setText(vedioInfo.get(0).getPraiseNumber() + "");
				break;

			default:
				break;
			}
		}
	};

	public class vedioInfoThread implements Runnable {
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
				contentVo = appContext.getVideoContentDetail(params, true, 1);

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
			mHandler.sendMessage(msg);

		}
	}

}
