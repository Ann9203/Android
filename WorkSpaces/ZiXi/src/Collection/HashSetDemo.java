package Collection;

import java.util.HashSet;
import java.util.Iterator;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class HashSetDemo {
	//set���ص���ǣ�Ԫ���ǲ������ظ��ģ�����Ԫ�ص������������
   //set������ʵ���� hasSet TreeSet
	public static void main(String[] args) {
		//��������Ҫ������hasSet
		//hasCode��Ԫ�صĴ洢λ��
		//equals�������ж�Ԫ���Ƿ����ظ���
		 HashSet hSet=new HashSet();
		 hSet.add("java");
		 hSet.add("Andriod");
		 hSet.add("Hello");
		 //�洢һ����ͬ��Ԫ��
		 hSet.add("java");
		 Iterator iterator =hSet.iterator();
		 while(iterator.hasNext())
		 {
			 //HashSet�в�û�а���ͬ��Ԫ�ش�ӡ����
			 String string =(String)iterator .next();
			 System.out.println(string.hashCode()+"***********"+string.equals(string));
		 }
		 //������HashSet��������ַ������ڴ���String����ַ���֮ǰ���Ѿ���HashCode��equals��������д
		 //����������String��Ķ��󡣡�
		 HashSet hashSet  =new HashSet();
		 //��hashSet�洢�����ʱ����ͬ�Ķ���ȷʵ�����ܹ����ȥ��֮���������ظ��Ķ���û�б�ʡ�Ե�һ��
		 //����Ϊ��Student��û����дHashCodeֵ��equals������
		 hashSet .add(new StudentDemo("С��",12));
		 hashSet .add(new StudentDemo("С��", 12));
		 hashSet .add(new StudentDemo("С��", 12));
		 Iterator iterator2=hashSet .iterator();
		 while(iterator2.hasNext())
		 {
			 StudentDemo stu=(StudentDemo)iterator2 .next();
			 System.out.println(stu.getName()+"**************"+stu.getAge());
			 System.out.println(stu.hashCode()+"********************"+stu.equals(stu));
		 }
	}

}
