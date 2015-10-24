package lianxi;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayLiudemo {
	/*
	 * 集合和IO的结合题（敲一遍）
	 * */
	public static void main(String[] args) throws IOException {
		//通过IO流读取集合中的数据
		ArrayList<String> arrayList=new ArrayList<>();
		arrayList.add("Hell0");
		arrayList.add("java");
		arrayList.add("Andriod");
		
		//定义一个流集合
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("b.txt"));
		for (String string : arrayList) {
			bWriter.write(string);
			bWriter.newLine();
		}
		bWriter.close();
		
	}

}
