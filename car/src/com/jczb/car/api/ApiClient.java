package com.jczb.car.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jczb.car.AppContext;
import com.jczb.car.AppException;
import com.jczb.car.bean.ChannelCategory;
import com.jczb.car.bean.ChannelCategoryList;
import com.jczb.car.bean.ChannelCategoryVo;
import com.jczb.car.bean.ChannelContentCommentVo;
import com.jczb.car.bean.Content;
import com.jczb.car.bean.ContentVo;
import com.jczb.car.bean.MyCollectionVo;
import com.jczb.car.bean.MyCommentVo;
import com.jczb.car.bean.SiteUser;
import com.jczb.car.bean.SiteUserVo;
import com.jczb.car.bean.URLs;
import com.jczb.car.bean.User;
import com.jczb.car.bean.ValidateCode;
import com.jczb.car.bean.VersionInfoVo;

public class ApiClient {

	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";

	private final static int TIMEOUT_CONNECTION = 20000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int RETRY_TIME = 3;

	private static String appCookie;
	private static String appUserAgent;

	public static void cleanCookie() {
		appCookie = "";
	}

	private static String getCookie(AppContext appContext) {
		if (appCookie == null || appCookie == "") {
			appCookie = appContext.getProperty("cookie");
		}
		return appCookie;
	}

	/**
	 * 登录， 自动处理cookie
	 * 
	 * @param url
	 * @param username
	 * @param pwd
	 * @return
	 * @throws AppException
	 */
	public static User login(AppContext appContext, String username, String pwd)
			throws AppException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("pwd", pwd);
		params.put("keep_login", 1);

		String loginurl = URLs.LOGIN_VALIDATE_HTTP;
		if (appContext.isHttpsLogin()) {
			loginurl = URLs.LOGIN_VALIDATE_HTTPS;
		}

