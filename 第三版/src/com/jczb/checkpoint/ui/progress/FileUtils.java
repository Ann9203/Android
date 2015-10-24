package com.jczb.checkpoint.ui.progress;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件操作工具类
 * 
 * @author dwtedx
 * 
 */
public class FileUtils {

	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}