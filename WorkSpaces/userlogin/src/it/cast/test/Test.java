package it.cast.test;

import it.cast.dao.impl.UserLoginImpl;
import it.cast.pojo.UserEntity;

import java.util.Scanner;

public class Test {

	/*
	 * ���ԣ�
	 * 		1����½
	 * 		2��ע��
	 * 		3���������ַ����˳�
	 * 
	 * 
	 * �Ľ���
	 * 		��ʵ���ʵ����һЩ���������Ӧ�õ���������һ����ȥʵ��
	 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true)	{
			System.out.println("��ӭ����itcast��������");
			System.out.println("����1�����½����");
			System.out.println("����2����ע�����");
			System.out.println("����3�˳���ϵͳ");
			System.out.println("****************************************");
			System.out.println("��ѡ��");
			Scanner sc=new Scanner(System.in) ;
			String value=sc.nextLine();
			//UserLoginImpl ul=new UserLoginImpl();
			switch (value) {
			case "1":
//				System.out.println("�������û�����");
//				String name=sc.nextLine();
//				System.out.println("��������  �룺");
//				String pwd=sc.nextLine();
//				
//				if(ul.isLogin(name, pwd))
//				{
//					System.out.println("���һ������ˣ�ɣ���");
//				}
//				else {
//					System.out.println("��½ʧ�ܣ���");
//				}
				chose1();
				break;
			case "2":
				chose2();
				break;
			case "3":
				
			default:
				System.out.println("ллʹ�ã�����");
				System.exit(0);
				sc.close();
				break;
			}
			
		}
		

	}
	public static void chose2()
	{
		UserLoginImpl ul=new UserLoginImpl();
		Scanner sc=new Scanner(System.in) ;
		System.out.println("��ӭ����ע�����");
		System.out.println("�������û�����");
		String username=sc.nextLine();
		System.out.println("��������  �룺");
		String password=sc.nextLine();
		System.out.println("�������ֻ����룺");
		String phonnum=sc.nextLine();
		System.out.println("������Email��");
		String email=sc.nextLine();
		UserEntity uEntity=new UserEntity(username,password,phonnum,email);
		ul.regist(uEntity);
		//sc.close();
	}
	public static void chose1()
	{
		Scanner sc=new Scanner(System.in) ;
		UserLoginImpl ul=new UserLoginImpl();
		System.out.println("�������û�����");
		String name=sc.nextLine();
		System.out.println("��������  �룺");
		String pwd=sc.nextLine();
		
		if(ul.isLogin(name, pwd))
		{
			System.out.println("���һ������ˣ�ɣ���");
		}
		else {
			System.out.println("��½ʧ�ܣ���");
		}
		//sc.close();
	}

}
