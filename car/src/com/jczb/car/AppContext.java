package com.jczb.car;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;



import com.jczb.car.api.ApiClient;
import com.jczb.car.bean.ChannelCategory;
import com.jczb.car.bean.ChannelCategoryList;
import com.jczb.car.bean.ChannelCategoryVo;
import com.jczb.car.bean.ChannelContentCommentVo;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;
import com.jczb.car.bean.MyCollectionVo;
import com.jczb.car.bean.MyCommentVo;
import com.jczb.car.bean.SiteUser;
import com.jczb.car.bean.User;
import com.jczb.car.bean.VersionInfo;
import com.jczb.car.bean.VersionInfoVo;
import com.jczb.car.common.CyptoUtils;
import com.jczb.car.common.FileUtils;
import com.jczb.car.common.StringUtils;

import android.R.bool;
import android.R.integer;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * 
 * @author 王向阳
 * 
 */
public class AppContext extends Application {

	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	public static final int PAGE_SIZE = 16;// 默认分页大小
	private static final int CACHE_TIME = 60 * 60000;// 缓存失效时间

	private boolean login = false; // 登录状态
	private int loginUid = 0; // 登录用户的id
	private Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();

	private String saveImagePath;// 保存图片路径

	@Override
	public void onCreate() {
		super.onCreate();
	}

	
	
	
	/**
	 * 内容详情
	 * 
	 * @param blog_id
	 * @return
	 * @throws AppException
	 */
	public Content getContent(int content_id, boolean isRefresh)
			throws AppException {
		Content content = null;
		String key = "content_" + content_id;
		if (isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
			try {
				content = ApiClient.getContentDetail(this, content_id);
				if (content != null) {
					content.setCacheKey(key);
					saveObject(content, key);
				}
			} catch (AppException e) {
				content = (Content) readObject(key);
				if (content == null)
					throw e;
			}
		} else {
			content = (Content) readObject(key);
			if (content == null)
				content = new Content();
		}
		return content;
	}

	/**
	 * 内容列表
	 * 
	 * @param pageIndex
	 * @return
	 * @throws AppException
	 */
	public ContentVo getContentList(int pageIndex, boolean isRefresh)
			throws AppException {
		ContentVo list = null;
		String key = "contentlist_" + pageIndex + "_" + PAGE_SIZE;
		if (isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
			try {
				list = ApiClient.getContentList(this, pageIndex, PAGE_SIZE);
				if (list != null && pageIndex == 0) {

					list.setCacheKey(key);
					saveObject(list, key);

				}
			} catch (AppException e) {
				list = (ContentVo) readObject(key);
				if (list == null)
					throw e;
			}
		} else {
			list = (ContentVo) readObject(key);
			if (list == null)
				list = new ContentVo();
		}
		return list;
	}

	/**
	 * 初始化用户登录信息
	 */
	public void initLoginInfo() {
		User loginUser = getLoginInfo();
		if (loginUser != null && loginUser.getUid() > 0) {
			this.loginUid = loginUser.getUid();
			this.login = true;
		} else {
			this.Logout();
		}
	}
	/**
	 * 用户是否登录
	 * @return
	 */
	public boolean isLogin() {
		return login;
	}
	
	/**
	 * 获取登录用户id
	 * @return
	 */
	public int getLoginUid() {
		return this.loginUid;
	}
	/**
	 * 用户注销
	 */
	public void Logout() {
		ApiClient.cleanCookie();
		this.cleanCookie();
		this.login = false;
		this.loginUid = 0;
	}
	/**
	 * 清除登录信息
	 */
	public void cleanLoginInfo() {
		this.loginUid = 0;
		this.login = false;
		removeProperty("user.uid","user.username","user.password","user.nickname","user.headimage",
				"user.type");
	}
	/**
	 * 清除保存的缓存
	 */
	public void cleanCookie() {
		removeProperty(AppConfig.CONF_COOKIE);
	}

