package digui;

import java.io.File;

public class DeleteDemo {
/*
 * 
 * ɾ��Z�Լ�����Ŀ¼�µ��ļ��Լ��ļ���
 * */
	public static void main(String[] args) {
		File file=new File("Z:\\aaa");
		deleteWenjian(file);
	}

private static void deleteWenjian(File file) {
	// TODO Auto-generated method stub
	File[] files=file.listFiles();
	if (file!=null) {
		//���Ⱦ���Ҫѭ��file�ļ�
		for (File file2 : files) {
			//�ж��ǲ���Ŀ¼������ǵĻ����ͼ����ݹ����
			if(file2.isDirectory())
			{
				deleteWenjian(file2);
			}else {
				//������ļ��Ļ���Ҫ����ֱ�ӵ�ɾ��
				System.out.println(file2.getName()+"***********"+file2.delete());
			}
		}
		//����ļ��еײ�û�ж����ˣ��ͻ�����ѭ������������Ҳ���Խ���ɾ��
		System.out.println(file.getName()+"**************"+file.delete());
	}
	
}
}
