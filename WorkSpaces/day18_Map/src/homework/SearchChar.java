package homework;

import java.util.HashMap;
import java.util.Set;

public class SearchChar {
	/*
	 * 1. ͳ���ַ�����ÿ���ַ����ֵĴ�����"
	 * cbxzbvavdvgd"��ȡ�ַ����У�
	 * ÿһ����ĸ���ִ�����a(1)b(2)c(1)d(2)g(1)v(3)x(1)z(1)
	 * 
	 * */
	
	public static void main(String[] args) {
		
		String string="cbxzbvavdvgd";
		//����Ҫ���ַ���ת�����ַ�����
		char[] ch=string.toCharArray();
		//��ΪҪ��ֵ�������֣���ʱ��Ϳ�����Map�����ϣ���chr����Ϊ�������ֵĴ���Ϊֵ
		HashMap<Character, Integer> hashMap=new HashMap<>();
		//b����ch���飬��ch��Ԫ����ΪHashMap�еļ����õ��������Ƿ���ֵ
		//���û��ֵ����hashMap�н�ֵ����1
		//�����ֵ�ͼ�1
		for (int i = 0; i < ch.length; i++) {
			Integer integer=hashMap.get(ch[i]);
			if(integer==null)
			{
				hashMap.put(ch[i], 1);
			}else {
				integer++;
				hashMap.put(ch[i], integer);
			}
		}
		
		//��Ԫ�ض�����Map��֮��
		//��ͨ����������Ԫ�ش���StringBuilderz��
		StringBuilder sbBuilder=new StringBuilder();
		Set<Character>set=hashMap.keySet();
		for (Character character : set) {
			Integer integer=hashMap.get(character);
			sbBuilder.append(character).append("(").append(integer).append(")");
		}
		String result=sbBuilder.toString();
		System.out.println(result);
		
	}

}
