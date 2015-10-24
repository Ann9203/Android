package com.ithei.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.ithei.dao.NewsDAO.NewsDAO;
import com.ithei.domain.newsbean.NewsBean;

public class Utils {
		//主要的功能就是从服务端获取数据
	//从本地数据库获取新闻
	public static  ArrayList<NewsBean> getNewsForLocal(Context context)
	{
		return new NewsDAO(context).getAllNews();
	}
	//从服务器端获取数据
	
	public static ArrayList<NewsBean> getNewsForServlet(Context context)
	{
		ArrayList<NewsBean> array=new ArrayList<NewsBean>();
		//url
		String path ="http://192.168.56.1:8090/itheima74/servlet/GetNewsServlet";
		try{
			URL url = new URL(path);
			HttpURLConnection open = (HttpURLConnection) url.openConnection();
			InputStream in = open.getInputStream();
			String result=StreamUtil.streamToString(in);
			//解析JESON
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("newss");
			//解析jsonarray
			for(int i=0;i<jsonArray.length();i++)
			{
				JSONObject jsObj = jsonArray.getJSONObject(i);
				NewsBean nb=new NewsBean();
				nb._id=jsObj.getInt("id");
				nb.comment=jsObj.getInt("comment");
				nb.type=jsObj.getInt("type");
				nb.title=jsObj.getString("title");
				nb.des=jsObj.getString("des");
				nb.time=jsObj.getString("time");
				nb.icon_url=jsObj.getString("icon_url");
				nb.news_url=jsObj.getString("news_url");
				array.add(nb);
				
			}
			//将数据添加到本地的数据库中
			NewsDAO newsDao=new NewsDAO(context);
			newsDao.deleNews();
			newsDao.saveNews(array);
			in.close();
			open.disconnect();

		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return array;
	}
	
}
