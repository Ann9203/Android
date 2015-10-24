package com.jczb.checkpoint.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.R.string;
import android.util.Log;



/**
 * 网络请求工具类
 * 
 * @author   
 * 
 */
public class AgentApi {

	public static final String TAG_ROOT = "Request";
	public static final String TAG_MESSAGE_NAME = "MessageName";

	public static final String TAG_SID = "Sid";

	public static final String TAG_MESSAGE = "Message";

	public static final String TAG_CLIENT = "Client";
	private static String returnConnection;

	/**
	 * post请求方法
	 * @param parmas 请求时发送的参数
	 * @param url 请求的url
	 * @return
	 */
	public static  String dopost(Map<String, String> parmas, String url) {


		DefaultHttpClient client = new DefaultHttpClient();// http�ͻ���

		HttpPost httpPost = new HttpPost(url);
		//Log.i("url", HttpUrl.URL_DOMAIN+HttpUrl.URL_1+url);

		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

		if (parmas != null) {

			Set<String> keys = parmas.keySet();

			for (Iterator<String> i = keys.iterator(); i.hasNext();) {

				String key = (String) i.next();

				pairs.add(new BasicNameValuePair(key, (String) parmas.get(key)));

			}

		}

		try {

			UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs,
					"utf-8");

			httpPost.setEntity(p_entity);

			HttpResponse response = client.execute(httpPost);

			HttpEntity entity = response.getEntity();

			InputStream content = entity.getContent();

			 returnConnection = convertStreamToString(content);
			Log.i("returnConnection", returnConnection);
//			activity_main_et_1.setText(returnConnection);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return returnConnection;

	}
	public static  String doget(String url) {


		DefaultHttpClient client = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);
		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		try {
			UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs,
					"utf-8");

			httpPost.setEntity(p_entity);

			HttpResponse response = client.execute(httpPost);

			HttpEntity entity = response.getEntity();

			InputStream content = entity.getContent();

			 returnConnection = convertStreamToString(content);
			Log.i("returnConnection", returnConnection);
//			activity_main_et_1.setText(returnConnection);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return returnConnection;

	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				is.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return sb.toString();

	}
	
	
	
	
	
	
	
	
	/**
	 * 上传图片
	 * 
	 * @param filePath
	 *            图片地址
	 * @param username
	 * @return
	 */
	public static String uploadFile(String filePath) {

		return uploadFile(filePath, 0);
	}

	/**
	 * 
	 * @param filePath
	 * @param flag
	 *            0，默认，1随手拍
	 * @return随手拍
	 */
	public static String uploadFile(String filePath, int flag) {
		String result = null;
		BufferedReader rd = null;
		URL u = null;
		URLConnection conn = null;
		OutputStream os = null;
		try {
			 String url="http://linju.client.3g.soufun.com/agentupload";
//			String url = HttpUrl.URL_DOMAIN;
//			if (flag == 1) {
//				url = "/AgentPhoto";
//			}
			u = new URL(url);
			conn = u.openConnection();
			conn.setReadTimeout(150 * 1000);
			conn.setConnectTimeout(15 * 1000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			if (filePath != null) {
				os = conn.getOutputStream();
			}
			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[4096];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}
			rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			StringBuffer strBuffer = new StringBuffer();
			String rn = System.getProperty("line.separator");
			while ((result = rd.readLine()) != null) {
				strBuffer.append(result + rn);
			}
			result = strBuffer.toString();
			strBuffer.delete(0, strBuffer.length());
			if (fis != null)
				fis.close();
			if (rd != null)
				rd.close();
			if (os != null)
				os.close();
			conn = null;
			u = null;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if (result != null) {
			result = result.replaceAll("<.*?>", "").trim();
			result = result.replaceAll("[%]amp", "&");
		}
		return result;
	}

	/**
	 * 通过网络获取返回的InputStream
	 * 
	 * @param pairs
	 * @return
	 * @throws Exception
	 */
//	public static InputStream getInputStream(Map<String, String> pairs)
//			throws Exception {
//		AgentHttpClient mHttpClient = null;
//		try {
//			mHttpClient = new AgentHttpClient();
//			RequestEntity requestEntity = new RequestEntity();
//			// requestEntity.setMethod(Task.METHOD_POST);
//			requestEntity.setMethod(Task.METHOD_GET);
//			Set<Entry<String, String>> entrySet = pairs.entrySet();
//			for (Entry<String, String> entry : entrySet) {
//				requestEntity.addParam(entry.getKey(), entry.getValue());
//			}
//			AgentInputStream ais = mHttpClient.execute(requestEntity);
//			return ais.getImpl();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e.getMessage(), e);
//		} finally {
//			// if (mHttpClient != null) {
//			// mHttpClient.close();
//			// }
//		}
//	}
}