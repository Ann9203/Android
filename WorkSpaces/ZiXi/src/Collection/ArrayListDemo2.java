package Collection;

import java.util.ArrayList;

public class ArrayListDemo2 {
	//Ԫ�رȽϴ�С�ĵڶ��ַ�����
	//����ʹ��forѭ����Ȼ���ؾ�����ѡ��������һ��Ԫ��
	//�ͺ�ߵļ���Ԫ��һ�εĽ��бȽϾͣϣ���
	public static void main(String[] args) {
		ArrayList arrayList=new ArrayList();
		arrayList.add("Hello");
		arrayList .add("java");
		arrayList.add("andrio");
		arrayList .add("Hello");
		arrayList.add("java");
		arrayList.add("Java");
		arrayList.add("best");
		int length=arrayList.size();
		for(int x=0;x<length;x++)
		{
			for(int y=x+1;y<length ;y++)
			{
				if(arrayList.get(x).equals(arrayList.get(y)))
				{
					arrayList.remove(y);
					//��Ϊ��ȥ��һ��Ԫ�أ����Գ��Ⱦ�Ҫ��һ
					//���������һ�ͻ����Խ��
					length--;
				}
			}
		}
		for(int x=0;x<arrayList.size();x++)
		{
			String string=(String)arrayList.get(x);
			System.out.println(string);
		}
			
		
	}

}
