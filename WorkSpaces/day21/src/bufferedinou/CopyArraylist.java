package bufferedinou;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CopyArraylist {
	/**
	 * ��ArrayList����洢��3���ַ���Ԫ�أ�
	 * ���������Ԫ��д���ı��ļ���
	 * ����ÿ��Ԫ�غ�����뻻�С�
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		ArrayList<String>arrayList=new ArrayList<>();
		BufferedWriter bWriter=new BufferedWriter(new FileWriter("bb.txt"));
		arrayList.add("Hello");
		arrayList.add("World");
		arrayList.add("Java");
		arrayList.add("Andriod");
		arrayList.add("EveryBody");
		for (String string : arrayList) {
			bWriter.write(string);
			bWriter.newLine();
			bWriter.flush();
		}
		bWriter.close();
	}

}