	/**
	 * 获取登录信息
	 * 
	 * @return
	 */
	public User getLoginInfo() {
		User lu = new User();
		lu.setUid(StringUtils.toInt(getProperty("user.uid"), 0));
		lu.setNickname(getProperty("user.nickname"));
		lu.setHeadimage(getProperty("user.headimage"));
		lu.setPassword(getProperty("user.password"));
		lu.setType(getProperty("user.type"));
		lu.setUserName(getProperty("user.username"));
		return lu;
	}
	/**
	 * 保存登录信息
	 * @param username
	 * @param pwd
	 */
	public void saveLoginInfo(final User user) {
		this.loginUid = user.getUid();
		this.login = true;
		setProperties(new Properties(){{
			setProperty("user.uid", String.valueOf(user.getUid()));
			setProperty("user.username", user.getUserName());
			setProperty("user.password",user.getPassword());
			// FileUtils.getFileName(user.getFace()));//用户头像-文件名
			setProperty("user.nickname", user.getNickname());
			setProperty("user.headimage", FileUtils.getFileName(user.getHeadimage()));//CyptoUtils.encode("oschinaApp",user.getPwd()));
			setProperty("user.type", user.getType());
			
		}});		
	}
	/**
	 * 用户登录验证
	 * 
	 * @param account
	 * @param pwd
	 * @return
	 * @throws AppException
	 */
	public User loginVerify(String account, String pwd) throws AppException {
		return ApiClient.login(this, account, pwd);
	}

	/**
	 * 是否Https登录
	 * 
	 * @return
	 */
	public boolean isHttpsLogin() {
		String perf_httpslogin = getProperty(AppConfig.CONF_HTTPS_LOGIN);
		// 默认是http
		if (StringUtils.isEmpty(perf_httpslogin))
			return false;
		else
			return StringUtils.toBool(perf_httpslogin);
	}

	/**
	 * 获取App唯一标识
	 * 
	 * @return
	 */
	public String getAppId() {
		String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
		if (StringUtils.isEmpty(uniqueID)) {
			uniqueID = UUID.randomUUID().toString();
			setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
		}
		return uniqueID;
	}

