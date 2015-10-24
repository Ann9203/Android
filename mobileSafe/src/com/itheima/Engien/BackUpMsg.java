package com.itheima.Engien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Xml;

import com.itheima.face.ProgressBarInterface;

public class BackUpMsg {

	private static ContentValues values;
	private static ContentResolver resolver;
	private static Uri uri;
	public static void backupMsg(Context context,ProgressBarInterface pb){
		//通过内容提供者来获取短信
		SystemClock.sleep(1000);
		ContentResolver contentResolver = context.getContentResolver();
		Uri uri=Uri.parse("content://sms");
		Cursor  cursor = contentResolver.query(uri, new String[]{"address","date","type","body"}, null, null, null);
		
		//查询出信息来
		//创建XML序列器
		XmlSerializer smlSerializer=Xml.newSerializer();
		//设置保存的路径
		try {
			smlSerializer.setOutput(new FileOutputStream(new File("/mnt/sdcard/backupmsg.xml")), "utf-8");
			//smlSerializer.setOutput(new FileOutputStream(new File("/mntsdcard/backupmsg.xml")), "utf-8");
			//设置表头
			smlSerializer.startDocument("utf-8", true);
			smlSerializer.startTag(null, "smss");
			pb.setMax(cursor.getCount());
			//获取信息，填充信息
			int count=0;
			while(cursor.moveToNext()){
				count++;
				pb.setProgress(count);
				SystemClock.sleep(1000);
				smlSerializer.startTag(null, "sms");
				
				smlSerializer.startTag(null, "address");
				String address=cursor.getString(0);
				smlSerializer.text(address);
				smlSerializer.endTag(null, "address");
				
				smlSerializer.startTag(null, "date");
				String date=cursor.getString(1);
				smlSerializer.text(date);
				smlSerializer.endTag(null, "date");
				
				smlSerializer.startTag(null, "type");
				String type=cursor.getString(2);
				smlSerializer.text(type);
				smlSerializer.endTag(null, "type");
				
				smlSerializer.startTag(null, "body");
				String body=cursor.getString(3);
				smlSerializer.text(body);
				smlSerializer.endTag(null, "body");
				
				smlSerializer.endTag(null, "sms");
				System.out.println("address:"+address+"   date:"+date+"  type:"+type+"  body:"+body);

			}
			
			smlSerializer.endTag(null, "smss");
			smlSerializer.endDocument();
			smlSerializer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void RestoreMsg(Context context){
		//解析短息
		//pull解析
		XmlPullParser xmlpullParser=Xml.newPullParser();
		//设置要独处的数据
		try {
			xmlpullParser.setInput(new FileInputStream(new File("/mnt/sdcard/backupmsg.xml")), "utf-8");
			//设置当前xml的事件类型
			int eventType = xmlpullParser.getEventType();
			//循环判断事件类型是否是文档结束类型
			while(eventType!=XmlPullParser.END_DOCUMENT){
				//如果不是文档结尾可以继续进行解析
				//获取当前标签的名称
				String tagName=xmlpullParser.getName();
				switch(eventType){
					case XmlPullParser.START_TAG://开始标签
						if("smss".equals(tagName)){
							//如果是当前的开始标签，就要进行初始化对象
							resolver = context.getContentResolver();
							uri = Uri.parse("content://sms");
						}else if("sms".equals(tagName)){
							values = new ContentValues();
						}else if("address".equals(tagName)){
							String address = xmlpullParser.nextText();
							values.put("address", address);
						}else if("date".equals(tagName)){
							String date=xmlpullParser.nextText();
							values.put("date", date);
						}else if("type".equals(tagName)){
							String type=xmlpullParser.nextText();
							values.put("type", type);
						}else if("body".equals(tagName)){
							String body=xmlpullParser.nextText();
							values.put("body", body);
						}
						break;
					case XmlPullParser.END_TAG:
						if("sms".equals(tagName)){
							resolver.insert(uri, values);
						}
						break;
					default :
						break;
				
				}
				//解析到最后一行的时候需要重新获取一下事件
				eventType=xmlpullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
