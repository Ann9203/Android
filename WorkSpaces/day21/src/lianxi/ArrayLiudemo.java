package lianxi;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayLiudemo {
	/*
	 * ���Ϻ�IO�Ľ���⣨��һ�飩
	 * */
	public static void main(String[] args) throws IOException {
		//ͨ��IO����ȡ�����е�����
		ArrayList<String> arrayList=new ArrayList<>();
		arrayList.add("Hell0");
		arrayList.add("java");
		arrayList.add("Andriod");
		
		//����һ��������
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("b.txt"));
		for (String string : arrayList) {
			bWriter.write(string);
			bWriter.newLine();
		}
		bWriter.close();
		
	}

}