	/**
	 * 应用程序是否发出提示音
	 * @return
	 */
	public boolean isAppSound() {
		return isAudioNormal() && isVoice();
	}
	
	
	/**
	 * 检测当前系统声音是否为正常模式
	 * @return
	 */
	public boolean isAudioNormal() {
		AudioManager mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE); 
		return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
	}
	
	
	/**
	 * 是否发出提示音
	 * @return
	 */
	public boolean isVoice()
	{
		String perf_voice = getProperty(AppConfig.CONF_VOICE);
		//默认是开启提示声音
		if(StringUtils.isEmpty(perf_voice))
			return true;
		else
			return StringUtils.toBool(perf_voice);
	}
	
	
	/**
	 * 检测网络是否可用
	 * 
	 * @return
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 保存对象
	 * 
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = openFileOutput(file, MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
			}
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 判断缓存是否存在
	 * 
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile) {
		boolean exist = false;
		File data = getFileStreamPath(cachefile);
		if (data.exists())
			exist = true;
		return exist;
	}

	/**
	 * 读取对象
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file) {
		if (!isExistDataCache(file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
			// 反序列化失败 - 删除缓存文件
			if (e instanceof InvalidClassException) {
				File data = getFileStreamPath(file);
				data.delete();
			}
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 判断缓存数据是否可读
	 * 
	 * @param cachefile
	 * @return
	 */
	private boolean isReadDataCache(String cachefile) {
		return readObject(cachefile) != null;
	}

	public boolean containsProperty(String key) {
		Properties props = getProperties();
		return props.containsKey(key);
	}

	public void setProperties(Properties ps) {
		AppConfig.getAppConfig(this).set(ps);
	}

	public Properties getProperties() {
		return AppConfig.getAppConfig(this).get();
	}

	public void setProperty(String key, String value) {
		AppConfig.getAppConfig(this).set(key, value);
	}

	public String getProperty(String key) {
		return AppConfig.getAppConfig(this).get(key);
	}

	public void removeProperty(String... key) {
		AppConfig.getAppConfig(this).remove(key);
	}

	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	// -------------------------------------吴利昌添加----开始-------------------------------------------
	/**
	 * 获取服务器验证码(不需要缓存）
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-28 上午9:07:14
	 */
	public String getCode(Map<String, Object> map) throws AppException {

		String validateCode = "";

		// 如果网络可连接且解析无误返回正确的验证码，否则返回空字符串
		if (isNetworkConnected()) {
			try {
				validateCode = ApiClient.getValidateCode(this, map);
			} catch (AppException e) {
				if (validateCode == "") {
					throw e;
				}
			}
		}
		return validateCode;
	}

	/**
	 * 注册并验证（不需要缓存）
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-8-28 下午2:49:48
	 */
	public boolean register(Map<String, Object> map) throws AppException {

		boolean isSuccess = false;

		// 网络可用且解析无误，则返回正确的执行结果，否则返回false
		if (isNetworkConnected()) {
			try {
				isSuccess = ApiClient.getRegisterStatus(this, map);
			} catch (AppException e) {
				if (!isSuccess) {
					throw e;
				}
			}
		}

		return isSuccess;

	}

	/**
	 * 登陆
	 * 
	 * @param map
	 *            登陆时带的参数集合
	 * @param isFirst
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-8-28 下午4:21:45
	 */
	public SiteUser getUser(Map<String, Object> map, boolean isFirst,
			int siteuserId) throws AppException {

		SiteUser siteUser = null;

		// 构建缓存文件的key
		String key = "siteUser_" + siteuserId;

		// 1.如果联网则从服务器获取数据（为了防止密码不一致的问题）
		if (isNetworkConnected() && (isFirst || isReadDataCache(key))) {
			try {

				siteUser = ApiClient.getSiteUser(this, map);

				// 如果能够获取到服务器中的数据则保存到本地缓存
				if (siteUser != null) {
					siteUser.setCacheKey(key);
					saveObject(siteUser, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				siteUser = (SiteUser) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (siteUser == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			siteUser = (SiteUser) readObject(key);
			if (siteUser == null) {
				siteUser = new SiteUser();
			}
			return siteUser;
		}

		return siteUser;
	}

	/**
	 * 修改密码
	 * 
	 * @param map
	 *            带新密码和用户名的参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 上午9:32:56
	 */
	public boolean updatePassword(Map<String, Object> map) throws AppException {

		boolean isSuccess = false;

		// 网络可用且解析无误，则返回正确的执行结果，否则返回false
		if (isNetworkConnected()) {
			try {
				// 调用修改密码方法
				isSuccess = ApiClient.modifyPassword(this, map);
			} catch (AppException e) {
				if (!isSuccess) {
					throw e;
				}
			}
		}

		return isSuccess;
	}

	/**
	 * 修改昵称
	 * 
	 * @param map
	 *            带新密码和用户名的参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 上午9:32:56
	 */
	public boolean updateNickname(Map<String, Object> map) throws AppException {

		boolean isSuccess = false;

		// 网络可用且解析无误，则返回正确的执行结果，否则返回false
		if (isNetworkConnected()) {
			try {
				// 调用修改密码方法
				isSuccess = ApiClient.modifyNickname(this, map);
			} catch (AppException e) {
				if (!isSuccess) {
					throw e;
				}
			}
		}

		return isSuccess;
	}

	/**
	 * 上传头像 post提交
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 上午9:32:56
	 */
	public boolean uploadImg(Map<String, Object> params, Map<String, File> files)
			throws AppException {

		boolean isSuccess = false;

		// 网络可用且解析无误，则返回正确的执行结果，否则返回false
		if (isNetworkConnected()) {
			try {
				// 调用修改密码方法
				isSuccess = ApiClient.uploadImage(this, params, files);
			} catch (AppException e) {
				if (!isSuccess) {
					throw e;
				}
			}
		}

		return isSuccess;
	}

	/**
	 * 发现
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 下午2:10:59
	 */
	public ContentVo discover(boolean isFirst, int contentVoId)
			throws AppException {

		ContentVo contentVo = null;

		// 构建缓存文件的key
		String key = "contentVo_" + contentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(isReadDataCache(key) || isFirst)) {
			try {

				// 从服务器获取Content的集合
				contentVo = ApiClient.discover(this);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (contentVo != null && contentVo.getResult().equals("false")) {
					contentVo.setCacheKey(key);
					saveObject(contentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				contentVo = (ContentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (contentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			contentVo = (ContentVo) readObject(key);
			if (contentVo == null) {
				contentVo = new ContentVo();
			}
			return contentVo;
		}

		return contentVo;
	}

	/**
	 * 我的收藏
	 * 
	 * @param map
	 *            带分页信息的map
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 下午2:10:59
	 */
	public MyCollectionVo getMyCollection(Map<String, Object> map,
			boolean isFirst, int myCollectionVoId) throws AppException {

		MyCollectionVo myCollectionVo = null;

		// 构建缓存文件的key
		String key = "myCollectionVo_" + myCollectionVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() && (isReadDataCache(key) || isFirst)) {
			try {

				// 从服务器获取mycollection的集合
				myCollectionVo = ApiClient.getCollection(this, map);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (myCollectionVo != null
						&& myCollectionVo.getResult().equals("false")) {
					myCollectionVo.setCacheKey(key);
					saveObject(myCollectionVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				myCollectionVo = (MyCollectionVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (myCollectionVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			myCollectionVo = (MyCollectionVo) readObject(key);
			if (myCollectionVo == null) {
				myCollectionVo = new MyCollectionVo();
			}
			return myCollectionVo;
		}

		return myCollectionVo;
	}

	/**
	 * 我的评论
	 * 
	 * @param map
	 *            带分页参数信息的集合
	 * @param isFirst
	 *            是否刷新
	 * @param myCommentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-8-31 下午4:49:44
	 */
	public MyCommentVo getMyComment(Map<String, Object> map, boolean isFirst,
			int myCommentVoId) throws AppException {

		MyCommentVo myCommentVo = null;

		// 构建缓存文件的key
		String key = "myCommentVo_" + myCommentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(isReadDataCache(key)|| isFirst)) {
			try {

				// 从服务器获取mycomment的集合
				myCommentVo = ApiClient.getEvaluation(this, map);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (myCommentVo != null
						&& myCommentVo.getResult().equals("false")) {
					myCommentVo.setCacheKey(key);
					saveObject(myCommentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				myCommentVo = (MyCommentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (myCommentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			myCommentVo = (MyCommentVo) readObject(key);
			if (myCommentVo == null) {
				myCommentVo = new MyCommentVo();
			}
			return myCommentVo;
		}

		return myCommentVo;
	}

	/**
	 * 频道内容评论
	 * 
	 * @param map
	 *            带分页参数信息及频道内容id的集合
	 * @param isFirst
	 *            是否刷新
	 * @param myCommentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-08-31 17:35:50
	 */
	public ChannelContentCommentVo getChannelComment(Map<String, Object> map,
			boolean isFirst, int channelContentVoId) throws AppException {

		ChannelContentCommentVo channelContentCommentVo = null;

		// 构建缓存文件的key
		String key = "channelContentCommentVo" + channelContentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(isReadDataCache(key)|| isFirst)) {
			try {

				// 从服务器获取mycomment的集合
				channelContentCommentVo = ApiClient.getChannelEvaluation(this,
						map);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (channelContentCommentVo != null
						&& channelContentCommentVo.getResult().equals("false")) {
					channelContentCommentVo.setCacheKey(key);
					saveObject(channelContentCommentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				channelContentCommentVo = (ChannelContentCommentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (channelContentCommentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			channelContentCommentVo = (ChannelContentCommentVo) readObject(key);
			if (channelContentCommentVo == null) {
				channelContentCommentVo = new ChannelContentCommentVo();
			}
			return channelContentCommentVo;
		}

		return channelContentCommentVo;
	}

	/**
	 * 版本信息
	 * 
	 * @param map
	 *            带分页参数信息及用户id的集合
	 * @param isFirst
	 *            是否刷新
	 * @param myCommentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-08-31 18:05:22
	 */
	public VersionInfoVo getVersion(Map<String, Object> map, boolean isFirst,
			int versionInfoVoId) throws AppException {

		VersionInfoVo versionInfoVo = null;

		// 构建缓存文件的key
		String key = "versionInfoVo_" + versionInfoVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(isReadDataCache(key)|| isFirst)) {
			try {

				// 从服务器获取versionInfoVo的集合
				versionInfoVo = ApiClient.getVersion(this, map);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (versionInfoVo != null
						&& versionInfoVo.getResult().equals("false")) {
					versionInfoVo.setCacheKey(key);
					saveObject(versionInfoVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				versionInfoVo = (VersionInfoVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (versionInfoVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			versionInfoVo = (VersionInfoVo) readObject(key);
			if (versionInfoVo == null) {
				versionInfoVo = new VersionInfoVo();
			}
			return versionInfoVo;
		}

		return versionInfoVo;
	}

	/**
	 * 获取添加评论是否成功的状态
	 * 
	 * @param map
	 *            带用户Id、被评论内容的Id、评论内容参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-08-31 18:19:25
	 */
	public boolean getAddCommentStatus(Map<String, Object> map)
			throws AppException {

		boolean isSuccess = false;

		// 网络可用且解析无误，则返回正确的执行结果，否则返回false
		if (isNetworkConnected()) {
			try {
				// 调用添加评论方法
				isSuccess = ApiClient.addComment(this, map);
			} catch (AppException e) {
				if (!isSuccess) {
					throw e;
				}
			}
		}

		return isSuccess;
	}

	/**
	 * 频道列表信息
	 * 
	 * @param isFirst
	 *            是否刷新
	 * @param channelCategoryVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-09-01 08:53:52
	 */
	public ChannelCategoryVo getChannelCategory(boolean isFirst,
			int channelCategoryVoId) throws AppException {

		ChannelCategoryVo channelCategoryVo = null;

		// 构建缓存文件的key
		String key = "channelCategoryVo_" + channelCategoryVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(isReadDataCache(key))|| isFirst) {
			try {

				// 从服务器获取channelCategoryVo的集合
				channelCategoryVo = ApiClient.getAllChannelCategory(this);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (channelCategoryVo != null
						&& channelCategoryVo.getResult().equals("false")) {
					channelCategoryVo.setCacheKey(key);
					saveObject(channelCategoryVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				channelCategoryVo = (ChannelCategoryVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (channelCategoryVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			channelCategoryVo = (ChannelCategoryVo) readObject(key);
			if (channelCategoryVo == null) {
				channelCategoryVo = new ChannelCategoryVo();
			}
			return channelCategoryVo;
		}

		return channelCategoryVo;
	}

	/**
	 * 获取段子内容详情
	 * 
	 * @param params
	 *            带频道内容id和评到类型(视频(0)或图文(1))参数的集合
	 * @param isFirst
	 *            是否刷新
	 * @param contentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 上午9:21:59
	 */
	public ContentVo getImgWordContentDetail(Map<String, Object> params,
			boolean isFirst, int contentVoId) throws AppException {

		ContentVo contentVo = null;

		// 构建缓存文件的key
		String key = "imgWordContentVo_" + contentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(isReadDataCache(key)|| isFirst)) {
			try {

				// 从服务器获取contentVo的集合
				contentVo = ApiClient.getPassageContent(this, params);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (contentVo != null && contentVo.getResult().equals("false")) {
					contentVo.setCacheKey(key);
					saveObject(contentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				contentVo = (ContentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (contentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			contentVo = (ContentVo) readObject(key);
			if (contentVo == null) {
				contentVo = new ContentVo();
			}
			return contentVo;
		}

		return contentVo;
	}
	
	/**
	 * 获取视频段子内容详情
	 * 
	 * @param params
	 *            带频道内容id和评到类型(视频(0))参数的集合
	 * @param isFirst
	 *            是否刷新
	 * @param contentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 上午9:21:59
	 */
	public ContentVo getVideoContentDetail(Map<String, Object> params,
			boolean isFirst, int contentVoId) throws AppException {

		ContentVo contentVo = null;

		// 构建缓存文件的key
		String key = "videoContentVo_" + contentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(isReadDataCache(key)|| isFirst)) {
			try {

				// 从服务器获取contentVo的集合
				contentVo = ApiClient.getPassageContent(this, params);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (contentVo != null && contentVo.getResult().equals("false")) {
					contentVo.setCacheKey(key);
					saveObject(contentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				contentVo = (ContentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (contentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			contentVo = (ContentVo) readObject(key);
			if (contentVo == null) {
				contentVo = new ContentVo();
			}
			return contentVo;
		}

		return contentVo;
	}
	

	
	
	
	/**
	 * 获取各频道下面的内容信息
	 * 
	 * @param params
	 *            带频类型Id、当前页码和当前页新闻数量参数的集合
	 * @param isRefresh
	 *            是否刷新
	 * @param contentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @user 吴利昌
	 * @date 2015-09-05 12:27:42
	 */
	public ContentVo getContentByCategory(Map<String, Object> params,
			boolean isRefresh, int contentVoId) throws AppException {

		ContentVo contentVo = null;

		// 构建缓存文件的key
		String key = "categoryContentVo_" + contentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(!isReadDataCache(key) || isRefresh)) {
			try {

				// 从服务器获取contentVo的集合
				contentVo = ApiClient.getContentByCategory(this,params);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (contentVo != null && contentVo.getResult().equals("false")) {
					contentVo.setCacheKey(key);
					saveObject(contentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				contentVo = (ContentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (contentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			contentVo = (ContentVo) readObject(key);
			if (contentVo == null) {
				contentVo = new ContentVo();
			}
			return contentVo;
		}

		return contentVo;
	}
	/**
	 * 搜索内容
	 * 
	 * @param params
	 *            当前页码(pagenow)和当前页新闻数量(count)参数的集合
	 * @param isRefresh
	 *            是否刷新
	 * @param contentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @user 吴利昌
	 * @date 2015-09-05 12:27:42
	 */
	public ContentVo getSearchContentVoCategoryContent(Map<String, Object> params,
			boolean isRefresh, int contentVoId) throws AppException {

		ContentVo contentVo = null;

		// 构建缓存文件的key
		String key = "searchCategoryContentVo_" + contentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(!isReadDataCache(key) || isRefresh)) {
			try {

				// 从服务器获取contentVo的集合
				contentVo = ApiClient.getSearchContent(this, params);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (contentVo != null && contentVo.getResult().equals("false")) {
					contentVo.setCacheKey(key);
					saveObject(contentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				contentVo = (ContentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (contentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			contentVo = (ContentVo) readObject(key);
			if (contentVo == null) {
				contentVo = new ContentVo();
			}
			return contentVo;
		}

		return contentVo;
	}
	
	/**
	 * 获取推荐的频道内容信息
	 * 
	 * @param params
	 *            当前页码(pagenow)和当前页新闻数量(count)参数的集合
	 * @param isRefresh
	 *            是否刷新
	 * @param contentVoId
	 *            本地缓存id的组成部分
	 * @return
	 * @user 吴利昌
	 * @date 2015-09-05 12:27:42
	 */
	public ContentVo getRecommandCategoryContent(Map<String, Object> params,
			boolean isRefresh, int contentVoId) throws AppException {

		ContentVo contentVo = null;

		// 构建缓存文件的key
		String key = "recommandCategoryContentVo_" + contentVoId;

		// 1.如果联网则首先从服务器获取数据
		if (isNetworkConnected() &&(!isReadDataCache(key) || isRefresh)) {
			try {

				// 从服务器获取contentVo的集合
				contentVo = ApiClient.getRecommandCategoryContent(this, params);

				// 如果能够获取到服务器中的数据则保存到本地缓存,只有保证数据不为空，且获取到的结果为true的时候才缓存到本地
				if (contentVo != null && contentVo.getResult().equals("false")) {
					contentVo.setCacheKey(key);
					saveObject(contentVo, key);
				}

			} catch (AppException e) {

				// 如果出现访问中途断网，则获取本地缓存中的数据
				contentVo = (ContentVo) readObject(key);
				// 如果本地缓存中数据为空，则抛出异常
				if (contentVo == null) {
					throw e;
				}
			}
		}
		// 2.如果未联网则从缓存中获取数据
		else {
			contentVo = (ContentVo) readObject(key);
			if (contentVo == null) {
				contentVo = new ContentVo();
			}
			return contentVo;
		}

		return contentVo;
	}

	// -------------------------------读取缓存的方法----开始--------------------------------------
	/**
	 * 获取缓存中的用户信息实体
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 下午7:09:57
	 */
	public SiteUser getCacheUser() {

		String key = "siteUser_1";

		SiteUser siteUser = (SiteUser) readObject(key);

		return siteUser;

	}

	// -------------------------------读取缓存的方法----结束--------------------------------------

	// -------------------------------------吴利昌添加----结束-------------------------------------------
	/**
	 * 说明：频道分类 作者：丁国华 时间：2015年8月20日 14:52:19
	 */
	// public ChannelCategory getChannelCategory(int channel_id, boolean
	// isRefresh)
	// throws AppException {
	// ChannelCategory channelCategory = null;
	// String key = "channelCategory_" + channel_id;
	// if (isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
	// try {
	// channelCategory = ApiClient.getChannelCategorytDetail(this,
	// channel_id);
	// if (channelCategory != null) {
	// channelCategory.setCacheKey(key);
	// saveObject(channelCategory, key);
	// }
	// } catch (AppException e) {
	// channelCategory = (ChannelCategory) readObject(key);
	// if (channelCategory == null)
	// throw e;
	// }
	// } else {
	// channelCategory = (ChannelCategory) readObject(key);
	// if (channelCategory == null)
	// channelCategory = new ChannelCategory();
	// }
	// return channelCategory;
	// }

	// /**
	// * 说明：频道分类列表
	// * 作者：丁国华
	// * 时间：2015年8月20日 14:52:19
	// */
	// public ChannelCategoryList getChannelCategoryList(int pageIndex, boolean
	// isRefresh) throws AppException {
	// ChannelCategoryList list = null;
	// String key = "channelCategoryList_" + pageIndex+"_"+PAGE_SIZE;
	// if(isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
	// try{
	// list = ApiClient.getChannelCategoryList(this, pageIndex, PAGE_SIZE);
	// if(list != null && pageIndex == 0){
	//
	// list.setCacheKey(key);
	// saveObject(list, key);
	//
	// }
	// }catch(AppException e){
	// list = (ChannelCategoryList)readObject(key);
	// if(list == null)
	// throw e;
	// }
	// } else {
	// list = (ChannelCategoryList)readObject(key);
	// if(list == null)
	// list = new ChannelCategoryList();
	// }
	// return list;
	// }

}
