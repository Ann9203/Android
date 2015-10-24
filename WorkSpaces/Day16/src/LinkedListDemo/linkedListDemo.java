package LinkedListDemo;

public class linkedListDemo {
	//用LinkedList来模拟一个栈功能
	//栈的特点就是：先进后出
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
