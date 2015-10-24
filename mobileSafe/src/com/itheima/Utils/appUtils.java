package com.itheima.Utils;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

public class appUtils {

	/**
	 * 获取SD卡可用的多少
	 * @return
	 */
	public static long getSD(){
		//获取SDcard
		File file = Environment.getExternalStorageDirectory();
		//获取硬盘API操作
		StatFs statfs=new StatFs(file.getPath());
		//获取总块数
		int blockCount = statfs.getBlockCount(); 	
		//获取每块的大小
		int blockSize = statfs.getBlockSize();
		//获取可用的快
		int availableBlocks = statfs.getAvailableBlocks();
		//返回可用的块数
		return availableBlocks*blockSize;			
	}
	
	/**
	 * 获取内存的大小
	 * @return
	 */
	public static long getRam(){
		     File file = Environment.getDataDirectory();
		     //获取对硬盘API的操作
		     StatFs statfs=new StatFs(file.getPath());
		    // long blockCountLong = statfs.getBlockCountLong();
		     //long availableBlocksLong = statfs.getAvailableBlocksLong();
		     //获取总块数
		      int blockCount = statfs.getBlockCount();
		      //获取每块的大小
		      int blockSize = statfs.getBlockSize();
		      //long blockSizeLong = statfs.getBlockSizeLong();
		      //获取有效的块数
		     int availableBlocks = statfs.getAvailableBlocks();
		      System.out.println(blockCount+",,,,,"+blockSize+",,,,,"+availableBlocks);
		      return availableBlocks*blockSize;
	}
	
	
}
