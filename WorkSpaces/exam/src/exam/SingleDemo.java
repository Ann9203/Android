package exam;
public class SingleDemo {
	//����ģʽ���ӳټ���
	private SingleDemo(){}
	private static SingleDemo singleDemo;
	public static synchronized SingleDemo getInstance()
	{
		
		if(singleDemo==null)
		{
			singleDemo=new SingleDemo();
		}
		
		
		return singleDemo;
	}

}
