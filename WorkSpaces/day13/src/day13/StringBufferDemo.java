package day13;

public class StringBufferDemo {
	public static void main(String[] args) {
		/*
		 * 需求：关于StringBuffer的案例
		 * 什么是StringBuffer：
		 * 		StringBuffer就是和String相比，他是一个容器长度和内容都可以发生改变
		 * 可以再StringBuffer中进行字符的增删改查动做
		 * 
		 * 步骤：
		 * 	A:append(char a):这个就是增加一个字符
		 *	B:insert(int offer,String str) 就是在第offer初添加一个字符串str
		 *	C:deletCharAt(int index) 删除指定索引出的字符
		 *	D:delet(int start,int end)删除从第start开始从end结束的字符串
		 *  E:setCharAt(int index,char c)修改指定index初的字符c
		 *  F：reverse（）反转zifuchuan
		 * 
		 * */
		//调用add方法
		add();
		//调用删除的方法
		remove();
		//调用修改的方法
		edit();
		
	}
	//关于StringBuffer增加字符串的操作以及插入的操作
	public static void add()
	{
		StringBuilder sb=new StringBuilder();
		//append可以直接添加一个字符串
		sb.append("HelloWorld");
		System.out.println("添加到Buffer的字符串为："+sb);
		sb.insert(2, "I,Am");
		System.out.println("插入到Buffer后的结果就是："+sb);
		
	
	}
	//关于删除StringBuffer中字符串的操作
	public static void remove()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("HelloJava");
		//删除指定的索引出的字符
		sb.deleteCharAt(3);
		System.out.println("删除一个指定位置的索引的结果："+sb);
		sb.delete(4, 8);
		System.out.println("删除指定范围内字符的结果："+sb);
		//结果为空
		sb.delete(0, sb.length());
		System.out.println("删除所有内容也就是清空缓存区最终结果："+sb);
	}
	//主要就是关于修改字符串，以及替换反转
	public static void edit()
	{
		StringBuilder sb=new StringBuilder();
		sb.append("HelloEveryBody");
		sb.setCharAt(3, 'P');
		System.out.println("修改后的Buffer结果为："+sb);
		System.out.println("反转后的Buffer的结果为："+sb.reverse());
		
	}

}
