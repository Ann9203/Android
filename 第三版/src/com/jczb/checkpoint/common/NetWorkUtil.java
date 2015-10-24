package com.jczb.checkpoint.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 网络助手类
 * @author wlc
 * @date 2015-3-26
 */
public class NetWorkUtil {

	/**
	 * Wifi是否可用
	 * @param context
	 * @return
	 */
	public boolean isWifiEnabled(Context context) {   
        ConnectivityManager mgrConn = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        TelephonyManager mgrTel = (TelephonyManager) context   
                .getSystemService(Context.TELEPHONY_SERVICE);   
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn   
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel   
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);   
    }
	
	/**
	 * 判断是否是3G网络
	 * @param context
	 * @return
	 */
	public boolean is3rd(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
        if (networkINfo != null   
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {   
            return true;   
        }   
        return false;   
    }
	
	/**
	 * 判断是wifi还是3g网络,用户的体现性在这里了，wifi就可以建议下载或者在线播放。
	 * @param context
	 * @return
	 */
	public boolean isWifi(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
        if (networkINfo != null   
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {   
            return true;   
        }   
        return false;   
    }
	
	/**
	 * 判断手机网络（包括2G/3G/4G/Wifi）是否可用
	 * @param context
	 * @return
	 */
	public boolean isNetworkAvailable(Context context) {   
		//使用自带的连接助手类
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        if (cm == null) {   
        	return false;
        } else { 
        	//如果仅仅是用来判断网络连接
        	//则可以使用cm.getActiveNetworkInfo().isAvailabe()方法
            NetworkInfo[] info = cm.getAllNetworkInfo();   
            if (info != null) {   
                for (int i = 0; i < info.length; i++) {   
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
                        return true;   
                    }   
                }   
            }   
        }   
        return false;   
    }
}
