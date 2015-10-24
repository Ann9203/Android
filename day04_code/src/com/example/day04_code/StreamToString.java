package com.example.day04_code;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamToString {

	public static String changToString(InputStream in) {
		// TODO Auto-generated method stub
		// ����ת�����ַ���
		// ���ȴ���һ���ַ������
 		ByteArrayOutputStream bos = null;

		try {
			bos = new ByteArrayOutputStream();
			byte[] by = new byte[1024];
			int len = -1;
			while ((len = in.read(by)) != -1) {
				bos.write(by, 0, len);
				bos.flush();
			}
			bos.close();
			return bos.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
