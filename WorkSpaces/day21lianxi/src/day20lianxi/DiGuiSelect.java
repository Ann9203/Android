package day20lianxi;
import java.io.File;
public class DiGuiSelect {

		/*
		 * �ݹ���Һ�׺��java���ļ�(���Ļ�������һ��)
		 * */
	public static void main(String[] args) {
		File file=new File("Z:");
		diGui(file);
	/*
	 * �ڶ��ֻ�ȡ�ķ�����
	 * 		ͨ��List��������F/ilenamFilterһ��������Ȼ�������дadd����
	 * 
	 * */
//	File[] files=file.listFiles();
//	for (File file2 : files) {
//		System.out.println(file2);
//	}
//		String[]strings=file.list(new FilenameFilter() {
//			
//			/*
//			 * 
//			 * ������Ǹ���File name������һ���ļ���Ȼ��
//			 *ͨ���ļ��鿴�Ƿ����ļ����鿴�ļ��������Ƿ���һ��.java��Ϊ��β
//			 * */
//			@Override
//			public boolean accept(File dir, String name) {
//				// TODO Auto-generated method stub
//				//return false;
//				File file=new File(dir,name);
//				boolean flag1=file.isFile();
//				boolean flag2=name.endsWith(".mp3");
//				
//				return flag1 && flag2;
//			}
//		});
//		for (String string : strings) {
//			System.out.println(string);
//		}
		
	}
	/*
	 * �ݹ�
	 * 		��Ҫ���ǿ��ǲ���Ŀ¼����Ŀ¼�Ļ���Ҫ�������в��ң����ǵĻ����ҵ�Ҫ�ҵ��ļ�
	 * ͬ�¸����ļ��в����ǿյ�
	 * */
	public static void diGui(File  file)
	{
		//��ʾ���������е��ļ����µ�Ŀ¼
		File[] file2=file.listFiles();
//		if(file2!=null)
//		{
			for (File file3 : file2) {
				if(file3.isDirectory())
				{
					diGui(file3);
				}
				else if(file3.getName().endsWith(".mp3")) {
					System.out.println(file3.getName());
				}
			}
//		}
	
	}
}
