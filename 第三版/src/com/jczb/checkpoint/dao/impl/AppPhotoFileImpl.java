package com.jczb.checkpoint.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.jczb.checkpoint.dao.IAppPhotoFile;
import com.jczb.checkpoint.db.DBHelper;
import com.jczb.checkpoint.db.PhotoFile;
import com.jczb.checkpoint.model.AppPhotoFile;

/**
 * 图片表实现类
 * 
 * @author wlc
 * @date 2015-4-1
 */
public class AppPhotoFileImpl implements IAppPhotoFile {

	private DBHelper dbHelper;

	public AppPhotoFileImpl(Context context) {
		dbHelper = new DBHelper(context);
	}

	/**
	 * 添加一条记录
	 */
	@Override
	public void insert(AppPhotoFile photoFile) {

		//对于不能直接转换为字符串的特殊字段，只能通过这种方式而不能使用下面的那种方式。否则，保存进去将不正确。
		ContentValues values  = new ContentValues();
		values.put(PhotoFile.FIELDS.NAME, photoFile.getName());
		values.put(PhotoFile.FIELDS.BEGINDATE, photoFile.getBeginDate());
		values.put(PhotoFile.FIELDS.MAINID, photoFile.getMainID());
		values.put(PhotoFile.FIELDS.MAINSTYPE, photoFile.getMainStype());
		values.put(PhotoFile.FIELDS.FILENAME, photoFile.getFileName());
		values.put(PhotoFile.FIELDS.FILESUFFIX, photoFile.getFileType());
		values.put(PhotoFile.FIELDS.IMAGE, photoFile.getImage());
		dbHelper.insert(PhotoFile.TABLENAME, values);
		
		/*String sql = String.format(PhotoFile.OPERATION.INSERT,
				photoFile.getName(), photoFile.getBeginDate(),
				photoFile.getMainID(),photoFile.getMainStype(),
				photoFile.getFileName(),photoFile.getFileType(),
				photoFile.getImage());
		dbHelper.execSQL(sql);*/
	}

	@Override
	public void delete(int photoId) {

	}

	@Override
	public void delete(String condition) {

	}

	@Override
	public void update(AppPhotoFile photoFile) {

	}

	/**
	 * 根据条件获取图片
	 */
	@Override
	public List<AppPhotoFile> getPhotoByCondition(String condition) {

		String sql = String.format(PhotoFile.OPERATION.QUERY, condition);
		List<AppPhotoFile> list = new ArrayList<AppPhotoFile>();
		try {
			Cursor cursor = dbHelper.rawQuery(sql);
			AppPhotoFile photoFile=null;
			while(cursor.moveToNext()){
				 photoFile= new AppPhotoFile();
				photoFile.setBeginDate(cursor.getString(cursor.getColumnIndex(PhotoFile.FIELDS.BEGINDATE)));
				photoFile.setFileName(cursor.getString(cursor.getColumnIndex(PhotoFile.FIELDS.FILENAME)));
				photoFile.setFileType(cursor.getString(cursor.getColumnIndex(PhotoFile.FIELDS.FILESUFFIX)));
				photoFile.setImage(cursor.getBlob(cursor.getColumnIndex(PhotoFile.FIELDS.IMAGE)));
				photoFile.setMainID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PhotoFile.FIELDS.MAINID))));
				photoFile.setMainStype(cursor.getString(cursor.getColumnIndex(PhotoFile.FIELDS.MAINSTYPE)));
				photoFile.setName(cursor.getString(cursor.getColumnIndex(PhotoFile.FIELDS.NAME)));
				list.add(photoFile);
			}
		} catch (Exception e) {
			Log.e("getPhotoFailed", "e=" + e.getMessage());
		} finally{
			dbHelper.close();
		}
		
		return list;
	}

}
