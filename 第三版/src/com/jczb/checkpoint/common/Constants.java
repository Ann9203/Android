package com.jczb.checkpoint.common;

import android.os.Environment;

/**
 * 常量类
 * 
 * @author wlc
 * @date 2015-3-23
 */
public class Constants {
	/**
	 ******************************************* 参数设置信息开始 ******************************************
	 */

	// 应用名称
	public static String APP_NAME = "";

	/********************** 安标查询系统通讯接口的url地址-开始 *********************/
	// 登录接口url地址
	public static final String LOGIN_URL = "http://192.168.1.138/CCRIAnBiao/houseInterface.ashx?json=PostEntityLogin";

	// 下载安标证书接口url地址
	public static final String DOWNLOAD_ANBIAO_URL = "http://192.168.1.138/CCRIAnBiao/houseInterface.ashx?json=PostDownloadAnbiao";

	// 下载安标证书图片信息接口url地址
	//public static final String DOWNLOAD_IMG_URL = "http://192.168.1.138/CCRIAnBiao/houseInterface.ashx?json=PostDownloadRFIDPic";
	public static final String DOWNLOAD_IMG_URL = "http://192.168.1.138/CCRIAnBiao/houseInterface.ashx?json=PostDownloadRFIDPic";
	
	// 下载安标产品接口url地址
	public static final String DOWNLOAD_RFID_URL = "http://192.168.1.138/CCRIAnBiao/houseInterface.ashx?json=PostDownRFIDInfo";

	//在本地需要获取并转换的图片地址
	public static final String CONVERT_IMG_URL = "http://192.168.1.138/CCRIAnBiao/Images.aspx?filename=";
	
	// 上传接口url地址
	public static final String UPLOAD_URL = "http://192.168.1.138/CCRIAnBiao/houseInterface.ashx?json=PostUpload";

	/************************ 安标查询系统通讯接口的url地址-结束 ********************/
	
	/****************************安标查询系统插入图片时的所属类型--开始*****************************/
	
	public static final String IMG_ANBIAO_TYPE = "AnbiaoType";
	public static final String IMG_SPOTCHECK_TYPE = "SpotcheckType";
	public static final String IMG_RFID_TYPE = "RFIDType";
	
	/****************************安标查询系统插入图片时的所属类型--开始*****************************/
	
	
	// 图片路径
	public static final String IMAGE_URL = "http://58.211.5.34:8080/studioms/staticmedia/images/#";

	// 视频路径
	public static final String VIDEO_URL = "http://58.211.5.34:8080/studioms/staticmedia/video/#";

	// 保存参数文件夹名�?
	public static final String SHARED_PREFERENCE_NAME = "itau_jingdong_prefs";

	// SDCard路径
	public static final String SD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	// 图片存储路径
	public static final String BASE_PATH = SD_PATH + "/iTau/jingdong/";

	// 缓存图片路径
	public static final String BASE_IMAGE_CACHE = BASE_PATH + "cache/images/";

	// �?要分享的图片
	public static final String SHARE_FILE = BASE_PATH + "QrShareImage.png";

	// 手机IMEI号码
	public static String IMEI = "";

	// 手机号码
	public static String TEL = "";

	// 屏幕高度
	public static int SCREEN_HEIGHT = 800;

	// 屏幕宽度
	public static int SCREEN_WIDTH = 480;

	// 屏幕密度
	public static float SCREEN_DENSITY = 1.5f;

	// 分享成功
	public static final int SHARE_SUCCESS = 0X1000;

	// 分享取消
	public static final int SHARE_CANCEL = 0X2000;

	// 分享失败
	public static final int SHARE_ERROR = 0X3000;

	// 开始执行
	public static final int EXECUTE_LOADING = 0X4000;

	// 正在执行
	public static final int EXECUTE_SUCCESS = 0X5000;

	// 执行完成
	public static final int EXECUTE_FAILED = 0X6000;

	// 加载数据成功
	public static final int LOAD_DATA_SUCCESS = 0X7000;

	// 加载数据失败
	public static final int LOAD_DATA_ERROR = 0X8000;

	// 动态加载数据
	public static final int SET_DATA = 0X9000;

	// 未登录
	public static final int NONE_LOGIN = 0X10000;

	/**
	 ******************************************* 参数设置信息结束 ******************************************
	 */
}
