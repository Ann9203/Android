package com.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class StreamToString {
	/**
	 * 将字节流转换成字符串输出
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String streamToString(InputStream in) throws IOException{
		//创建buffered的字符输入流
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		//创建字符输出流
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
