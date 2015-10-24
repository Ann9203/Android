package com.itheima.Engien;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class ContactEngien {

	/**
	 * 获取联系人
	 * 
	 * @return
	 */
	public static List<HashMap<String, String>> getContact(Context context) {
		// 创建list的集合
		ArrayList<HashMap<String, String>> array = new ArrayList<HashMap<String, String>>();
		Uri raw_contacts = Uri
				.parse("content://com.android.contacts/raw_contacts");
		Uri data_url = Uri.parse("content://com.android.contacts/data");
		ContentResolver cr = context.getContentResolver();
		// 获取联系人Id
		Cursor cursor = cr.query(raw_contacts, new String[] { "contact_id" },
				null, null, null);
		// 但contact_id这个值没有被删除

		// 循环遍历联系人的Id
		while (cursor.moveToNext()) {
			if (cursor.getString(0) != null) {
				String contact_id = cursor.getString(0);
				// cursor.getColumnIndex("contact_id")
				// 根据得到的contact_id的值来查询data表中的数据
				Cursor cursor2 = cr.query(data_url, new String[] { "data1",
						"mimetype" }, "raw_contact_id=?",
						new String[] { contact_id }, null);
				// 定义hashMap
				HashMap<String, String> ha = new HashMap<String, String>();
				// 循环遍历表中的数据
				while (cursor2.moveToNext()) {
					String data1 = cursor2.getString(0);
					String mimetype = cursor2.getString(1);
					// 判断获取的mimetype的类型是哈
					if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
						ha.put("phone", data1);

					} else if (mimetype.equals("vnd.android.cursor.item/name")) {
						ha.put("name", data1);

					}
					System.out.println(ha.toString());
				}
				array.add(ha);
				cursor2.close();
			}
		

		}
		cursor.close();
		return array;
	}
}
