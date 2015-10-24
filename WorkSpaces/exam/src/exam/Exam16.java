package exam;

import java.util.Set;
import java.util.TreeMap;

import sun.reflect.generics.tree.Tree;

public class Exam16 {
	/*
	 * 
	 * 7��ȡ��һ���ַ�������ĸ���ֵĴ������磺�ַ�����"abcdekka27qoq" �������ʽΪ��a(2)b(1)k(2)...
	 * 
	 * ˼·��
	 * 		A:���ַ���ת��Ϊ�ַ�����
	 * 		B:Ȼ�󴴽�Map����
	 * 		C:ͨ�������жϣ��������е�Ԫ����ӵ�Map������ȥ
	 * 		D:Ȼ��ͨ��BufferBuilder�����д�ӡ��Ԫ����
	 * 	
	 * */
	public static void main(String[] args) {
		String string="abcdekka27qoq";
		char[] chs=string.toCharArray();
		//����map ����
		TreeMap< Character,Integer> trm=new TreeMap<>();
		for (char c : chs) {
			Integer integer=trm.get(c);
			if(integer==null)
			{
				trm.put(c, 1);
			
			}else {
				//�ȼӼ��ڽ��и�ֵ
				integer++;
				trm.put(c, integer);
				
			}
		}
		StringBuilder sb=new StringBuilder();
		Set<Character>set=trm.keySet();
		for (Character character : set) {
			Integer integer=trm.get(character);
			sb.append(character).append("(").append(integer).append(")");
		}
		String result=sb.toString();
		System.out.println(result);
		
		
		
	}
}
