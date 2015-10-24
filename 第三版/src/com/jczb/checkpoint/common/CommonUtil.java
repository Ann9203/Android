package com.jczb.checkpoint.common;

import java.util.Calendar;
import java.util.List;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class CommonUtil {
	/**
	 * 日志信息
	 * 
	 * @param tag
	 *            标志
	 * @param funcName
	 *            打日志的函数名
	 * @param msg
	 *            日志信息内容
	 * @param type
	 *            日志类型
	 */
	public static void Log(String tag, String funcName, String msg, char type) {
		switch (type) {
		case 'e':
			Log.e(tag, funcName + "===>" + msg);
			break;
		case 'v':
			Log.v(tag, funcName + "===>" + msg);
			break;
		case 'i':
			Log.i(tag, funcName + "===>" + msg);
			break;
		case 'd':
			Log.d(tag, funcName + "===>" + msg);
			break;
		default:
			Log.d(tag, funcName + "===>" + msg);
			break;
		}
	}

	/**
	 * 显示提示信息
	 * 
	 * @param context
	 *            要显示信息的当前实例
	 * @param msg
	 *            信息内容
	 */
	public static void Toast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * 返回当前系统日期
	 * 
	 * @return
	 */
	public static String getNowDate() {
		String date = null;
		Calendar calendar = Calendar.getInstance();
		int mYear = calendar.get(Calendar.YEAR);
		int mMonth = calendar.get(Calendar.MONTH);
		int mDay = calendar.get(Calendar.DAY_OF_MONTH);
		mMonth++;
		if (mMonth < 10)
			date = mYear + "-0" + mMonth;
		else
			date = mYear + "-" + mMonth;
		if (mDay < 10)
			date = date + "-0" + mDay;
		else
			date = date + "-" + mDay;
		return date;
	}

	/**
	 * 返回当前系统时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		String time = null;
		Calendar calendar = Calendar.getInstance();
		int mHour = calendar.get(Calendar.HOUR_OF_DAY);
		int mMinute = calendar.get(Calendar.MINUTE);
		int mSecond = calendar.get(Calendar.SECOND);

		if (mHour < 10)
			time = "0" + mHour;
		else
			time = mHour + "";
		if (mMinute < 10)
			time = time + ":0" + mMinute;
		else
			time = time + ":" + mMinute;
		if (mSecond < 10)
			time = time + ":0" + mSecond;
		else
			time = time + ":" + mSecond;
		return time;
	}

	/**
	 * 打电话
	 * 
	 * @param num
	 *            电话号码
	 */
	public static void dial(Context context, String num) {
		if (num.equals("无")) {
			CommonUtil.Toast(context, "该联系人无号码!");
			return;
		}
		Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ num));
		context.startActivity(phoneIntent);
		CommonUtil.Toast(context, "正在拨打" + num);
	}

	
}
