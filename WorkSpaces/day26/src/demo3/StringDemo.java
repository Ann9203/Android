package demo3;

import java.util.Scanner;

public class StringDemo {
	
	/*
	 * 
	 * 那么我们就写这样一个功能：键盘输入年龄，请打印出是哪个年龄段
		 0-9岁，儿童
		 10-19岁，少年
		 20-29岁，青年
		 30-39岁，壮年
		 40-49岁，中年
		 50-59岁，中老年
		 60岁以上，老年
	 * */
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		//String age=scanner.nextLine();
		//Scanner sc = new Scanner(System.in);
		int age =scanner.nextInt();
		//sortAge(age);
		sortAge1(age);

		
		
	}
	public static void sortAge(int age){
		if(age <= 9   && age >=0 ){
			System.out.println("儿童");
		}else if(age >= 10 && age <= 19){
			System.out.println("少年");
		}else	if(age >= 20 && age <= 29){
			System.out.println("青年");
		}else	if(age >= 30 && age <= 39){
			System.out.println("壮年");
		}else if(age >= 40 && age <= 49){
			System.out.println("中年");
		}else if(age >= 50 && age <= 59){
			System.out.println("中老年");
		}else{
			System.out.println("老年");
		}
	}
	
	public static void sortAge1(int age){
		String[] arrs={"儿童","少年","青年","壮年","中年","中老年","老年"};
		System.out.println(arrs[age/10]);
	}


}
