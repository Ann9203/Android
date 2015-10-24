package demo6;

public class Test {
	public static void main(String[] args) {
		//一个线程两个对象进行调用
		//一个线程里边有100个数字，两个对象来使用这个100个数字
		XianCheng xianCheng=new XianCheng();
		XianCheng xianCheng2=new XianCheng();
		xianCheng.setName("一号");
		xianCheng2.setName("二号");
		xianCheng.start();
		xianCheng2.start();
	}

}
