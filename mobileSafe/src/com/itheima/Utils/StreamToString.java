package com.itheima.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;


public class StreamToString {
	
	/**
	 * ���ַ���ת�����ַ���
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	public static String streamToString(InputStream in) throws IOException
	{
		//��ȡ������ϢΪ�ַ���ʹ���ַ���
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		//���������
		StringWriter sw=new StringWriter();
		String result=null;
		
			while((result=br.readLine())!=null)
			{
				sw.write(result);
			}
			return sw.toString();
		
	}
}
