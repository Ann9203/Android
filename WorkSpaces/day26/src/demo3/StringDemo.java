package demo3;

import java.util.Scanner;

public class StringDemo {
	
	/*
	 * 
	 * ��ô���Ǿ�д����һ�����ܣ������������䣬���ӡ�����ĸ������
		 0-9�꣬��ͯ
		 10-19�꣬����
		 20-29�꣬����
		 30-39�꣬׳��
		 40-49�꣬����
		 50-59�꣬������
		 60�����ϣ�����
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
			System.out.println("��ͯ");
		}else if(age >= 10 && age <= 19){
			System.out.println("����");
		}else	if(age >= 20 && age <= 29){
			System.out.println("����");
		}else	if(age >= 30 && age <= 39){
			System.out.println("׳��");
		}else if(age >= 40 && age <= 49){
			System.out.println("����");
		}else if(age >= 50 && age <= 59){
			System.out.println("������");
		}else{
			System.out.println("����");
		}
	}
	
	public static void sortAge1(int age){
		String[] arrs={"��ͯ","����","����","׳��","����","������","����"};
		System.out.println(arrs[age/10]);
	}


}
