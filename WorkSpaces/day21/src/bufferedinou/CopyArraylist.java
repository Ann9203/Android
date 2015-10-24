package bufferedinou;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CopyArraylist {
	/**
	 * 在ArrayList里面存储了3个字符串元素，
	 * 请把这三个元素写入文本文件。
	 * 并在每个元素后面加入换行。
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
