package com.itheima.mobileSafe;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.itheima.dao.QueryLocationDAO;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class QueryLocationActivity extends Activity {

	@ViewInject(R.id.et_querylocal_phonenum)
	private EditText ed_querylocation_phonenum;
	@ViewInject(R.id.tv_querylocal_result)
	private TextView tv_querylocation_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_querylocal);
		ViewUtils.inject(this);
		//设置EditText监听的事件ain，输入号码自动检测
		ed_querylocation_phonenum.addTextChangedListener(new TextWatcher() {
			
			//当EditText在改变的时候
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				String num = ed_querylocation_phonenum.getText().toString().trim();
				System.out.println(num);
				// 校验数据是否是为空
				if (TextUtils.isEmpty(num)) {
					Toast.makeText(getApplicationContext(), "请输入电话号码",
							Toast.LENGTH_SHORT).show();
					return;

				}
				// 校验数据的格式是否正确
				if (!num.matches("^[0-9]*[1-9][0-9]*$")) {
					Toast.makeText(getApplicationContext(), "请输入正确的电话号码",
							Toast.LENGTH_SHORT).show();
					tv_querylocation_result.setText("请输入正确的电话号码");
					return;
				}
				// 将数据传递给数据库层，进行查询数据，然后返回数据
				String location = QueryLocationDAO.queryDao(num,
						getApplicationContext());
				tv_querylocation_result.setText("号码的归属地是：" + location);
			}
			//改变之前
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			//改变之后
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	/**
	 * 检验数据的正确与否
	 */
	public void checkOut(String num) {

	}

	/**
	 * 查询归属地
	 */
	public void query(View view) {
		String num = ed_querylocation_phonenum.getText().toString().trim();
		// 校验数据是否是为空
		if (TextUtils.isEmpty(num)) {
			//引入震动的效果
			Vibrator bibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
			//long[]震动的频率
			//1代表的是震动是否重复
			bibrator.vibrate(new long[]{10l,20l,10l,20l}, 1);
	        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
	        findViewById(R.id.et_querylocal_phonenum).startAnimation(shake);
	        Toast.makeText(getApplicationContext(), "请输入电话号码",
					Toast.LENGTH_SHORT).show();
	        return;

		}
		// 校验数据的格式是否正确
		if (!num.matches("^[0-9]*[1-9][0-9]*$")) {
			Toast.makeText(getApplicationContext(), "请输入正确的电话号码",
					Toast.LENGTH_SHORT).show();
			tv_querylocation_result.setText("请输入正确的电话号码");
			return;
		}
		// 将数据传递给数据库层，进行查询数据，然后返回数据
		String location = QueryLocationDAO.queryDao(num,
				getApplicationContext());
		tv_querylocation_result.setText(location);
	}

}
