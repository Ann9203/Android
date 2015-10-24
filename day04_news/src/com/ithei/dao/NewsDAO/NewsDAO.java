
package com.ithei.dao.NewsDAO;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ithei.domain.newsbean.NewsBean;
import com.itheim.db.MySQLiteHelper;

public class NewsDAO {
	//������ǻ�ȡ��������
	//�����������
	//ͬʱɾ���ɵ���������
	//����Ҫдһ�����췽��
	private MySQLiteHelper mysql;
	public NewsDAO(Context context)
	{
		this.mysql=new MySQLiteHelper(context);
	}
	
	//��ȡ���е���������
	public ArrayList<NewsBean> getAllNews()
	{
		//��ȡ���ݿ�
		SQLiteDatabase sq = mysql.getReadableDatabase();
		//�������ݿ��SQL���
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
	
	//�����ص����ݿ���û�����ݵ�ʱ�����Ǿ�Ҫ�ӷ�������ȥ��ȡ����
	//��ȡ����֮���Ҫ�ȴ浽���ص����ݿ���Ȼ����ܴӱ������ݿ��ж�ȡ����
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
	
	//�������ݿ��ȡ�µ����ݵ�ʱ�򣬾���Ҫ�ѱ��ص�������Ҫɾ����
	public void deleNews()
	{
		SQLiteDatabase sql = mysql.getReadableDatabase();
		sql.delete("news", null, null);
		sql.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
