package it.cast.test;

import it.cast.dao.impl.UserLoginImpl;
import it.cast.pojo.UserEntity;

import java.util.Scanner;

public class Test {

	/*
	 * 测试：
	 * 		1：登陆
	 * 		2：注册
	 * 		3和其他的字符：退出
	 * 
	 * 
	 * 改进：
	 * 		其实这个实例的一些具体操作还应该单独放入另一个类去实现
	 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true)	{
			System.out.println("欢迎来到itcast游乐中心");
			System.out.println("输入1进入登陆界面");
			System.out.println("输入2进入注册界面");
			System.out.println("输入3退出本系统");
			System.out.println("****************************************");
			System.out.println("请选择：");
			Scanner sc=new Scanner(System.in) ;
			String value=sc.nextLine();
			//UserLoginImpl ul=new UserLoginImpl();
			switch (value) {
			case "1":
//				System.out.println("请输入用户名：");
//				String name=sc.nextLine();
//				System.out.println("请输入密  码：");
//				String pwd=sc.nextLine();
//				
//				if(ul.isLogin(name, pwd))
//				{
//					System.out.println("大家一起来玩耍吧！！");
//				}
//				else {
//					System.out.println("登陆失败！！");
//				}
				chose1();
				break;
			case "2":
				chose2();
				break;
			case "3":
				
			default:
				System.out.println("谢谢使用！！！");
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
		System.out.println("欢迎来到注册界面");
		System.out.println("请输入用户名：");
		String username=sc.nextLine();
		System.out.println("请输入密  码：");
		String password=sc.nextLine();
		System.out.println("请输入手机号码：");
		String phonnum=sc.nextLine();
		System.out.println("请输入Email：");
		String email=sc.nextLine();
		UserEntity uEntity=new UserEntity(username,password,phonnum,email);
		ul.regist(uEntity);
		//sc.close();
	}
	public static void chose1()
	{
		Scanner sc=new Scanner(System.in) ;
		UserLoginImpl ul=new UserLoginImpl();
		System.out.println("请输入用户名：");
		String name=sc.nextLine();
		System.out.println("请输入密  码：");
		String pwd=sc.nextLine();
		
		if(ul.isLogin(name, pwd))
		{
			System.out.println("大家一起来玩耍吧！！");
		}
		else {
			System.out.println("登陆失败！！");
		}
		//sc.close();
	}

}