		try {
			return JSON.parseObject(
					http_post(appContext, loginurl, params, null), User.class);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	private static String getUserAgent(AppContext appContext) {
		if (appUserAgent == null || appUserAgent == "") {
			StringBuilder ua = new StringBuilder("OSChina.NET");
			ua.append('/' + appContext.getPackageInfo().versionName + '_'
					+ appContext.getPackageInfo().versionCode);// App版本
			ua.append("/Android");// 手机系统平台
			ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
			ua.append("/" + android.os.Build.MODEL); // 手机型号
			ua.append("/" + appContext.getAppId());// 客户端唯一标识
			appUserAgent = ua.toString();
		}
		return appUserAgent;
	}

	private static HttpClient getHttpClient() {
		HttpClient httpClient = new HttpClient();
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		httpClient.getParams().setCookiePolicy(
				CookiePolicy.BROWSER_COMPATIBILITY);
		// 设置 默认的超时重试处理策略
		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// 设置 连接超时时间
		httpClient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(TIMEOUT_CONNECTION);
		// 设置 读数据超时时间
		httpClient.getHttpConnectionManager().getParams()
				.setSoTimeout(TIMEOUT_SOCKET);
		// 设置 字符集
		httpClient.getParams().setContentCharset(UTF_8);
		return httpClient;
	}

	private static GetMethod getHttpGet(String url, String cookie,
			String userAgent) {
		GetMethod httpGet = new GetMethod(url);
		// 设置 请求超时时间
		httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
		httpGet.setRequestHeader("Host", URLs.HOST);
		httpGet.setRequestHeader("Connection", "Keep-Alive");
		httpGet.setRequestHeader("Cookie", cookie);
		httpGet.setRequestHeader("User-Agent", userAgent);
		return httpGet;
	}

	private static PostMethod getHttpPost(String url, String cookie,
			String userAgent) {
		PostMethod httpPost = new PostMethod(url);
		// 设置 请求超时时间
		httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
		// httpPost.setRequestHeader("Host", URLs.HOST);
		httpPost.setRequestHeader("Connection", "Keep-Alive");
		httpPost.setRequestHeader("Cookie", cookie);
		httpPost.setRequestHeader("User-Agent", userAgent);
		return httpPost;
	}

	private static String _MakeURL(String p_url, Map<String, Object> params) {
		StringBuilder url = new StringBuilder(p_url);
		if (url.indexOf("?") < 0)
			url.append('?');

		for (String name : params.keySet()) {
			url.append('&');
			url.append(name);
			url.append('=');
			url.append(String.valueOf(params.get(name)));
			// 不做URLEncoder处理
			// url.append(URLEncoder.encode(String.valueOf(params.get(name)),
			// UTF_8));
		}

		return url.toString().replace("?&", "?");
	}

	/**
	 * get请求URL
	 * 
	 * @param url
	 * @throws AppException
	 */
	private static String http_get(AppContext appContext, String url)
			throws AppException {
		System.out.println("get_url==> " + url);
		String cookie = getCookie(appContext);
		String userAgent = getUserAgent(appContext);

		HttpClient httpClient = null;
		GetMethod httpGet = null;

		String responseBody = "";
		int time = 0;
		do {
			try {
				httpClient = getHttpClient();
				httpGet = getHttpGet(url, cookie, userAgent);
				int statusCode = httpClient.executeMethod(httpGet);
				if (statusCode != HttpStatus.SC_OK) {
					throw AppException.http(statusCode);
				}
				responseBody = httpGet.getResponseBodyAsString();
				System.out.println("XMLDATA=====>" + responseBody);
				break;
			} catch (HttpException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				e.printStackTrace();
				throw AppException.http(e);
			} catch (IOException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生网络异常
				e.printStackTrace();
				throw AppException.network(e);
			} catch (Exception e) {
				Log.e("访问出错了------>>>>>>", e.toString());
			} finally {
				// 释放连接
				httpGet.releaseConnection();
				httpClient = null;
			}
		} while (time < RETRY_TIME);

		responseBody = responseBody.replaceAll("\r\n", "");
		responseBody = responseBody.replaceAll("\t", "");

		responseBody = responseBody.replaceAll("\\p{Cntrl}", "");
		if (responseBody.contains("result")
				&& responseBody.contains("errorCode")
				&& appContext.containsProperty("user.uid")) {
			// try {
			// Result res = Result.parse(new
			// ByteArrayInputStream(responseBody.getBytes()));
			// if(res.getErrorCode() == 0){
			// appContext.Logout();
			// appContext.getUnLoginHandler().sendEmptyMessage(1);
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}
		return responseBody;
	}

	/**
	 * 公用post方法
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @throws AppException
	 */
	private static String http_post(AppContext appContext, String url,
			Map<String, Object> params, Map<String, File> files)
			throws AppException {
		System.out.println("post_url==> " + url);
		String cookie = getCookie(appContext);
		String userAgent = getUserAgent(appContext);

		HttpClient httpClient = null;
		PostMethod httpPost = null;

		// post表单参数处理
		int length = (params == null ? 0 : params.size())
				+ (files == null ? 0 : files.size());
		Part[] parts = new Part[length];
		int i = 0;
		if (params != null)
			for (String name : params.keySet()) {
				parts[i++] = new StringPart(name, String.valueOf(params
						.get(name)), UTF_8);
				System.out.println("post_key==> " + name + "    value==>"
						+ String.valueOf(params.get(name)));
			}
		if (files != null)
			for (String file : files.keySet()) {
				try {
					parts[i++] = new FilePart(file, files.get(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("post_key_file==> " + file);
			}

		String responseBody = "";
		int time = 0;
		do {
			try {
				httpClient = getHttpClient();
				httpPost = getHttpPost(url, cookie, userAgent);
				httpPost.setRequestEntity(new MultipartRequestEntity(parts,
						httpPost.getParams()));
				int statusCode = httpClient.executeMethod(httpPost);
				if (statusCode != HttpStatus.SC_OK) {
					throw AppException.http(statusCode);
				} else if (statusCode == HttpStatus.SC_OK) {
					Cookie[] cookies = httpClient.getState().getCookies();
					String tmpcookies = "";
					for (Cookie ck : cookies) {
						tmpcookies += ck.toString() + ";";
					}
					// 保存cookie
					if (appContext != null && tmpcookies != "") {
						appContext.setProperty("cookie", tmpcookies);
						appCookie = tmpcookies;
					}
				}
				responseBody = httpPost.getResponseBodyAsString();
				System.out.println("XMLDATA=====>" + responseBody);
				break;
			} catch (HttpException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				e.printStackTrace();
				throw AppException.http(e);
			} catch (IOException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生网络异常
				e.printStackTrace();
				throw AppException.network(e);
			} finally {
				// 释放连接
				httpPost.releaseConnection();
				httpClient = null;
			}
		} while (time < RETRY_TIME);

		responseBody = responseBody.replaceAll("\\p{Cntrl}", "");
		if (responseBody.contains("result")
				&& responseBody.contains("errorCode")
				&& appContext.containsProperty("user.uid")) {
			// try {
			// Result res = Result.parse(new
			// ByteArrayInputStream(responseBody.getBytes()));
			// if(res.getErrorCode() == 0){
			// appContext.Logout();
			// appContext.getUnLoginHandler().sendEmptyMessage(1);
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}
		return responseBody;// new
							// ByteArrayInputStream(responseBody.getBytes());
	}

	/**
	 * post请求URL
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @throws AppException
	 * @throws IOException
	 * @throws
	 */
	// private static Result http_post(AppContext appContext, String url,
	// Map<String, Object> params, Map<String,File> files) throws AppException,
	// IOException {
	// return Result.parse(_post(appContext, url, params, files));
	// }
	/**
	 * 获取内容详情
	 * 
	 * @param blog_id
	 * @return
	 * @throws AppException
	 */
	public static Content getContentDetail(AppContext appContext,
			final int content_id) throws AppException {
		String newUrl = _MakeURL(URLs.BLOG_DETAIL,
				new HashMap<String, Object>() {
					{
						put("id", content_id);
					}
				});

		try {
			return JSON
					.parseObject(http_get(appContext, newUrl), Content.class);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取内容列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws AppException
	 */
	public static ContentVo getContentList(AppContext appContext,
			final int pageIndex, final int pageSize) throws AppException {
		String newUrl = _MakeURL(URLs.CONTENT_LIST,
				new HashMap<String, Object>() {
					{
						put("pageIndex", pageIndex);
						put("pageSize", pageSize);
					}
				});

		try {
			return JSON.parseObject(http_get(appContext, newUrl),
					ContentVo.class);
			// return BlogList.parse(http_get(appContext, newUrl));

		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取网络图片
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getNetBitmap(String url) throws AppException {
		// System.out.println("image_url==> "+url);
		HttpClient httpClient = null;
		GetMethod httpGet = null;
		Bitmap bitmap = null;
		int time = 0;
		do {
			try {
				httpClient = getHttpClient();
				httpGet = getHttpGet(url, null, null);
				int statusCode = httpClient.executeMethod(httpGet);
				if (statusCode != HttpStatus.SC_OK) {
					throw AppException.http(statusCode);
				}
				InputStream inStream = httpGet.getResponseBodyAsStream();
				bitmap = BitmapFactory.decodeStream(inStream);
				inStream.close();
				break;
			} catch (HttpException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生致命的异常，可能是协议不对或者返回的内容有问题
				e.printStackTrace();
				throw AppException.http(e);
			} catch (IOException e) {
				time++;
				if (time < RETRY_TIME) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					continue;
				}
				// 发生网络异常
				e.printStackTrace();
				throw AppException.network(e);
			} finally {
				// 释放连接
				httpGet.releaseConnection();
				httpClient = null;
			}
		} while (time < RETRY_TIME);
		return bitmap;
	}

	// ------------------------------吴利昌添加的方法--开始------------------------------------------
	/**
	 * 获取验证码
	 * 
	 * @return 字符串
	 * @user 吴利昌
	 * @date 2015-8-27 下午5:47:36
	 */
	public static String getValidateCode(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 定义要访问的接口和要强转的实体
		String validateUrl = _MakeURL(URLs.VALIDATE_CODE_URL, map);
		ValidateCode validateCode = null;

		try {

			// 获取服务器端Json数据
			String json = http_get(appContext, validateUrl);

			// 解析为制定的实体对象
			validateCode = (ValidateCode) JSON.parseObject(json,
					ValidateCode.class);

		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		// 返回验证码
		return validateCode.getCode();
	}

	/**
	 * 注册并获取注册是否成功的状态
	 * 
	 * @param appContext
	 *            Application
	 * @param map
	 *            注册时带过去的参数
	 * @return 布尔值，是否成功
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-8-28 上午10:47:08
	 */
	public static boolean getRegisterStatus(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 定义返回状态
		boolean flag = false;
		String registStatus = "false";

		// 拼接带参数的url地址
		String registUrl = _MakeURL(URLs.REGISTER_URL, map);

		try {

			// 获取服务器端Json数据
			String json = http_get(appContext, registUrl);

			// 解析为fastJson自带的对象类型
			JSONObject object = (JSONObject) JSON.parse(json);

			// 获取注册的结果
			registStatus = object.getString("result");

		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		// 返回的字符串为true，则返回布尔值true
		if (registStatus == "true") {
			flag = true;
		}

		return flag;
	}

	/**
	 * 登陆并获取站点用户信息
	 * 
	 * @param appContext
	 *            全局Application
	 * @param map
	 *            登陆时带过去的参数集合
	 * @return 站点用户
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-8-28 下午3:35:42
	 */
	public static SiteUser getSiteUser(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 拼接带参数的Url地址
		String loginUrl = _MakeURL(URLs.LOGIN_URL, map);

		// 定义SiteUser对象
		SiteUser siteUser = null;
		SiteUserVo siteUserVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, loginUrl);

			// 解析为SiteUserVo对象
			siteUserVo = (SiteUserVo) JSON.parseObject(json, SiteUserVo.class);

			// 获取SiteUser
			siteUser = siteUserVo.getSiteUser().get(0);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return siteUser;

	}

	/**
	 * 修改密码
	 * 
	 * @param appContext
	 *            全局Application
	 * @param map
	 *            携带新密码和用户名的参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 上午9:36:40
	 */
	public static boolean modifyPassword(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 定义返回状态
		boolean flag = false;
		String modifyStatus = "false";

		// 拼接带参数的url地址
		String modifyUrl = _MakeURL(URLs.UPDATE_PWD_URL, map);

		try {

			// 获取服务器端Json数据
			String json = http_get(appContext, modifyUrl);

			// 解析为fastJson自带的对象类型
			JSONObject object = (JSONObject) JSON.parse(json);

			// 获取修改的结果
			modifyStatus = object.getString("result");

		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		// 返回的字符串为true，则返回布尔值true
		if (modifyStatus == "true") {
			flag = true;
		}

		return flag;

	}

	/**
	 * 修改昵称
	 * 
	 * @param appContext
	 *            全局Application
	 * @param map
	 *            携带新昵称和用户名的参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-08-31 09:58:42
	 */
	public static boolean modifyNickname(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 定义返回状态
		boolean flag = false;
		String modifyStatus = "false";

		// 拼接带参数的url地址
		String modifyUrl = _MakeURL(URLs.UPDATE_NICKNAME_URL, map);

		try {

			// 获取服务器端Json数据
			String json = http_get(appContext, modifyUrl);

			// 解析为fastJson自带的对象类型
			JSONObject object = (JSONObject) JSON.parse(json);

			// 获取修改的结果
			modifyStatus = object.getString("result");

		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		// 返回的字符串为true，则返回布尔值true
		if (modifyStatus == "true") {
			flag = true;
		}

		return flag;

	}

	/**
	 * 上传头像 Post提交方式
	 * 
	 * @param appContext
	 *            Application
	 * @param params
	 *            除File类型之外的参数
	 * @param files
	 *            文件类型
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-8-31 上午10:23:26
	 */
	public static boolean uploadImage(AppContext appContext,
			Map<String, Object> params, Map<String, File> files)
			throws AppException {

		// 定义返回状态
		boolean flag = false;
		String uploadStatus = "false";

		// 拼接带参数的url地址
		String uploadUrl = URLs.UPLOAD_IMG_URL;

		try {

			// 获取服务器端Json数据
			String json = http_post(appContext, uploadUrl, params, files);

			// 解析为fastJson自带的对象类型
			JSONObject object = (JSONObject) JSON.parse(json);

			// 获取修改的结果
			uploadStatus = object.getString("result");

		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		// 返回的字符串为true，则返回布尔值true
		if (uploadStatus == "true") {
			flag = true;
		}

		return flag;

	}

	/**
	 * 发现
	 * 
	 * @param appContext
	 *            全局Application
	 * @return
	 * @user 吴利昌
	 * @date 2015-08-31 13:55:07
	 */
	public static ContentVo discover(AppContext appContext) throws AppException {

		// 拼接带参数的Url地址
		String discoverUrl = URLs.DISCOVER_URL;

		// 声明映射类
		ContentVo contentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, discoverUrl);

			// 解析为SiteUserVo对象
			contentVo = (ContentVo) JSON.parseObject(json, ContentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return contentVo;

	}

	/**
	 * 我的收藏
	 * 
	 * @param appContext
	 *            Appication
	 * @param map
	 *            带pagenow和count以及用户id的参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 下午2:43:44
	 */
	public static MyCollectionVo getCollection(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 拼接带参数的Url地址
		String collectionUrl = _MakeURL(URLs.MYCOLLECTION_URL, map);

		// 声明映射类
		MyCollectionVo myCollectionVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, collectionUrl);

			// 解析为SiteUserVo对象
			myCollectionVo = (MyCollectionVo) JSON.parseObject(json,
					MyCollectionVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return myCollectionVo;
	}

	/**
	 * 我的评论
	 * 
	 * @param appContext
	 *            Appication
	 * @param map
	 *            带pagenow和count以及用户id的参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-8-31 下午2:43:44
	 */
	public static MyCommentVo getEvaluation(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 拼接带参数的Url地址
		String commentUrl = _MakeURL(URLs.MYCOMMENT_URL, map);

		// 声明映射类
		MyCommentVo myCommentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, commentUrl);

			// 解析为MyCommentVo对象
			myCommentVo = (MyCommentVo) JSON.parseObject(json,
					MyCommentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return myCommentVo;
	}

	/**
	 * 频道内容评论
	 * 
	 * @param appContext
	 *            Application
	 * @param map
	 *            带pagenow和count以及频道内容id的参数集合
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-08-31 17:29:27
	 */
	public static ChannelContentCommentVo getChannelEvaluation(
			AppContext appContext, Map<String, Object> map) throws AppException {

		// 拼接带参数的Url地址
		String commentUrl = _MakeURL(URLs.CHANNELCONTENT_COMMENT_URL, map);

		// 声明映射类
		ChannelContentCommentVo channelContentCommentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, commentUrl);

			// 解析为ChannelContentCommentVo对象
			channelContentCommentVo = (ChannelContentCommentVo) JSON
					.parseObject(json, ChannelContentCommentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return channelContentCommentVo;
	}

	/**
	 * 版本信息
	 * 
	 * @param appContext
	 *            Appication
	 * @param map
	 *            带pagenow和count以及用户id的参数集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-08-31 17:49:03
	 */
	public static VersionInfoVo getVersion(AppContext appContext,
			Map<String, Object> map) throws AppException {

		// 拼接带参数的Url地址
		String versionUrl = _MakeURL(URLs.VERSION_URL, map);

		// 声明映射类
		VersionInfoVo versionInfoVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, versionUrl);

			// 解析为MyCommentVo对象
			versionInfoVo = (VersionInfoVo) JSON.parseObject(json,
					VersionInfoVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return versionInfoVo;
	}

	/**
	 * 添加评论
	 * 
	 * @param appContext
	 *            Appilication
	 * @param params
	 *            用户Id、被评论内容的Id、评论内容参数集合
	 * @return
	 * @throws AppException
	 * @user 吴利昌
	 * @date 2015-8-31 下午6:10:33
	 */
	public static boolean addComment(AppContext appContext,
			Map<String, Object> params) throws AppException {

		// 定义返回状态
		boolean flag = false;
		String addStatus = "false";

		// 拼接带参数的url地址
		String addCommentUrl = _MakeURL(URLs.ADD_COMMENT_URL, params);

		try {
			// 获取服务器端Json数据
			String json = http_get(appContext, addCommentUrl);

			// 解析为fastJson自带的对象类型
			JSONObject object = (JSONObject) JSON.parse(json);

			// 获取修改的结果
			addStatus = object.getString("result");

		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		// 返回的字符串为true，则返回布尔值true
		if (addStatus == "true") {
			flag = true;
		}

		return flag;

	}

	/**
	 * 获取所有频道列表
	 * 
	 * @param appContext
	 *            Appication
	 * @return
	 * @user 吴利昌
	 * @date 2015-09-01 08:47:11
	 */
	public static ChannelCategoryVo getAllChannelCategory(AppContext appContext)
			throws AppException {

		// 拼接带参数的Url地址
		String channelUrl = URLs.CHANNEL_CATEGORY_URL;

		// 声明映射类
		ChannelCategoryVo channelCategoryVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, channelUrl);

			// 解析为channelCategoryVo对象
			channelCategoryVo = (ChannelCategoryVo) JSON.parseObject(json,
					ChannelCategoryVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return channelCategoryVo;
	}

	/**
	 * 获取段子内容详细信息(这个应该是图文，视频的接口不对）
	 * 
	 * @param appContext
	 *            Application
	 * @param params
	 *            带频道内容id和评到类型(视频(1)或图文(0))参数的集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 上午9:12:24
	 */
	public static ContentVo getPassageContent(AppContext appContext,
			Map<String, Object> params) throws AppException {

		// 拼接带参数的Url地址
		String contentUrl = _MakeURL(URLs.PASSAGE_CONTENT_URL, params);

		// 声明映射类
		ContentVo contentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, contentUrl);

			// 解析为contentVo对象
			contentVo = (ContentVo) JSON.parseObject(json, ContentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return contentVo;
	}
	/**
	 * 获取段子内容详细信息(这个应该是图文，视频的接口不对）
	 * 
	 * @param appContext
	 *            Application
	 * @param params
	 *            带频道内容id和评到类型(视频(1)或图文(0))参数的集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-1 上午9:12:24
	 */
	public static ContentVo getSearchContent(AppContext appContext,
			Map<String, Object> params) throws AppException {

		// 拼接带参数的Url地址
		String contentUrl = _MakeURL(URLs.SEARCH_CONTENT_URL, params);

		// 声明映射类
		ContentVo contentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, contentUrl);

			// 解析为contentVo对象
			contentVo = (ContentVo) JSON.parseObject(json, ContentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return contentVo;
	}
	/**
	 * 获取段子的视频内容详细信息
	 * 
	 * @param appContext
	 *            Application
	 * @param params
	 *            带频道内容id和频道类型(视频(0))参数的集合
	 * @return
	 * @user 吴利昌
	 * @date 2015-09-06 21:26:12
	 */
	public static ContentVo getVideoDetailContent(AppContext appContext,
			Map<String, Object> params) throws AppException {

		// 拼接带参数的Url地址
		String contentUrl = _MakeURL(URLs.PASSAGE_CONTENT_URL, params);

		// 声明映射类
		ContentVo contentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, contentUrl);

			// 解析为contentVo对象
			contentVo = (ContentVo) JSON.parseObject(json, ContentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return contentVo;
	}
	

	/**
	 * 获取各频道下面的内容信息
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-9-5 上午11:50:26
	 */
	public static ContentVo getContentByCategory(AppContext appContext,
			Map<String, Object> params) throws AppException{
		
		// 拼接带参数的Url地址
		String contentUrl = _MakeURL(URLs.CATEGORY_CONTENT_URL, params);

		// 声明映射类
		ContentVo contentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, contentUrl);

			// 解析为contentVo对象
			contentVo = (ContentVo) JSON.parseObject(json, ContentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return contentVo;
	}
	
	/**
	 * 获取推荐的频道内容信息
	 * 
	 * @return
	 * @user 吴利昌
	 * @date 2015-09-05 12:32:34
	 */
	public static ContentVo getRecommandCategoryContent(AppContext appContext,
			Map<String, Object> params) throws AppException{
		
		// 拼接带参数的Url地址
		String contentUrl = _MakeURL(URLs.RECOMMEND_CONTENT_URL, params);

		// 声明映射类
		ContentVo contentVo = null;

		try {
			// 获取服务器端Json串
			String json = http_get(appContext, contentUrl);

			// 解析为contentVo对象
			contentVo = (ContentVo) JSON.parseObject(json, ContentVo.class);

		} catch (Exception e) {

			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}

		return contentVo;
	}

	// -----------------------吴利昌添加的方法----结束--------------------------------------------------

	/**
	 * 说明：获取频道详情 作者：丁国华 时间：2015年8月21日 09:14:22
	 */

	// public static ChannelCategory getChannelCategorytDetail(
	// AppContext appContext, final int ChannelCategory_id)
	// throws AppException {
	// String newUrl = _MakeURL(URLs.test, new HashMap<String, Object>() {
	// {
	// put("id", ChannelCategory_id);
	// }
	// });
	//
	// try {
	// return JSON.parseObject(http_get(appContext, newUrl),
	// ChannelCategory.class);
	// } catch (Exception e) {
	//
	// if (e instanceof AppException)
	// throw (AppException) e;
	// throw AppException.network(e);
	// }
	// }
	//
	// /**
	// * 说明：获取频道详情列表 作者：丁国华 时间：2015年8月21日 09:14:22
	// */
	// public static ChannelCategoryList getChannelCategoryList(
	// AppContext appContext, final int pageIndex, final int pageSize)
	// throws AppException {
	// String newUrl = _MakeURL(URLs.test, new HashMap<String, Object>() {
	// {
	// put("pageIndex", pageIndex);
	// put("pageSize", pageSize);
	// }
	// });
	//
	// try {
	// // String
	// contentString =
	// "{\"ChannelCategoryListCount\": 22,\"pageSize\": 33,\"id\": 1}";
	// // String
	// contentString =
	// "{\"ChannelCategoryListCount\": 22,\"pageSize\": 33,\"ChannelCategory\": [{\"channelName\": \"推荐\",\"id\": 1},{\"channelName\": \"推荐1\",\"id\": 11}]}";
	// String contentString = http_get(appContext, newUrl);
	// // ChannelCategoryList cl= JSON.parseObject(http_get(appContext,
	// // newUrl),
	//
	// ChannelCategoryList cl = JSON.parseObject(contentString,
	// ChannelCategoryList.class);
	//
	// // Log.i("==============", cl.getPageSize()+"=====");
	// return cl;
	// // return JSON.parseObject(http_get(appContext, newUrl),
	// // ChannelCategoryList.class);
	// // return BlogList.parse(http_get(appContext, newUrl));
	//
	// } catch (Exception e) {
	// if (e instanceof AppException)
	// throw (AppException) e;
	// throw AppException.network(e);
	// }
	// }

}
