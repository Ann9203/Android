
package com.ithei.dao.NewsDAO;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ithei.domain.newsbean.NewsBean;
import com.itheim.db.MySQLiteHelper;

public class NewsDAO {
	//这个就是获取新闻数据
	//添加新闻数据
	//同时删除旧的新闻数据
	//首先要写一个构造方法
	private MySQLiteHelper mysql;
	public NewsDAO(Context context)
	{
		this.mysql=new MySQLiteHelper(context);
	}
	
	//获取所有的新闻数据
	public ArrayList<NewsBean> getAllNews()
	{
		//获取数据库
		SQLiteDatabase sq = mysql.getReadableDatabase();
		//创建数据库的SQL语句
		ArrayList<NewsBean> array=new ArrayList<NewsBean>();
//		db.execSQL("create table news (_id integer primary key , title varchar(200),des varchar(200),time varchar(100),news_url varchar(200)," +
//		"icon_url varchar(200),comment integer ,type integer )");

		Cursor query = sq.query("news", new String[]{"_id","title","des","icon_url","news_url","time","comment","type"}, null, null, null, null, null);
		if(query!=null && query.getCount()>0)
		{
			while(query.moveToNext())
			{
				NewsBean nb=new NewsBean();
				nb._id=query.getInt(0);
				nb.title=query.getString(1);
				nb.des=query.getString(2);
				nb.icon_url=query.getString(3);
				nb.news_url=query.getString(4);
				nb.time=query.getString(5);
				nb.comment=query.getInt(6);
				nb.type=query.getInt(7);
				array.add(nb);
			}
		}
		sq.close();
		return array;
	}
	
	//当本地的数据库中没有数据的时候我们就要从服务器中去获取数据
	//获取数据之后就要先存到本地的数据库中然后才能从本地数据库中读取数据
	public void saveNews(ArrayList<NewsBean> array){
	
		SQLiteDatabase sql = mysql.getReadableDatabase();
		for(NewsBean nb:array)
		{
			ContentValues values=new ContentValues();
			values.put("_id",nb._id);
			values.put("title",nb.title);
			values.put("des",nb.des);
			values.put("icon_url",nb.icon_url);
			values.put("news_url",nb.news_url);
			values.put("time",nb.time);
			values.put("comment",nb.comment);
			values.put("type",nb.type);
			sql.insert("news", null, values);
			
		}
		sql.close();
	}
	
	//当从数据库获取新的数据的时候，就先要把本地的数据先要删除掉
	public void deleNews()
	{
		SQLiteDatabase sql = mysql.getReadableDatabase();
		sql.delete("news", null, null);
		sql.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
