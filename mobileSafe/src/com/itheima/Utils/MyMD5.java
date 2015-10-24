package com.itheima.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyMD5 {

	public static String passwordMD5(String pwd) {
		StringBuffer sb = new StringBuffer();
		// 首先获取内容摘要其
		MessageDigest messagedigest;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
			// 将密码转换成字符串
			byte[] digest = messagedigest.digest(pwd.getBytes());
			// 遍历字节数组
			for (int i = 0; i < digest.length; i++) {
				// 进行计算返回一个新的值
				int result = digest[i] & 0xff;
				// 将得到的int类型的数据转换成16禁止
				String hexString = Integer.toHexString(result);
				if (hexString.length() < 2) {
					sb.append(0);
				}
				sb.append(result);

			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
