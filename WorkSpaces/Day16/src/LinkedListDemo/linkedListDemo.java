package LinkedListDemo;

public class linkedListDemo {
	//��LinkedList��ģ��һ��ջ����
	//ջ���ص���ǣ��Ƚ����
	public static void main(String[] args) {
		
		StatcDemo sDemo=new StatcDemo();
		sDemo.add("Hello");
		sDemo.add("Every");
		sDemo.add("Body");
		sDemo.add("Hello");
		sDemo.add("Java");
		sDemo.add("World");
		//
		for (int i = 0; i < sDemo.size(); i++) {
			String string =(String)sDemo .get(i);
			System.out.println(string);
		}
	}

}
