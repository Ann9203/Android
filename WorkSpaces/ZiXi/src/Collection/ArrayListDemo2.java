package Collection;

import java.util.ArrayList;

public class ArrayListDemo2 {
	//元素比较大小的第二种方法：
	//就是使用for循环，然后呢就像是选择排序，那一个元素
	//和后边的几个元素一次的进行比较就ＯＫ了
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
					//因为出去了一个元素，所以长度就要减一
					//如果不剪掉一就会造成越界
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
