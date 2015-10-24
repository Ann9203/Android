package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class StreamToString {
	/**
	 * ���ֽ���ת�����ַ������
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String streamToString(InputStream in) throws IOException{
		//����buffered���ַ�������
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		//�����ַ������
		StringWriter sw=new StringWriter();
		String len=null;
		while((len=br.readLine())!=null){
			sw.write(len);
		}
		br.close();
		sw.close();
		return sw.toString();
	}

}
