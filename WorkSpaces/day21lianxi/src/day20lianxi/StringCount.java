package day20lianxi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringCount {
	/*
	 * ��һ���ַ���"wweeyusdfaald"
	 * �����ÿ���ַ������ֵĴ���
	 * ˼·��
	 * 		A:���ַ���Ӧ���ֵĴ�����Ҳ���Ǽ�ֵ�Ĺ�ϵ
	 * 		B:�Ȱ��ַ���ת�����ַ�����
	 * 		C:Ȼ��������ϣ��Ѽ��ϱ���������Ȼ��ͨ��StringBuilder��ӡ����
	 *                              
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		String string="wwwweeyusdfaald";
		char[] ch=string.toCharArray();
		//Integer num=1;
		//����һ������
		Map<Character,Integer> mp=new HashMap<Character, Integer>();
		for (Character c : ch) {
			//������������˹̶���ֵ��ÿ��ѭ��������ԭ����ֵ1
			//Integer num=1;
			Integer num=mp.get(c);
			//�жϼ������Ƿ�����ؼ�������������ؼ���
			//����� ValueֵΪ1
			if(!(mp.containsKey(c)))
			{
				mp.put(c, 1);
			}else  {
				num++;
				mp.put(c, num);
			}
			//System.out.println(c);
			
		}
		//�ڼ����������Ԫ�غ󣬾�Ҫ����StringBuilder����ӡ����
		StringBuilder sBuilder=new StringBuilder();
		//����Map����
		
		Set<Character>key=mp.keySet();
		
		for (Character character : key) {
			sBuilder.append(character).append("(").append(mp.get(character)).append(")");
			//System.out.println(character+"*************"+mp.get(character));
			
		}
		String result=sBuilder.toString();
		System.out.println(result);
//		String result=sBuilder.toString();
//		System.out.println(result);
		
	}

}
