package com.itheima.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;


public class StreamToString {
	
	/**
	 * 将字符流转换成字符串
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	public static String streamToString(InputStream in) throws IOException
	{
		//读取流的信息为字符串使用字符流
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		//穿件输出流
		StringWriter sw=new StringWriter();
		String result=null;
		
			while((result=br.readLine())!=null)
			{
				sw.write(result);
			}
			return sw.toString();
		
	}
}
