package exam;
public class SingleDemo {
	//¿¡∫∫ƒ£ Ω£∫—”≥Ÿº”‘ÿ
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
